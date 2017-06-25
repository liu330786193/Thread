package ReenterLock;

/**
 * Created by lyl on 2017/6/24.
 */
public class Synchronized implements Runnable {

    public static volatile int i = 0;

    public void run() {
        for (int j = 0; j < 10000000; j++){
            synchronized (Synchronized.class){
                try {
                    i++;
                }finally {

                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Synchronized t1 = new Synchronized();
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t1);
        Thread thread3 = new Thread(t1);
        Thread thread4 = new Thread(t1);
        Thread thread5 = new Thread(t1);
        Thread thread6 = new Thread(t1);
        thread1.start();thread2.start();thread3.start();thread4.start();thread5.start();thread6.start();
        Long startTime = System.currentTimeMillis();
        thread1.join();thread2.join();thread3.join();thread4.join();thread5.join();thread6.join();
        System.out.println(i);
        System.out.println(System.currentTimeMillis() - startTime);
    }

}
