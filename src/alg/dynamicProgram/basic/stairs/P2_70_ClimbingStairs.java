package alg.dynamicProgram.basic.stairs;

/**
 * 假设你在爬楼梯，需要n阶才能达到山顶。每次可以爬一个或两个台阶
 * 有多少种方法可以爬到楼顶呢
 * 输入：2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶1+1 ，2
 * 输入：3
 * 输出：3
 * 解释1+1+1 ，1+2 ，2+1
 */
public class P2_70_ClimbingStairs {
    //到第n个台阶只有从n-1和n-2两个台阶才可以
    public static int climb(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(climb(3));
    }
}
