package alg.dynamicProgram.bag;

/**
 * 动态规划阶梯步骤：
 * 1、确定dp数组（或dp table）以及下标含义
 * 2、确定地推公式
 * 3、dp数组如何初始化
 * 4、确定便利顺序
 * 5、举例推导dp数组
 *
 * 01背包
 * 有n件物品和一个最多能背重量为w的背包，第i件物品的重量是weight[i],得到的价值是value[i]，每件物品只能用一次，求解将哪些物品装入背包里物品价值总和最大。
 * 输入：weight = [1,3,4] value = [15, 20 ,30]  bagSize = 4
 * 输出：35
 */
public class P11_Bag01 {
    /**
     * 常规解法
     * 物品1（重量1，价值15）物品2（重量3，价值20）物品3（重量4，价值30）
     *              0            1            2            3            4
     * 前0个物品    0[0,0]       0[0,1]       0[0,2]       0[0,3]       0[0,4]
     * 前1个物品    0[1,0]       15[1,1]      15[1,2]      15[1,3]      15[1,4]
     * 前2个物品    0[2,0]       15[2,1]      15[2,2]      20[2,3]      35[2,4]
     * 前3个物品    0[3,0]       15[3,1]      15[3,2]      20[3,3]      35[3,4]
     * @param weight
     * @param value
     * @param bagSize
     */
    public static void weightBag(int[] weight, int[] value, int bagSize) {
        int wlen = weight.length, value0 = 0;
        //1、定义dp数组：dp[i][j]表示背包容量为j时，前i个物品能获得的最大价值
        //第一个坐标代表前几个物品，前i个物品实际上是物品0 - i-1；第二个坐标代表背包容量；坐标的值代表前i个物品最大价值
        int[][] dp = new int[wlen + 1][bagSize + 1];//横轴0 - len，纵轴0 - bagsize
        //2、初始化：背包容量为0时，能获得价值都为0
        for (int i = 0; i <= wlen; i++) {
            dp[i][0] = value0;
        }
        //4、遍历顺序：先遍历竖列物品，再遍历横排背包容量
        for (int i = 1; i <= wlen; i++) {//物品编号从0开始，但是考虑状态转移函数，从1开始遍历，当前物品就是i-1
            for (int j = 1; j <= bagSize; j++) {//因为0没有意义，从容量为1开始遍历
                if (j < weight[i - 1]) {//如果背包空间小于物品i-1的重量就不需要考虑i-1了
                    dp[i][j] = dp[i - 1][j];//此时前i个物品在背包容量j情况下，最大价值就是前i-1个物品在背包最大价值
                } else {
                    //3、状态转移公式
                    dp[i][j] = Math.max(dp[i - 1][j],//不放物品i的最大价值
                            dp[i - 1][j - weight[i - 1]] + value[i - 1]);//放物品i的价值 = 不加i-1物品的前i-1个物品的最大价值 + 物品i-1的价值
                }
            }
        }
        //5、返回结果，打印dp数组
        System.out.println(dp[wlen][bagSize]);
        for (int i = 0; i <= wlen; i++) {
            for (int j = 0; j <= bagSize; j++) {
                System.out.print(dp[i][j]++);
            }
            System.out.println();
        }
    }

    public static int[][] weightBagSelf(int[] weight, int[] value, int bagsize) {
        int dp[][] = new int[weight.length + 1][bagsize + 1];
        for (int i = 0; i < weight.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i <= weight.length; i++) {
            for (int j = 1; j <= bagsize; j++) {
                if (weight[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);
                }
            }
        }
        return dp;
    }

    /**
     * 滚动数组法
     *
     * @param weight
     * @param value
     * @param bagWeight
     */
    public static void weightBag2Official(int[] weight, int[] value, int bagWeight) {
        //定义dp数组：dp[j]表示背包容量为j时，获得的最大值
        int[] dp = new int[bagWeight + 1];
        //遍历顺序：先遍历物品，在遍历背包容量
        for (int i = 0; i <= weight.length; i++) {
            for (int j = bagWeight; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        for (int i = 0; i <= bagWeight; i++) {
            System.out.print(dp[i] + " ");
        }
    }

    public static int[] weightBag2Self(int[] weight, int[] value, int bagsize) {
        int[] dp = new int[bagsize + 1];
        for (int i = 0; i < weight.length; i++) {
            for (int j = bagsize; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        int[] weight = new int[]{1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        weightBag2Self(weight, value, bagSize);
    }
}
