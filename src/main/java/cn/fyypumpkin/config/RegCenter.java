package cn.fyypumpkin.config;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cn.fyypumpkin on 2018/8/6
 *         <p>
 *         类似一个注册中心
 */
public class RegCenter {
    public static final RegCenter REG = new RegCenter();

    private static final ConcurrentHashMap<String, Object> REG_INSTANCE_MAP = new ConcurrentHashMap<>();

    public boolean publish(Class clazz, Object obj) {
        return REG_INSTANCE_MAP.putIfAbsent(clazz.getName(), obj) != null;
    }

    public static Object get(Class<?> clazz) {
        return REG_INSTANCE_MAP.get(clazz.getName());
    }

    public static Object get(String name) {
        return REG_INSTANCE_MAP.get(name);
    }

    public static void query() {
        REG_INSTANCE_MAP.forEach((key, value) -> {
            System.out.println(key + " : " + value);
        });
    }
}
