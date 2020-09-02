package bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        //客户端启动必备
        Socket socket = null;

        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;

        InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 10002);

        try{
            socket = new Socket();
            socket.connect(addr);

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            outputStream.writeUTF("mark");
            outputStream.flush();

            System.out.println(inputStream.readUTF());
        }finally{
            if(socket != null) socket.close();
            if(outputStream != null) outputStream.close();
            if(inputStream != null) inputStream.close();
        }
    }
}
