package testorio.core;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Created by def on 13.01.17.
 */
public class JsonUtils {
    private static ObjectMapper objectMapper;

    public static ObjectMapper getMapper() {
        if (objectMapper == null) {
            initMapper();
        }
        return objectMapper;
    }

    private static synchronized void initMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
            objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        }
    }
}
