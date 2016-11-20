import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiuxiangu on 2016/11/19.
 */
public class ThreadDemo implements Runnable {
    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    private String threadName;

    public ThreadDemo(String threadName) {
        this.threadName = threadName;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(this.getThreadName() + " 正在运行......." + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main thread runing........");
        List<Thread> list = new ArrayList<Thread>();
        for (int i = 0; i < 5; i++) {
            ThreadDemo t = new ThreadDemo("Thread Name:" + i);
            Thread thread = new Thread(t);
            thread.start();
            list.add(thread);
        }
        for(Thread thread : list) {
            thread.join();
        }
        System.out.println("main thread end........");
    }
}
