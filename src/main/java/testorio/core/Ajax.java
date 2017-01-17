package testorio.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by def on 14.01.17.
 */
public class Ajax {
    public static Map<String, Object> successResponse(Object object) {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("result", "success");
        response.put("data", object);
        return response;
    }

    public static Map<String, Object> emptyResponse() {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("result", "success");
        return response;
    }

    public static Map<String, Object> errorResponse(String errorMessage) {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("result", "error");
        response.put("message", errorMessage);
        return response;
    }
}
