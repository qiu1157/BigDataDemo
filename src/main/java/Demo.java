import java.util.Arrays;

/**
 * Created by qiuxiangu on 2016/6/6.
 */
public class Demo {
    public static void main(String[] args) {
        String str = "a=b=c";
        String[] s = str.split("=", -1);
        System.out.println(Arrays.asList(s).toString());
    }
}
