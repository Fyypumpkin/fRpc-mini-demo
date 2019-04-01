package cn.fyypumpkin.main.test_interface;

import java.util.List;

/**
 * @author cn.fyypumpkin on 2018/8/6
 */
public interface RpcInterface {
    String sayHi(String name);

    String sayMulti(String name, String age, String sex, List<String> hobbies);

    default String getType() {
        return "RpcInterface";
    }
}
