package cn.fyypumpkin.main;

import cn.fyypumpkin.config.RegCenter;
import cn.fyypumpkin.main.test_interface.RpcInterface;
import cn.fyypumpkin.main.test_interface_impl.RpcServiceImpl;
import cn.fyypumpkin.rpc_remote.ServerRemote;

/**
 * @author cn.fyypumpkin on 4/1/19
 */
public class reg {
    public static void main(String[] args) throws Exception {
        // 注册
        RegCenter.REG.publish(RpcInterface.class, new RpcServiceImpl());

        ServerRemote remote = new ServerRemote();
        remote.startServer(9999);
    }
}
