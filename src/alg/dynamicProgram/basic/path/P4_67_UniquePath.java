package alg.dynamicProgram.basic.path;

/**
 * 一个机器人位于一个M*N网格的左上角（起始点爱下图标记为‘start’）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网络的右下角(在图中标记点为finish）
 * 总共有多少条不同路径
 * 输入：3.，7
 * 输出：28
 */
public class P4_67_UniquePath {
    /**
     * 1、确定数组下标含义，到每个坐标可能的路径种类（确定返回信息）
     * 2、递推公式dp[i][j] = dp[i-1][j] + dp[i][j-1]（确定递推公式)
     * 3、初始化dp[i][0] = 1 dp[0][i]初始化横竖就可(初始化信息）
     * 4、遍历顺序，一行一行遍历
     * 5、推到结果
     * @param m
     * @param n
     * @return
     */
    public static int uniquePathsOfficial(int m, int n) {
        int[][] dp = new int[m][n];
        //初始化
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for(int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static int uniquePathsSelf(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for(int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePathsOfficial(3, 7));;
    }
}
