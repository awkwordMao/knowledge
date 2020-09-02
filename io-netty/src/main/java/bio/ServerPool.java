package bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerPool {

    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) throws IOException {
        /*服务器必备*/
        ServerSocket serverSocket = new ServerSocket();
        //绑定监听端口
        serverSocket.bind(new InetSocketAddress(10002));
        System.out.println("Server start.....");

        while(true){
            executorService.execute(new ServerTask(serverSocket.accept()));
        }
    }

    private static class ServerTask implements Runnable{

        private Socket socket = null;

        public ServerTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            //拿和客户端通讯的输入输出流
            try(ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream outputStream  = new ObjectOutputStream(socket.getOutputStream())
            ){
                //服务器的输入
                String userName = inputStream.readUTF();
                System.out.println("Accept client message: " + userName);

                outputStream.writeUTF("Hello, " + userName);
                outputStream.flush();

            }catch (Exception e){
                    e.printStackTrace();
            }
            finally{
                try{
                    socket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
