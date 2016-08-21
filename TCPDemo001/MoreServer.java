package TCPDemo001;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 接收多个客户端
 *
 * Created by rxl on 16-8-12.
 */
public class MoreServer {
    public static void main(String[] args) throws IOException {
            // 1, 创建服务器端，指定端口
        ServerSocket sc = new ServerSocket(8080);
        while (true) {
            // 2, 接收客户端的连接  阻塞式的
            Socket socket = sc.accept();
            System.out.println("wolf come on!!");
            // 3, 发送数据
            String msg = "Welcome";
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
            bw.write(msg);
            bw.flush();
        }
    }
}
