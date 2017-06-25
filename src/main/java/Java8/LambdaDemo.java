package Java8;

import java.util.Arrays;

/**
 * Created by lyl on 2017/6/25.
 */
public class LambdaDemo {

    public static void main(String[] args) {
        int [] arr = {1,2,4,5,6,7,3,7};
        Arrays.stream(arr).forEach(System.out::println);

    }

    public void forTest(){
        int[] arr = {1,2,3,4,6,7,3,8};
        Arrays.stream(arr).forEach((x) -> System.out.println(x));
    }


}
