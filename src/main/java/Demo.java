import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by qiuxiangu on 2016/6/6.
 */
public class Demo {
    public static void main(String[] args) {
        while (true) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long time = System.currentTimeMillis();
            System.out.println(formatter.format(time));
            try {
                Thread.sleep(60000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
