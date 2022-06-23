package alg.dynamicProgram.stock;

/**
 * 最多进行k此交易
 */
public class P36_124_BestTimeToBuyAndSellStock_iiii {
    public static int maxProfit(int k,int[] prices) {

        if (prices.length == 0) {
            return 0;
        }
        //[天数】【交易次数】【是否持有股票】
        int[][][] dp = new int[prices.length][k][2];

        //dp数组初始化
        //初始化所有的交易次数是为确保 最后结果是最多k次买卖的最大利润
        for (int i = 0; i < k; i++) {
            dp[0][i][1] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = i; j < k; j++) {
                //dp方程，0表示不持有/买出， 1表示持有/买入
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[prices.length - 1][k][0];
    }

}
