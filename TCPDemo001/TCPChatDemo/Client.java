package TCPDemo001.TCPChatDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by rxl on 16-8-12.
 */
public class Client {

    public static void main(String[] args) throws IOException {
        System.out.println("请输入您的昵称：");
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String name=br.readLine();
        if (name.equals("")){
            return;

        }
        //创建一个客户端
        Socket client=new Socket("localhost",8889);
//        Thread t1=new Thread(new Send(client));
//
//        t1.getId();
//        t1.start();

        new Thread(new Send(client,name)).start();

        new Thread(new Receve(client)).start();

    }
}
