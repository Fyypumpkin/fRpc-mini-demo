package cn.fyypumpkin.main;

import cn.fyypumpkin.main.test_interface.RpcInterface;
import cn.fyypumpkin.serviceClient.ServiceProxyClient;
import com.google.common.collect.Lists;


/**
 * @author cn.fyypumpkin on 2018/8/6
 */
public class ClientDemo {
    public static void main(String[] args) {
        RpcInterface rpcInterface = ServiceProxyClient.getInstance(RpcInterface.class);
        System.out.println(rpcInterface.sayHi("fyy"));

        System.out.println(rpcInterface.sayMulti("fyy","20", "male", Lists.newArrayList("hobby1","hobby2","hobby3","hobby4")));
    }
}
