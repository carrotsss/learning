package company.zhaoyin;

import java.util.Scanner;

/**
 * Created by carrots on 2022/2/20.
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        char[] chars = string.toCharArray();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '*') {
                out.append(chars[i]);
            } else {
                out = out.insert(0, '*');
            }
        }
        System.out.println(out);
    }
}
