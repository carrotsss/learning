package alg.dynamicProgram.stock;

public class P39_714_BestTimeToBuyAndSellStockWithTransactionFee_important {
    /**
     * 买出收手续费
     *
     * @param prices
     * @param fee
     * @return
     */
    public static int maxProfitOfficial1(int[] prices, int fee) {
        //0持股（买入）
        //1不持股（买出）
        //dp定义第一天持股/不持股，所得最多现金
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][0] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
        }
        return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][1]);
    }

    public static int maxPrifitOfficial2(int[] prices, int fee) {
        //0持股（买入）
        //1不持股（买出）
        //dp定义第一天持股/不持股，所得最多现金
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][0] - prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][1]);
    }
}
