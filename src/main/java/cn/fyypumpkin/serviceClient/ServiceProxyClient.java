package cn.fyypumpkin.serviceClient;

import cn.fyypumpkin.protocol.ServiceProtocol;
import cn.fyypumpkin.remote.ClientRemote;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author fyypumpkin on 2018/8/6
 */
public class ServiceProxyClient {


    public static <T> T getInstance(Class<T> clazz){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new ServiceProxy(clazz));
    }

    public static class ServiceProxy implements InvocationHandler{

        private Class clazz;

        private ServiceProxy(Class clazz) {
            this.clazz = clazz;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            ServiceProtocol.ProtocolModel modal = new ServiceProtocol.ProtocolModel();
            modal.setClazz(clazz.getName());
            modal.setMethod(method.getName());
            modal.setArgs(args);

            String[] argType = new String[method.getParameterTypes().length];
            for(int i = 0 ;i<argType.length; i++){
                argType[i] = method.getParameterTypes()[i].getName();
            }
            modal.setArgTypes(argType);

            byte[] req = ServiceProtocol.PROTOCOL.encode(modal);
            byte[] resp = ClientRemote.client.getDataRemote("127.0.0.1", 9999, req);

            return ServiceProtocol.PROTOCOL.decode(resp, method.getReturnType());

        }
    }

}
