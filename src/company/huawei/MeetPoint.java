package company.huawei;

import java.util.Scanner;

/**
 * Created by carrots on 2022/2/28.
 */
public class MeetPoint {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        char[] chars = line.toCharArray();
        int rowcount = Integer.parseInt(String.valueOf(line.charAt(0)));
        int colcount = Integer.parseInt(String.valueOf(line.charAt(1)));
        if (rowcount == 0) {
            return;
        }
        int[][] grid = new int[rowcount][colcount];
        int result = 0;
        for (int i = 0; i < rowcount; i++) {
            String s = scanner.nextLine();
            for (int j = 0; j < colcount; j++) {
                grid[i][j] = s.charAt(j);
            }
        }
        for(int i = 0; i < rowcount; i++) {
            for(int j = 0; j< colcount; j++) {
                if (grid[i][j] != 1) {
                    dfs(grid, i, j, rowcount, colcount);
                    result ++;
                }
            }
        }
    }

    private static int[][] direction = {{0,1},{0-1},{1,0},{-1,0}};
    private static void dfs(int[][] grid, int i, int j, int rowcount, int colcount) {
        if (i < 0 || i >= rowcount || j < 0 || j >= colcount) {
            return;
        }
        grid[i][j] = 3;
        for (int[] ints : direction) {
            dfs(grid, i + ints[0], j + ints[1], rowcount, colcount);
        }
    }
}
