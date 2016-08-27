/**
 * Created by qiuxiangu on 2016/6/6.
 */
public class Demo {
    public static void main(String[] args) {
        String str = "a\tb\tc";
        String[] s = str.split("\t", -1);
        for (String tmp : s) {
            System.out.println(tmp);
        }
    }
}
