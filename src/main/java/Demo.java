import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by qiuxiangu on 2016/6/6.
 */
public class Demo {
    public static void main(String[] args) {
        String str = "a,b,c";
        String[] s = str.split(",", -1);
        Set<String> set = new HashSet(Arrays.asList(s));
        for(String tmp : set) {
            System.out.println(tmp);
        }
    }
}
