package Java8;

import java.security.Principal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /*final  CompletableFuture<Integer> future = new CompletableFuture<>();
        new Thread(new CompletableFutureDemo(future)).start();
        Thread.sleep(1000);
        future.complete(600);*/

        /*final CompletableFuture<Void> future = CompletableFuture
                .supplyAsync(() -> calc(33))
                .thenApply((i) -> Integer.toString(i))
                .thenApply((str) -> "\"" + str + "\"")
                .thenAccept(System.out::println);*/

        final CompletableFuture<Void> future = CompletableFuture
                .supplyAsync(() -> calc(33))
                .exceptionally(ex -> {
                    System.out.println(ex.toString());
                    return 0;
                })
                .thenApply((i) -> Integer.toString(i))
                .thenApply((str) -> "\"" + str + "\"")
                .thenAccept(System.out::println);
    }

    public static Integer calc(Integer para){
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }
//        return para * para;
        return para /0;
    }
}
