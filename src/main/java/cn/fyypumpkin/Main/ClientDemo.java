package cn.fyypumpkin.Main;

import cn.fyypumpkin.remote.ClientRemote;
import cn.fyypumpkin.serviceClient.ServiceProxyClient;

import java.util.Arrays;

/**
 * @author fyypumpkin on 2018/8/6
 */
public class ClientDemo {
    public static void main(String[] args) {
        RpcInterface rpcInterface = ServiceProxyClient.getInstance(RpcInterface.class);
        System.out.println(rpcInterface.sayHi("fyy"));
    }
}
