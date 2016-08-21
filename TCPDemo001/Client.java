package TCPDemo001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by rxl on 16-8-12.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket client=new Socket("localhost",8080);
        BufferedReader br=new BufferedReader(new InputStreamReader(client.getInputStream()));
        String econ=br.readLine();
        System.out.println(econ);
    }
}
