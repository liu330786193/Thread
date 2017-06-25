package ReenterLock;


import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by lyl on 2017/6/24.
 */
public class SemaphoneDemo implements Runnable {

    public static final Semaphore sema = new Semaphore(5);

    public void run() {
        try {
            sema.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() + "done");
            sema.release();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        final SemaphoneDemo demo = new SemaphoneDemo();
        for (int i = 0; i < 20; i++){
            exec.submit(demo); //每次以五个线程为一组 一次打印出
        }
    }
}
