package alg.dynamicProgram.basic.path;

import javax.swing.*;

/**
 * 不同路径2
 * 左上角开始，只能向右或者向下，到达网格右下角。考虑网格中有障碍物，网各种障碍物和空位置分别用1和0表示。
 * 输入：obstacle = [[0,0,0].[0,1,0],[0,0,0]]
 * 输出：2
 */
public class P5_63_UniquePath_ii {
    public static int uniquePathsWithObstacleOfficial(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            obstacleGrid[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static int uniquePathWithObstaclesSelf(int[][] obstacles) {
        int m = obstacles.length, n = obstacles[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (dp[i][0] == 1) {
                break;//有障碍物就跳过后面的路
            }
            dp[i][0] = 1;//无障碍物就继续初始化
        }
        for (int i = 0; i < n; i++) {
            if (dp[0][i] == 1) {
                break;
            }
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (dp[i][j] == 1) {
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] obstacles = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(uniquePathsWithObstacleOfficial(obstacles));
    }
}
