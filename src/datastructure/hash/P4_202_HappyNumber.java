package datastructure.hash;

import java.util.HashSet;

/**
 * 快乐数
 * 编写一个程序判断是否快乐数
 * 对于一个正整数，每一次将该树替换为他每个位置上的数字的平方和
 * 然后重复这个过程知道这个数变为1，也可能是无限循环但始终变不到1
 * 如果可以变为1，那么这个数就是快乐数
 * <p>
 * 输入：19
 * 输出：true
 * 解释：1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2  = 100
 * 1^2 + 0^2 + 0^2 = 1
 */
public class P4_202_HappyNumber {

    /**
     * 迭代过程中可能会出现重复数字
     */
    public static boolean isHappyOfficial(int n) {
        HashSet<Integer> set = new HashSet<>();
        int sum = 0;
        while (true) {
            sum = getSum(n);
            if (set.contains(sum)) {
                return false;
            }
            if (sum == 1) {
                return true;
            }
            set.add(sum);
            n = sum;
        }
    }

    static int getSum(int n) {
        int sum = 0;
        while (n != 0) {
            sum = sum + (n % 10) * (n % 10);
            n = n / 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        if (isHappyOfficial(99)) {
            System.out.println("是happy数");
        }
    }
}
