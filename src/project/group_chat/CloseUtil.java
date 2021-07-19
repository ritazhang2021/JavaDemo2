package project.group_chat;

import java.io.Closeable;
import java.io.IOException;

/**
 * @Author: Rita
 */
public class CloseUtil {
    public static void closeAll(Closeable... io) {
        for(Closeable temp : io) {
            try {
                if(temp != null) {
                    temp.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
