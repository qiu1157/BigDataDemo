import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by qiuxiangu on 2016/6/6.
 */
public class Demo {

    public static void getDate() {
        long time = System.currentTimeMillis();
        DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        System.out.println(df.format(time));
    }


    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(System.currentTimeMillis() - (24 * 3 * 60 * 60 * 1000));
        System.out.println(date);
    }
}
