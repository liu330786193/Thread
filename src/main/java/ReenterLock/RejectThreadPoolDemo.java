package ReenterLock;

import java.util.concurrent.*;

/**
 * Created by lyl on 2017/6/25.
 */
public class RejectThreadPoolDemo {

    public static class MyTask implements Runnable{
        private String name;

        public MyTask(String name){
            this.name = name;
        }

        public void run() {
            System.out.println(System.currentTimeMillis() + ": Thread ID:" + Thread.currentThread().getId() + ", Task name = " + name);;
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        /*ExecutorService es = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(10),
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r.toString() + " is discard");
                    }
                }) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行：" + ((MyTask)r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
            }

            @Override
            protected void terminated() {
                super.terminated();
            }
        };*/

        ExecutorService es = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行：" + ((MyTask)r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
            }

            @Override
            protected void terminated() {
                super.terminated();
            }
        };

        for (int i = 0; i < 5; i++){
            MyTask task = new MyTask("Task name:" + i);
            es.submit(task);
            Thread.sleep(10);
        }
        es.shutdown();
    }

}
