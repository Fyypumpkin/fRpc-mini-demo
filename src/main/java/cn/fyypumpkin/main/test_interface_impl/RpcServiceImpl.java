package cn.fyypumpkin.main.test_interface_impl;


import cn.fyypumpkin.main.test_interface.RpcInterface;

import java.util.List;

/**
 * @author cn.fyypumpkin on 2018/8/6
 */
public class RpcServiceImpl implements RpcInterface {
    @Override
    public String getType() {
        return "RpcServiceImpl";
    }

    @Override
    public String sayHi(String name) {
        return "hello" + ": " + name;
    }

    @Override
    public String sayMulti(String name, String age, String sex, List<String> hobbies) {
        StringBuilder result = new StringBuilder();
        result.append("name: ").append(name).append("\n").append("age: ")
                .append(age).append("\n").append("sex: ").append(sex)
                .append("\n").append("hobbies: ");

        hobbies.forEach(item -> {
            result.append(item).append(" ");
        });

        return result.toString();
    }

    @Override
    public String toString() {
        return getType();
    }
}
