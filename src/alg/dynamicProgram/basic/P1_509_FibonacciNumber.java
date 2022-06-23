package alg.dynamicProgram.basic;

/**
 * 斐波那契数，通常用F(n)表示，形成序列称为斐波那契数列，该数列由01开始，后面每一个数字都是前面两项苏子的和也就是f(n) = f(n-1) + f(n-2)
 * 输入：2
 * 输出：1
 * 解释：f(2) = f(1）+ f(0) = 1 + 0 = 1
 * <p>
 * 1、确定dp数组及下标的含义
 * 第i个斐波那契数dp[i]
 * 2、确定递推公式
 * dp[i] = dp[i-1] + dp[i-2]
 * 3、dp数组如何初始化
 * dp[0] = 0 dp[1] = 1
 * 4、确定遍历顺序
 * 从递推公式dp[i] = dp[i-1] + dp[i-2]得知dp[i]是依赖于dp[i-1]和dp[i-2]，所以一定是从前往后遍历
 */
public class P1_509_FibonacciNumber {
    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(fib(2));
    }
}
