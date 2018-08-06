package cn.fyypumpkin.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author fyypumpkin on 2018/8/6
 */
public class JsonUtil {

    private static final ObjectMapper OBJ;

    private JsonUtil(){}

    static {
        OBJ = new ObjectMapper();
    }

    public static <T> T decode (byte[] json, Class<T> clazz) throws IOException {
        return OBJ.readValue(json, clazz);
    }

    public static <T> T decode (String json, Class<T> clazz) throws IOException {
        return OBJ.readValue(json, clazz);
    }

    public static byte[] encode(Object o) throws JsonProcessingException {
        return  OBJ.writeValueAsBytes(o);
    }

}
