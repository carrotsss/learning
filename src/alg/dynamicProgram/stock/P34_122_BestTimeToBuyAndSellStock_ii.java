package alg.dynamicProgram.stock;

import java.util.function.IntPredicate;

/**
 * 股票最佳买入时间II
 * 给定一个数组，他的第i个元素表示一只给定股票第i天的价格
 * 设计一个算法计算你所能获取的最大利润，你尽可能多的完成更多的交易
 * 注意：你不能同时参与多笔交易（你必须在买入之前买出之前的股票
 * 输入：【7,1,5,3,6,4】
 * 输出：7
 *
 */
public class P34_122_BestTimeToBuyAndSellStock_ii {
    public int maxProfitOfficial(int[] prices) {
        int[][] dp = new int[prices.length][1];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);//第i天，不持有股票
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);//第i天，持有股票
        }
        return dp[prices.length - 1][0];//买出股票收益高于持有股票，因此取【0】
    }

    /**
     * 节省空间
     * @param prices
     * @return
     */
    public int maxxProfitOfficial2(int[] prices) {
        int[] dp = new int[2];
        dp[0] = -prices[0];
        dp[1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[0] = Math.max(dp[0], dp[1] - prices[i - 1]);
            dp[1] = Math.max(dp[1], dp[1] + prices[i - 1]);
        }
        return dp[1];
    }
}
