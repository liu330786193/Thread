package ReenterLock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lyl on 2017/6/25.
 */
public class CountDownLatchDemo implements Runnable {

    public static final CountDownLatch end = new CountDownLatch(10);
    public static final CountDownLatchDemo demo = new CountDownLatchDemo();

    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("检查完成");
            end.countDown();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++){
            exec.submit(demo);
        }
        end.await();
        System.out.println("开火");
        exec.shutdown();
    }
}
