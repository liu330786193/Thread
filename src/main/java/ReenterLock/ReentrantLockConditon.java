package ReenterLock;

import sun.jvm.hotspot.debugger.ThreadAccess;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lyl on 2017/6/24.
 */
public class ReentrantLockConditon implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    public void run() {
        try {
            lock.lock();
            condition.await();
            System.out.println("线程被唤醒了 可以继续了");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockConditon t1 = new ReentrantLockConditon();
        Thread thread1 = new Thread(t1);
        thread1.start();
        Thread.sleep(2000);
//        lock.lock();
        condition.signal();
//        lock.unlock();
    }

}
