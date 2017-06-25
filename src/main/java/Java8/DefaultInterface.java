package Java8;

/**
 * Created by lyl on 2017/6/25.
 */
public interface DefaultInterface {
    void eat();
    default void run(){
        System.out.println("default interface");
    }
}
