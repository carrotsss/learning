package datastructure.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个数组nums和一个整数目标值target，请你在该数组中找出和为目标值target的两个整数，并返回他们的数组下标
 * 你可以假设美中输入只会对应一个答案，但是，数组中同一个元素在答案里不能重复出现。
 * 输入：【2,7,11,15】 target = 9
 * 输出：【0,1】
 */
public class P5_1_TwoSum {
    public int[] twoSumSelf(int[] nums, int target) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])){
                continue;
            }
            set.add(nums[i]);
            for (int j = i; i < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static int[] twoSumOfficial(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
            return res;
        }
//        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
//            if (map.containsKey(temp)) {
            if (set.contains(temp)) {
//                res[0] = i;
                res[0] = nums[i];
                res[1] = temp;
            }
            set.add(nums[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 13};
        for (int i :
                twoSumOfficial(nums, 9)) {
            System.out.print(i);
        };
    }
}
