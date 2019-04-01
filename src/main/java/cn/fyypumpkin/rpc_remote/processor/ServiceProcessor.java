package cn.fyypumpkin.rpc_remote.processor;

import cn.fyypumpkin.config.RegCenter;
import cn.fyypumpkin.protocol.ServiceProtocol;

import java.lang.reflect.Method;

/**
 * @author cn.fyypumpkin on 4/1/19
 *
 * remote 处理器
 */
public class ServiceProcessor {
    public static final ServiceProcessor PROCESSOR = new ServiceProcessor();

    public Object process(ServiceProtocol.ProtocolModel modal) {
        try {
            // 获取 class
            Class clazz = Class.forName(modal.getClazz());

            // 获取入参 class
            Class[] types = new Class[modal.getArgTypes().length];
            for (int i = 0; i < types.length; i++) {
                types[i] = Class.forName(modal.getArgTypes()[i]);
            }

            // 获取 method
            Method method = clazz.getMethod(modal.getMethod(), types);

            // 获取之前注册上来的对象
            Object obj = RegCenter.get(modal.getClazz());

            // 遍历节点
            RegCenter.query();

            if (obj == null) {
                return null;
            }

            // 执行
            return method.invoke(obj, modal.getArgs());
        } catch (Exception e) {
            return null;
        }
    }
}
