package ReenterLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by lyl on 2017/6/25.
 */
public class LockSupportDemo {

    private static Object obj = new Object();

    public static class ChangeObjectThread implements Runnable{
        public void run() {
            synchronized (obj){
                System.out.println("线程等待！");
                LockSupport.park();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ChangeObjectThread obj1 = new ChangeObjectThread();
        ChangeObjectThread obj2 = new ChangeObjectThread();
        Thread t1 = new Thread(obj1);
        Thread t2 = new Thread(obj2);
        t1.start();
        Thread.sleep(1000);
        t2.start();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();

    }
}
