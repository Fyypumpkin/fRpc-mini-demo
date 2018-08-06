package cn.fyypumpkin.processor;

import cn.fyypumpkin.protocol.ServiceProtocol;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fyypumpkin on 2018/8/6
 */
public class ServiceProcessor {
    public static final ServiceProcessor PROCESSOR = new ServiceProcessor();

    private static final ConcurrentHashMap<String, Object> PROCESSOR_INSTANCE_MAP = new ConcurrentHashMap<>();

    public boolean publish(Class clazz, Object obj){
        return PROCESSOR_INSTANCE_MAP.putIfAbsent(clazz.getName(), obj) != null;
    }

    public Object process(ServiceProtocol.ProtocolModel modal){
        try{
            Class clazz = Class.forName(modal.getClazz());

            Class[] types = new Class[modal.getArgTypes().length];
            for(int i = 0; i<types.length;i++){
                types[i] = Class.forName(modal.getArgTypes()[i]);
            }

            Method method = clazz.getMethod(modal.getMethod(), types);

            Object obj = PROCESSOR_INSTANCE_MAP.get(modal.getClazz());

            PROCESSOR_INSTANCE_MAP.forEach((key, value) -> {
                System.out.println(key + " : " + value);
            });

            if(obj == null){
                return null;
            }

            return method.invoke(obj, modal.getArgs());
        }catch (Exception e){
            return null;
        }
    }
}
