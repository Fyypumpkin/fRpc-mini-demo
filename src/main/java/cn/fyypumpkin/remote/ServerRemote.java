package cn.fyypumpkin.remote;

import cn.fyypumpkin.processor.ServiceProcessor;
import cn.fyypumpkin.protocol.ServiceProtocol;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author fyypumpkin on 2018/8/6
 */
public class ServerRemote {
    private static final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void startServer(int port) throws Exception {
        final ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(port));
        try {
            while (true){
                final Socket socket = server.accept();
                executor.execute(new MyRunnable(socket));
            }
        }finally {
            server.close();
        }
    }

    class MyRunnable implements Runnable{
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
                        = ServiceProtocol.protocol.decode(Arrays.copyOfRange(data, 0, len), ServiceProtocol.ProtocolModel.class);
                System.out.println(modal.toString());
                Object o = ServiceProcessor.PROCESSOR.process(modal);
                os.write(ServiceProtocol.protocol.encode(o));
                os.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
