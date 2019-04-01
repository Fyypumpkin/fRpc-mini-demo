package cn.fyypumpkin.rpc_client;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author cn.fyypumpkin on 2018/8/6
 */
public class ClientRemote {
    public static final ClientRemote client = new ClientRemote();

    public byte[] getDataRemote(String hostname, int port, byte[] data){
        try(Socket socket = new Socket()){
            socket.connect(new InetSocketAddress(hostname, port));
            socket.getOutputStream().write(data);
            socket.getOutputStream().flush();

            // 10k
            byte[] res = new byte[10240];
            int len = socket.getInputStream().read(res);
            return Arrays.copyOfRange(res, 0, len);
        } catch (Exception e){
            return null;
        }
    }
}
