package zuo_leetcode.like;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * 给定一个证书数组nums和一个整数目标值target，请你在该数组中找出和为目标值target的那两个整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案，但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 *
 * 输入：怒没事= [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为nums[0] + nums[1] == 9, 返回[0,1]
 *
 */
public class Problem_0001_TwoSum_simple {

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    public static int[] towSumSelf(int[] num, int target) {
        for (int i = 0; i < num.length; i++) {
            for (int j = num.length - i; j < num.length; j++) {
                if ((num[i] + num[j]) == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static int[] twoSumSelf2(int[] num, int target) {
        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
        for (int i = 0; i < num.length; i++) {
            if (hashtable.contains(target - num[i])) {
                return new int[]{i, hashtable.get(target - num[i])};
            }
            hashtable.put(num[i], i);
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] num = {2, 3, 1, 3, 2, 4, 5, 2, 1, 5};
        int target = 4;
        twoSumSelf2(num, target);
        System.out.println(String.valueOf(twoSum(num, target)));
    }
}
