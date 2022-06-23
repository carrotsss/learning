package alg.backtrack.subset;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个整数数组nums,数组中的元素互不相同，返回该诉诸所有可能的子集（幂集）
 * 解集不能包含重复的子集，你可以按任意顺序返回解集。
 * 123
 * 1 2 3
 * 12 13 23
 * 123
 */
public class P11_78_Subsets {
    static List<List<Integer>> result = new ArrayList<>();
    static LinkedList<Integer> path = new LinkedList<>();
    public static List<List<Integer>> subsetsOfficial(int[] nums) {
        if (nums.length == 0) {
            result.add(new ArrayList<>());
            return result;
        }
        subsetHelperOfficial(nums, 0);
        return result;
    }

    private static void subsetHelperOfficial(int[] nums, int startIndex) {
        result.add(new ArrayList<>(path));
        if (startIndex >= nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            subsetHelperOfficial(nums, i + 1);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        for (List<Integer> list :
        subsetsOfficial(nums)) {
            for (int i :
                    list) {
                System.out.print(i + " ");
            }
            System.out.println();
        };
    }
}
