package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2016/7/9.
 */
public class SocketTest {

    public static void main(String args[]) {
          Server server=new Server();
          Thread thread=new Thread(server);
          thread.start();
    }

}

class Server implements Runnable{
    //创建一个ServerSocket监听8080端口
    ServerSocket server=null;
    @Override
    public void run() {
        try {
            while (true){
                if (server==null){
                    System.err.println("init ServerSocket...");
                    server= new ServerSocket(8080);
                }

                //等待请求
                Socket socket = server.accept();
                //接收到请求后使用socket进行通信，创建BufferedReader用于读取数据，
                BufferedReader is = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                String line = is.readLine();
                System.out.println("received from client: " + line);
                //创建PrintWriter，用于发送数据
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                pw.println("received data: server接收到client信息" + line);
                pw.flush();
                //关闭资源
                pw.close();
                is.close();
                socket.close();
//                server.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class Client {
    public static void main(String args[]) {
        String msg = "Client Data";
        try {
            //创建一个Socket，跟本机的8080端口连接
            Socket socket = new Socket("127.0.0.1", 8080);
            // 使用Socket创建PrintWriter和BufferedReader进行读写数据
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            BufferedReader is = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            // 发送数据
            pw.println(msg);
            pw.flush();
            //接收数据
            String line = is.readLine();
            System.out.println("received from server: " + line);
        //关闭资源 pw.close(); is.close(); socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}