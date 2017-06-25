package ReenterLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lyl on 2017/6/24.
 */
public class FairLock implements Runnable {

    public static ReentrantLock lock = new ReentrantLock(true);

    public void run() {
        while (true){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "获得锁");
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLock lock = new FairLock();
        Thread t1 = new Thread(lock, "t1");
        Thread t2 = new Thread(lock, "t2");
        t1.start();t2.start();
    }
}
