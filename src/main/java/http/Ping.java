package http;

import utils.StatisticsNode;

import java.util.List;
import java.util.Map;

public class Ping {

    /**
     * Method to retrieve status code from the request for every HttpEndpoint parsed from the yaml input file.
     * @param listOfEndpoints a list of endpoints parsed from the yaml input file
     * @param mapOfCounts a mapping of domain names to nodes tracking the statistics for that domain
     */
    public static void pingEndpoints(List<HttpEndpoint> listOfEndpoints, Map<String, StatisticsNode> mapOfCounts) {
        for (HttpEndpoint endpoint : listOfEndpoints) {
            StatisticsNode node = mapOfCounts.get(endpoint.getDomain());
            node.incrementTotalPings();
            int statusCode = ping(endpoint);
            // only 2xx responses are considered successful
            if (statusCode >= 200 && statusCode <= 299) node.incrementSuccessfulPings();
        }
    }

    private static int ping(HttpEndpoint endpoint) {
        return HttpAccess.getResponseCode(endpoint);
    }
}
