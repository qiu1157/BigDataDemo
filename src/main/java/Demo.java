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
        while (true) {
            Demo.getDate();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        String str = "i";
//        System.out.println((str.matches("^[A-Za-z]+$")&& str.length() ==1) || (str.matches("^[0-9]+$") && str.length() <3));

//        Set<String> set = new HashSet<String>();
//        set.add("a");
//        set.add("b");
//        System.out.println(set.toString());
//        String[] str = set.toArray(new String[set.size()]);
//        for (String s : str) {
//            System.out.println(s);
//        }
}
}
