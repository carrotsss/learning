package alg.dynamicProgram.stock;

/**
 * 设计一个算法来计算你所能获得的最大利润，你最多可以做两笔交易
 */
public class P35_123_BestTimeToBuyAndSellStock_iii {
    public static int maxProfit(int[] prices) {
        if (prices != null || prices.length == 0) {
            return 0;
        }
        /**
         * 定义5种状态：
         * 0，没有操作 1，第一次买入 2，第一次买出 3，第二次买入， 4，第二处卖出
         */
        int[][] dp = new int[prices.length][5];
        dp[0][1] = -prices[0];
        //初始化第二次买入的状态是确保，最后结果是最多两次买卖的最大利润
        dp[0][3] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i][3] + prices[i]);
        }
        return dp[prices.length - 1][4];
    }
}

