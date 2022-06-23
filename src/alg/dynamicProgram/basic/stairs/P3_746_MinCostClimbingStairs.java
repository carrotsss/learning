package alg.dynamicProgram.basic.stairs;

import java.util.HashMap;

/**
 * 最小花费爬楼梯
 * 数组的每一个下标最为阶梯，第i个阶梯对应这一个非负数的体力花费值cost【i】(下标从0开始）
 * 每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值你就可以选择向上爬一个阶梯或爬两个阶梯。
 * 请你找出达到顶层的最低花费，开始时，你可以选择从下标0或1的元素作为初始阶梯
 * 输入：cost = 【10,15,20】
 * 输出：15
 */
public class P3_746_MinCostClimbingStairs {
    public static int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }
        if (cost.length == 1) {
            return cost[0];
        }
        int[] dp = new int[cost.length];
        dp[0] = cost[0];//爬一个阶梯的体力花费
        dp[1] = cost[1];//爬两个阶梯的体力花费
        //dp + cost取最小值
        for(int i = 2; i < cost.length; i++) {
            //状态转移表达式很重要，每一级阶梯都是前两级阶梯最小值 + 本阶梯花费
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }

    public static void main(String[] args) {
        int[] cost = {10, 15, 20};
        System.out.println(minCostClimbingStairs(cost));;
    }
}
