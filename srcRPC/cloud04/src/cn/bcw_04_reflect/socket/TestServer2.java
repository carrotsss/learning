package cn.bcw_04_reflect.socket;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by carrots on 2019/1/30.
 */
public class TestServer2 {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("localhost", 9898));
        while (true){
            Socket socket = serverSocket.accept();
            new Thread(new TestServerTask(socket));
        }
    }
}

