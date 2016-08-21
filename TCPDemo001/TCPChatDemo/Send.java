package TCPDemo001.TCPChatDemo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by rxl on 16-8-12.
 */
public class Send implements Runnable {
    //控制台输入流
    private BufferedReader br;
    //管道输出流
    private DataOutputStream dos;
    //控制线程的标识
    private boolean isRunning=true;

    private String name;

    String line=null;
    //初始化
    public Send(){
        br=new BufferedReader(new InputStreamReader(System.in));
    }

    public Send(Socket client,String name){
        this();
        try {
            dos=new DataOutputStream(client.getOutputStream());
            this.name=name;
            send(this.name);
        } catch (IOException e) {
           isRunning=false;
            CloseUtil.CloseAll(dos,br);
        }
    }
    //从控制台接收数据
    private String getmsgFrombr(){
        try {
            return br.readLine();
        } catch (IOException e) {
//            e.printStackTrace();
        }
        return "";
    }
    //发送数据
    public void send(String msg){

            try {
                if (msg!=null&&!msg.equals("")){
                    dos.writeUTF(msg);
                    dos.flush();
                }
            } catch (IOException e) {
                isRunning=false;
                CloseUtil.CloseAll(dos, br);
            }


    }

    @Override
    public void run() {
        //线程体
        while (isRunning){
            send(getmsgFrombr());

            if ("886".equals(line)){
                break;
        }

        }

    }
}
