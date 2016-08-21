package TCPDemo001.TCPChatDemo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by rxl on 16-8-12.
 */

public class Server {

    private List<MyServer> all=new ArrayList<MyServer>();

    public static void main(String[] args) throws IOException {

        new Server().start();

    }


    public void start() throws IOException {
        //创建一个服务器
        ServerSocket server=new ServerSocket(8889);
        while (true) {
            Socket client = server.accept();
            MyServer ms=new MyServer(client);
            all.add(ms);
            new Thread(ms).start();
        }
    }

    private class MyServer implements Runnable{
        private DataInputStream dis;
        private DataOutputStream dos;
        private boolean isRunning=true;
        private  String name;



        public  MyServer(Socket client){
            try {
                dis=new DataInputStream(client.getInputStream());
                dos=new DataOutputStream(client.getOutputStream());

                this.name=dis.readUTF();

                this.send("欢迎进入聊天室：");
                sendOthers(this.name+"进入聊天室",true);
            } catch (IOException e) {
                isRunning=false;
                CloseUtil.CloseAll(dis,dos);
            }
        }

        /**
         *发送数据
         */
        public String receive(){
            String msg="";
            try {
                msg=dis.readUTF();
            } catch (IOException e) {
                isRunning=false;
                CloseUtil.CloseAll(dis);
                all.remove(this);
            }
            return msg;
        }

        /**
         *接收数据
         * @param msg
         */
        public void send(String msg){
            if (null==msg || msg.equals("")){
                return;

            }
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                isRunning=false;
                CloseUtil.CloseAll(dos);
                all.remove(this);
            }
        }
        public void sendOthers(String msg,boolean sys){
            if (msg.startsWith("@") && msg.indexOf(":")>-1){
               String name=msg.substring(1,msg.indexOf(":"));
                String content=msg.substring(msg.indexOf(":")+1);
                for (MyServer other:all){
                    if (other.name.equals(name)){
                        other.send(this.name+"对您悄悄的说:"+content);
                    }
                }
            }else {
                for (MyServer other:all){
                    if (other==this){
                        continue;
                    }
                    if (sys){
                        other.send(("系统信息:"+msg));
                    }else {
                        other.send((this.name+"对所有人说:"+msg));
                    }
                }
            }
        }
        @Override
        public void run() {
            while (isRunning){
//            send(receive());
            sendOthers(receive(),false);

            }
        }
    }
}
