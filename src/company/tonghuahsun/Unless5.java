package company.tonghuahsun;

/**
 * Created by carrots on 2022/2/28.
 */
public class Unless5 {
    public static void main(String[] args) {
        int[] arr = new int[999];
        //定义常量
        final short a = 3, b = 5;
        int count = 0;
        for (int i = 1; i < 999; i++) {
            arr[i]=i;
            if (i % a == 0 && (i % 100 == b || (i / 10) % 100 == b) ) {

                System.out.print(i + "\t");
                count++;
            }
        }
        System.out.println(count);
    }
}
