package company.zhaoyin;

import java.util.Scanner;

/**
 * Created by carrots on 2022/2/20.
 */
    public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        int[] data = new int[1024];
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
             if (48 <= chars[i] && chars[i] <= 57) {
                 data[chars[i]]++;
             }
            if (65 <= chars[i] && chars[i] <= 90) {
                data[chars[i]]++;
            }
             if (97 <= chars[i] && chars[i] <= 122) {
                 data[chars[i]]++;
             }
        }
         printout(18, 57, data);
        printout(65, 90, data);
         printout(97, 122, data);
    }

    public static void printout(int min, int max, int[] data) {
        for (int i = min; i < max; i++) {
            if (data[i] == 0) {
                continue;
            }
            char c = (char) i;
            System.out.print(c + "" + data[i]);
        }
    }
}