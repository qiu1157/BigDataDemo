import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by qiuxiangu on 2016/11/20.
 */
public class CountDownLatchDemo implements Runnable {
    private CountDownLatch latch;
    private String ThreadName;
    static Map<String, String> map = new ConcurrentHashMap();
    public CountDownLatchDemo(CountDownLatch latch, String threadName) {
        this.latch = latch;
        ThreadName = threadName;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    public String getThreadName() {
        return ThreadName;
    }

    public void setThreadName(String threadName) {
        ThreadName = threadName;
    }

    public void run() {
        for (int i = 0; i < 9; i++) {
            System.out.println(this.getThreadName() + " 正在运行.......");
            map.put(getThreadName()+"_"+i, String.valueOf(i));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        latch.countDown();
        System.out.println("线程数：" + latch.getCount());
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Thread runing......");
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            CountDownLatchDemo c = new CountDownLatchDemo(latch, "Thread Name:" + i);
            Thread thread = new Thread(c);
            fixedThreadPool.execute(thread);
        }
        latch.await();
        for(Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key:"+ entry.getKey() + "--->> value:"+entry.getValue());
        }
        System.out.println("Thread end....");
    }
}
