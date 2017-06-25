package ReenterLock;

import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by lyl on 2017/6/24.
 */
public class ReadWriteLockDemo {

//    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;

    public Object handleRead(Lock lock) throws InterruptedException{
        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        }finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock, int index) throws InterruptedException{
        try {
            lock.lock();
            Thread.sleep(1000);
            value = index;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();
        final Runnable readRunnable = new Runnable() {
            public void run() {
                try {
                    demo.handleRead(readLock);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        Runnable writeRunnable = new Runnable() {
            public void run() {
                try {
                    demo.handleWrite(writeLock, new Random().nextInt());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 18; i++){
            new Thread(readRunnable).start();
        }
        for (int i = 0; i < 18; i++){
            new Thread(writeRunnable).start();
        }
    }

}
