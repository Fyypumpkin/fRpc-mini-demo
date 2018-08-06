package cn.fyypumpkin.protocol;


import cn.fyypumpkin.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author fyypumpkin on 2018/8/6
 */
public class ServiceProtocol {
    public static final ServiceProtocol PROTOCOL;

    static {
        PROTOCOL = new ServiceProtocol();
    }

    public byte[] encode(Object o) throws JsonProcessingException {
        return JsonUtil.encode(o);
    }

    public <T> T decode(byte[] bytes, Class<T> clazz) throws IOException {
     return JsonUtil.decode(bytes, clazz);
    }

    public static class ProtocolModel {
        private String clazz;
        private String method;
        private String[] argTypes;
        private Object[] args;

        @Override
        public String toString() {
            return "ProtocolModel{" +
                    "clazz='" + clazz + '\'' +
                    ", method='" + method + '\'' +
                    ", argTypes=" + Arrays.toString(argTypes) +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String[] getArgTypes() {
            return argTypes;
        }

        public void setArgTypes(String[] argTypes) {
            this.argTypes = argTypes;
        }

        public Object[] getArgs() {
            return args;
        }

        public void setArgs(Object[] args) {
            this.args = args;
        }
    }
}
