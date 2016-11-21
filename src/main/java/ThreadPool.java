import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by qiuxiangu on 2016/11/21.
 */
public class ThreadPool {
    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        fixedThreadPool.execute(new ThreadDemo("a"));
        fixedThreadPool.execute(new ThreadDemo("b"));
    }
}
