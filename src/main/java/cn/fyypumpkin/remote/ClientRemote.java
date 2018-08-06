package cn.fyypumpkin.remote;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author fyypumpkin on 2018/8/6
 */
public class ClientRemote {
    public static final ClientRemote client = new ClientRemote();

    public byte[] getDataRemote(byte[] data){
        try(Socket socket = new Socket()){
            socket.connect(new InetSocketAddress("127.0.0.1", 9999));
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
