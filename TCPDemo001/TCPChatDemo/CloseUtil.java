package TCPDemo001.TCPChatDemo;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by rxl on 16-8-12.
 */
public class CloseUtil {
    public static void CloseAll(Closeable...io){
        for (Closeable temp:io){
            try {
                    if(temp!=null){
                    temp.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
