package company.huawei;

import java.util.Scanner;

/**
 * Created by carrots on 2022/2/28.
 */
public class Stairs {
    public static int climbStairs(int n) {
        int[] way = new int[n + 1];
        way[0] = 1;
        way[1] = 1;
        way[2] = 1;
        way[3] = 2;
        //dp[i] = dp[i-1] + dp[i-3]
        for (int i = 3; i <= n; i++) {
            way[i] = way[i - 1] + way[i - 3];
        }
        return way[n];
    }

    public static int main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] way = new int[n + 1];
        way[0] = 1;
        way[1] = 1;
        way[2] = 1;
        way[3] = 2;
        for (int i = 3; i <= n; i++) {
            way[i] = way[i - 1] + way[i - 3];
        }
        return way[n];
    }
}
