package ReenterLock;

import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lyl on 2017/6/24.
 * 锁等待超时 - ReentrantLock
 */
public class LockWait implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();

    public void run() {
        try {
            /**
             * 获取所得时长 计时单位
             * 如果没有获得锁 则返货false
             * 也可以不带参数 失败直接返回flase
             * 使用tryLock() 不断地尝试获得锁 某些情况可以避免死锁 只要
             * 执行的时间够长 总是可以获得锁
             */
            if (lock.tryLock(5, TimeUnit.SECONDS)){
                Thread.sleep(6000);
            }else {
                System.out.println("获取锁失败");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LockWait lockWait = new LockWait();
        Thread t1 = new Thread(lockWait);
        Thread t2 = new Thread(lockWait);
        t1.start();
        t2.start();
    }

}
