package cn.bcw_04_reflect.socket;


import java.io.*;
import java.net.Socket;

/**
 * Created by carrots on 2019/1/30.
 */
public class TestClient2 {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("localhost", 9898);;


            OutputStream out = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(new BufferedOutputStream(out));
            pw.println("socket  out");
            pw.flush();

            InputStream in = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String readline = bufferedReader.readLine();


            System.out.println("readline:" + readline);
            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
