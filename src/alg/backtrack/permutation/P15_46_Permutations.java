package alg.backtrack.permutation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个不含重复的数组nums，返回器所有可能的全排列。你可以按任意顺序返回答案。
 * 输入 nusm = 【1,2,3】
 * 输出 【【1,2,3,】，【1,3,2】，[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class P15_46_Permutations {
    static List<List<Integer>> resutl = new ArrayList<>();
    static LinkedList<Integer> path = new LinkedList<>();
    static boolean[] used;

    static public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
            return resutl;
        }
        used = new boolean[nums.length];
        permuteHelper(nums);
        return resutl;
    }

    static private void permuteHelper(int[] nums) {
        if (path.size() == nums.length) {
            resutl.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            permuteHelper(nums);
            path.removeLast();
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        for (List<Integer> list : permute(nums)) {
            for (int i :
                    list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
