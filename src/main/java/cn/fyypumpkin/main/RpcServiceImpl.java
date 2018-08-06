package cn.fyypumpkin.main;

import cn.fyypumpkin.processor.ServiceProcessor;
import cn.fyypumpkin.remote.ServerRemote;

/**
 * @author fyypumpkin on 2018/8/6
 */
public class RpcServiceImpl implements RpcInterface {
    @Override
    public String sayHi(String name) {
        return "hello" + ": " + name;
    }

    public static void main(String[] args) throws Exception {
        ServiceProcessor.PROCESSOR.publish(RpcInterface.class, new RpcServiceImpl());

        ServerRemote remote = new ServerRemote();
        remote.startServer(9999);
    }
}
