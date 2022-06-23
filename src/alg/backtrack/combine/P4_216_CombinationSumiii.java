package alg.backtrack.combine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合求和
 * 找出所有相加之和为n的k个数的组合。组合中只允许含有1-9的正整数，并且每种组合中不存在重复的数字
 * 输入：k = 3  n = 7
 * 输出：【【1,2,4】】
 *
 * k = 3 n = 9
 * 【【1,2,6,】，【1,3,5】，【2,3,4】】
 *
 * k = 2 n = 7
 * 1-9
 * 1 2 3 4 5 6 7 8 9
 * 12 13 14 15 [16] 17 18 19 23 24 [25] 26 27 28 29 [34] 35 36 37 38 39 45 46 47 48 49 56 57 58 59 67 68 69 78 79 89
 */
public class P4_216_CombinationSumiii {
    static List<List<Integer>> result = new ArrayList<>();
    static LinkedList<Integer> path = new LinkedList<>();

    public static List<List<Integer>> combinationSum3Official(int k, int n) {
        backTrackingOfficial(n, k, 1, 0);
        return result;
    }

    private static void backTrackingOfficial(int targetSum, int k, int startIndex, int sum) {
        //递归结束条件
        if (sum > targetSum) {
            return;
        }
        //递归结束条件
        if (path.size() == k) {
            if (sum == targetSum) {
                result.add(new ArrayList<>(path));
            }
            return;
        }
        //回溯搜索的循环遍历过程
        //剪枝 9 - （k-path.size（））+1
        for (int i = startIndex; i <= 9; i++) {
            path.add(i);
            sum += i;
            backTrackingOfficial(targetSum, k, i + 1, sum);
            //回溯
            path.removeLast();
            //回溯
            sum -= i;
        }
    }

    //k 几个数，n 和是多少
    public static List<List<Integer>> combineSumSelf(int k, int n) {
        combineSumRecurSelf(n, 0, 1, k);
        return result;
    }

    public static void combineSumRecurSelf(int targetSum, int currentSum, int startIndex, int k) {
        if (currentSum > targetSum) {
            return;
        }
        if (path.size() == k) {
            if (targetSum == currentSum) {
                result.add(path);
            }
            return;
        }
        for(int i = startIndex; i < 9 - (k - path.size()) + 1; i++) {
            currentSum += i;
            path.add(i);
            combineSumRecurSelf(targetSum, currentSum, startIndex, k);
            path.removeLast();
            currentSum -= i;
        }
    }

    public static void main(String[] args) {
        for (List<Integer> list : combinationSum3Official(3, 9)) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
