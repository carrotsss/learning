package alg.dynamicProgram.basic;

/**
 * 给定一个正整数n，将其拆分成至少两个正整数的和，并使这些整数的乘积最大化。返回你可以获得的最大乘积
 * 输入：2
 * 输出：1
 * 2 = 1+1， 1*1 = 1
 */
public class P6_343_IntegerBreak {
    public static int integerBreakOfficial(int n) {
        //dp[i]为正整数i拆分结果的最大乘积
        int[] dp = new int[n + 1];
        dp[2] = 1;
        //根据状态转移方程进行遍历
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i - 1; j++) {
                //j * （i-j)代表把i拆分为j和i-j两个数相乘
                //j * dp[i-j]代表把i拆分成j和继续把（i-j）这个数拆分，取（i-j)拆分结果中的最大乘积与j相乘
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));//将当前dp[i]值和重新计算的dp[i]值取最大
            }
        }
        return dp[n];
    }

    public static int integerBreakSelf(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 2; i <= n; i++) {
            //j = i - 1的话i - j = 1，i和i-j相乘无差别
            for (int j = 0; j < i - 1; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (j - i), j * dp[i - j]));
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(integerBreakSelf(2));;
    }
}
