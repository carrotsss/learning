package alg.search.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 完全平方数
 * 给你一个整数n，返回和为n的完全平方数的最少数量
 * 完全平方数是一个整数，其值等于另一个整数的平方，换句话说，其值等于一个整数自乘的乘积。如1、4、9和16都是完全平方数
 * 输入：n=12
 * 输出：3
 * 说明：12 = 4+ 4 +4
 *
 * 输入：13
 * 输出：2
 * 说明：13=4+9
 */
public class P2_279_PerfectSquares {
    public static int numSquares(int n) {
        List<Integer> squares = generateSquares(n);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] marked = new boolean[n + 1];
        queue.add(n);
        marked[n] = true;
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            while (size-- > 0) {
                int cur = queue.poll();
                for (Integer square : squares) {
                    int next = cur - square;
                    if (next < 0) {
                        break;
                    }
                    if (next == 0) {
                        return level;
                    }
                    if (marked[next]) {
                        continue;
                    }
                    marked[next] = true;
                    queue.add(next);
                }
            }
        }
        return n;
    }

    /**
     * 生成小于n的平方数序列
     * @param n
     * @return 1,4,9...
     */
    private static List<Integer> generateSquares(int n) {
        List<Integer> squares = new ArrayList<>();
        int square = 1;
        int diff = 3;
        while (square <= n) {
            squares.add(square);
            square += diff;
            diff += 2;
        }
        return squares;
    }

    public static void main(String[] args) {
        System.out.println(numSquares(13));;
    }
}
