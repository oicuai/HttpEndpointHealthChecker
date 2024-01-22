package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Constants {

    public enum HTTPMethods {
        GET, PUT, POST, PATCH, DELETE, HEAD, OPTIONS, TRACE, CONNECT;
    }

    public static final int TIMEOUT_RESPONSE_CODE = 408;
    // status code if sending the request results in an exception not related to timing out
    public static final int TEAPOT_RESPONSE_CODE = 418;

    // time out of 500 ms
    public static final long TIMEOUT = 500L;
    // time to wait between health check pings
    public static final long WAIT_TIME = 15000L;

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
}
