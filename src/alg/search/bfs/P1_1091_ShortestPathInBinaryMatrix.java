package alg.search.bfs;

import javafx.util.Pair;

import java.util.Currency;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 0可以表示经过某个位置，求解从左上角到右下角的最短路径长度
 * 输入：
 * 【0,0,0】
 * 【1,1,0】
 * 【1,1,0】
 * 输出：4
 *
 */
public class P1_1091_ShortestPathInBinaryMatrix {
    public static int shortestPathBinaryMatrixOfficial1(int[][] grids) {
        if (grids == null || grids.length == 0) {
            return -1;
        }
        int[][] direction = {{1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 1}, {-1, -1}, {-1, 0}, {-1, 1}};
        /**
         * 1 -1
         * 1 0
         * 1 1
         * 0 -1
         * 0 1
         * -1 -1
         * -1 0
         * -1 1
         */
        int m = grids.length, n = grids[0].length;
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        int pathLength = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            pathLength++;
            while (size-- > 0) {
                Pair<Integer, Integer> cur = queue.poll();
                int cr = cur.getKey(), cc = cur.getValue();
                if (grids[cr][cc] == 1) {
                    continue;
                }
                if (cr == m - 1 && cc == n - 1) {
                    return pathLength;
                }
                grids[cr][cc] = 1;//标记
                for (int[] d : direction) {
                    int nr = cr + d[0], nc = cc + d[1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }
                    queue.add(new Pair<>(nr, nc));
                }
            }
        }
        return -1;
    }

    static int n = 0;
    static int[][] direction = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, 0}, {1, -1}, {1, 1}};
    public static int shortestPathBinaryMatrix(int[][] grid) {
        n = grid.length;
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
            return -1;
        }
        if (n == 1) {
            return 1;
        }
        Deque<int[]> dq = new LinkedList<>();
        grid[0][0] = 1;
        dq.offer(new int[]{0, 0});
        while (!dq.isEmpty()) {
            int num = dq.size();
            for (int i = 0; i < num; i++) {
                int[] cur = dq.poll();
                int x = cur[0];
                int y = cur[1];
                for (int j = 0; j < 8; j++) {
                    int tx = x + direction[j][0];
                    int ty = y + direction[j][1];
                    if (tx < 0 || tx > n - 1 || ty < 0 || ty > n - 1) {
                        continue;
                    }
                    if (grid[tx][ty] == 0) {
                        if (tx == n - 1 && ty == n - 1) {
                            return grid[x][y] + 1;
                        }
                        grid[tx][ty] = grid[x][y] + 1;
                        dq.offer(new int[]{tx, ty});
                    }
                }
            }
        }
        return -1;
    }
}
