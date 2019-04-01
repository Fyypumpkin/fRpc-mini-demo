package cn.fyypumpkin.rpc_remote;

import cn.fyypumpkin.protocol.ServiceProtocol;
import cn.fyypumpkin.rpc_remote.processor.ServiceProcessor;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author cn.fyypumpkin on 2018/8/6
 */
public class ServerRemote {
    private static final ExecutorService executor =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void startServer(int port) throws Exception {
        final ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(port));
        try {
            while (true){
                final Socket socket = server.accept();
                executor.execute(new MyRunnable(socket));
            }
        } finally {
            server.close();
        }
    }

    class MyRunnable implements Runnable {
        private Socket socket;
        public MyRunnable(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try(InputStream is = socket.getInputStream(); OutputStream os = socket.getOutputStream()){
                byte[] data = new byte[10240];
                int len = is.read(data);

                ServiceProtocol.ProtocolModel modal
                        = ServiceProtocol.PROTOCOL
                        .decode(Arrays.copyOfRange(data, 0, len), ServiceProtocol.ProtocolModel.class);

                System.out.println(modal.toString());

                // 远程调用
                Object o = ServiceProcessor.PROCESSOR.process(modal);

                os.write(ServiceProtocol.PROTOCOL.encode(o));
                os.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
