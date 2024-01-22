package http;

import lombok.Data;
import utils.Constants.HTTPMethods;

import java.util.HashMap;
import java.util.Map;

@Data
public class HttpEndpoint {

    private String name;
    private String url;
    private String domain;
    private HTTPMethods method = HTTPMethods.GET;
    private Map<String, String> headers = new HashMap<>();
    private String body = "";

}