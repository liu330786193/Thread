package Java8;

import java.security.Principal;
import java.util.concurrent.CompletableFuture;

/**
 * Created by lyl on 2017/6/26.
 */
public class CompletableFutureDemo implements Runnable {

    CompletableFuture<Integer> re = null;

    public CompletableFutureDemo(CompletableFuture<Integer> re){
        this.re = re;
    }

    @Override
    public void run() {
        int myRe = 0;
        try {
            myRe = re.get() * re.get();
        }catch (Exception e){

        }
        System.out.println(myRe);
    }

    public static void main(String[] args) throws InterruptedException {
        final  CompletableFuture<Integer> future = new CompletableFuture<>();
        new Thread(new CompletableFutureDemo(future)).start();
        Thread.sleep(1000);
        future.complete(60);
    }
}
