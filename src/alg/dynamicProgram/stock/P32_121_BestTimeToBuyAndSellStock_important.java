package alg.dynamicProgram.stock;

import java.util.function.IntPredicate;

/**
 * 给定一个数组prices，他的第i个元素prices[i]表示一只给定股票第i天的价格
 * 你只能选择莫一天买入这个股票，并选择在未来某一个不同的日子卖出该股票，设计一个算法来计算你能获取的最大利润。
 * 输入：【7,1,5,3,6,4】
 * 输出：5
 */
public class P32_121_BestTimeToBuyAndSellStock_important {

    /**
     * 暴力解法
     * @param prices
     * @return
     */
    public int maxProfitForceOfficial(int[] prices) {
        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                result = Math.max(result, prices[j] - prices[i]);
            }
        }
        return result;
    }

    /**
     * 贪心解法
     * @param prices
     * @return
     */
    public static int maxProfitGreedyOfficial(int[] prices) {
        int low = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            low = Math.min(low, prices[i]);
            result = Math.max(result, prices[i] - low);
        }
        return result;
    }

    /**
     * 动态规划(重点关注)
     * @param prices
     * @return
     */
    public static int maxProfitDynamicOfficial(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        //dp【i][0]代表第i天持有股票的最大收益
        //dp【i][1]代表第i天不持有股票的最大收益
        int[][] dp = new int[prices.length][2];
        int result = 0;
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            //第i天持有股票的最大收益 = 前i-1天持有股票且第i天不买入的最大收益 和 第i天买入的最大收益 取最大值
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            //第i天不持有股票的最大收益 = 前i-1天不买入且第i天不买入的最大收益 和 第i-1天持有股票且第i天卖出的最大收益
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[prices.length - 1][1];//返回最后一天不持有任何股票截止
    }

    public static int maxProfitDynamicSelf(int[] prices) {
        int[][] dp = new int[prices.length][2];
        int result = 0;
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 0; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[prices.length - 1][1];
    }

    /**
     * 动态规划2
     *
     * @param prices
     * @return
     */
    public static int maxProfitDynamicOfficila2(int[] prices) {
        int[] dp = new int[2];
        //第一次交易，一次交易有买入卖出两种状态
        //0代表持有，1代表卖出
        dp[0] = -prices[0];
        dp[1] = 0;
        //可以参考斐波那契问题的优化方式
        //我们从i-1开始遍历数组，一共有prices.length天
        // 所以是i<=prices.length
        for (int i = 1; i <= prices.length; i++) {
            //前一天持有；或当天买入
            dp[0] = Math.max(dp[0], -prices[i - 1]);
            //如果dp【0】被更新，那么dp【1】肯定会被更新为正数的dp【1】
            //而不是dp【0】 + prices【i-1】==0的0
            //所以这里使用会改变的dp【0】也是可以的
            //当然dp【1】初始值为0，被更新成0也没影响
            //前一天买出；或当天卖出，要当天买出，得前一天持有才行
            dp[1] = Math.max(dp[1], dp[0] + prices[i - 1]);
        }
        return dp[1];
    }
}
