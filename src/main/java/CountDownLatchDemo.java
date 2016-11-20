import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by qiuxiangu on 2016/11/20.
 */
public class CountDownLatchDemo implements Runnable {
    private CountDownLatch latch;
    private String ThreadName;

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
        CountDownLatch latch = new CountDownLatch(6);
        for (int i = 0; i < 5; i++) {
            CountDownLatchDemo c = new CountDownLatchDemo(latch, "Thread Name:" + i);
            Thread thread = new Thread(c);
            thread.start();

        }
        latch.await();
        System.out.println("Thread end....");
    }
}
