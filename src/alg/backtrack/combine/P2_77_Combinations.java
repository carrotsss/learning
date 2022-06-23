package alg.backtrack.combine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合
 * 给定两个指数n和k，返回范围【1，n】中所有可能的k个
 * 你可以按照任何顺序返回答案
 * 示例：
 * 输入：n = 4， k= 2
 * 输出：【【2,4】，【3,4】，【2,3】，【1,2】，[1.3],[1,4]】
 *
 * k = 2, n = 4
 * 1234
 * 1 2 3 4
 * 12 13 14 23 24 34
 */
public class P2_77_Combinations {
    /**
     * 递归返回值
     */
    private static List<List<Integer>> result = new ArrayList<>();//存最终结果
    private static LinkedList<Integer> path = new LinkedList<>();//存每个子集

    public static List<List<Integer>> combineOfficial(int n, int k) {
        combineHelperOfficial(n, k, 1);
        return result;
    }

    /**
     * 每次从集合中选择元素，可选择的范围随着选择的进行而收缩，调整可选择的范围，就是要startIndex
     * @param n
     * @param k
     * @param startIndex 用来记录本层递归的中，集合从哪里开始遍历（集合就是【1.。。。n】）
     */
    private static void combineHelperOfficial(int n, int k, int startIndex) {
        //回溯要素1终止条件
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        /**
         * 回溯要素3单层遍历的过程
         */
//        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
        for (int i = startIndex; i <= n; i++) {
            path.add(i);
            combineHelperOfficial(n, k, i + 1);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        for (List<Integer> list : combineOfficial(4, 2)) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}

