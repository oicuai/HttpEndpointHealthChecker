package http;

import utils.Constants;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpConnectTimeoutException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpAccess {

    private static HttpClient client;
    private static HttpRequest request;

    private static void initClient() {
        // connectTimeout in the builder is to timeout requests if the latency is above 500 ms
        client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(Constants.TIMEOUT))
                .build();
    }

    private static void createRequestWitHeaders(HttpEndpoint endpoint) {
        List<String> headersList = new ArrayList<>();
        for (Map.Entry<String, String> entry : endpoint.getHeaders().entrySet()) {
            headersList.add(entry.getKey());
            headersList.add(entry.getValue());
        }

        request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint.getUrl()))
                .headers(headersList.toArray(new String[0]))
                .method(endpoint.getMethod().toString(), HttpRequest.BodyPublishers.ofString(endpoint.getBody()))
                .build();
    }

    private static void createRequestWithoutHeaders(HttpEndpoint endpoint) {
        request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint.getUrl()))
                .method(endpoint.getMethod().toString(), HttpRequest.BodyPublishers.ofString(endpoint.getBody()))
                .build();
    }

    private static HttpResponse<String> sendRequest(HttpEndpoint endpoint) throws IOException, InterruptedException {
        initClient();

        // the request builder will fail if the array of headers has length == 0
        // so two methods are defined to prevent issues
        if (endpoint.getHeaders().size() > 0) createRequestWitHeaders(endpoint);
        else createRequestWithoutHeaders(endpoint);
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    /**
     * Returns the response code from the request represented by the endpoint parameter.
     * @param endpoint the object containing the fields of the request from the yaml input file
     * @return the response code from the response to the request sent to the endpoint
     */
    public static int getResponseCode(HttpEndpoint endpoint) {
        try {
            HttpResponse<String> response = sendRequest(endpoint);
            return response.statusCode();
        } catch (HttpConnectTimeoutException e) {
            return Constants.TIMEOUT_RESPONSE_CODE;
        } catch (IOException | InterruptedException e) {
            return Constants.TEAPOT_RESPONSE_CODE;
        }
    }
}
