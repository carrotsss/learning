package alg.binaryOper;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个生序排列的整数数组nums,和一个目标值target，找出给定目标值在数组中开始位置和结束位置
 * 输入：【5，7,7,7，8,8,10】 target=8
 * 输出；[3,4]
 */
public class P6_34_FindFirstAndLastPositionOfElementInSortedArray {
    public static int[] searchRange(int[] nums, int target) {
        // O(2*logn)
        int first = findFirst(nums, target);
        int last = findFirst(nums, target + 1) - 1;
        if (first == nums.length || nums[first] != target) {
            return new int[]{-1, -1};
        } else {
            return new int[]{first, Math.max(first, last)};
        }
    }

    public static int findFirst(int[] nums, int target) {
        int l = 0, h = nums.length;
        while (l < h) {
            int m = l + (h - 1) / 2;
            if (nums[m] >= target) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
}
