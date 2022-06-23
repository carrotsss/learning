package datastructure.array.slidewindow;

/**
 * 给定一个含有n个正整数的数组合一个正整数target
 * 找出该数组中满足其和.>=target 的长度最小的连续字数组【nums1, nums1+1. ..., nusmr-1,numsr],并返回其长度，如果不存在符合条件的数组，则返回-
 * 示例：target = 7 nums=[2,3,1,2,4,3]
 * 输出：2
 * 子数组【4,3】是符合条件的子数组，长度为2
 */
public class p4_209_MinimumSizeSubarraySum_slidewindow {
    /**
     * 暴力解法：o(n2)
     *
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLenOfficial(int target, int[] nums) {
        int result = Integer.MAX_VALUE;
        int sum = 0;
        int subLength;
        for (int i = 0; i < nums.length; ) {
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    subLength = j - i;
                    result = Math.min(result, subLength);
                    break;
                }
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    /**
     * 滑动窗口:o(n)
     * 这里两个循环不是o(n2) ，主要看每个元素被操作的次数，每个元素在滑动窗口进来操作一次，出去操作一次，每个操作都被操作两次，所以复杂度是2*n
     *
     * @param target
     * @param nums
     * @return
     */
    private static int minSubArrayLengthSlideWindowOfficial(int target, int[] nums) {
        int result = Integer.MAX_VALUE;
        int sum = 0;//滑动窗口数值之和
        int i = 0;//滑动窗口起始位置
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            //注意这里使用while，每次更新i(起始位置)并不断比较子序列是否符合条件
            while (sum >= target) {
                result = Math.min(result, j - i + 1);
                sum -= nums[i++];//这里体现出滑动窗口的精髓之处，不断变更i（子序列的起始位置）
            }
        }
        //如果result没有被赋值的话，就返回0，说明没有符合条件的子序列
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        System.out.println(minSubArrayLengthSlideWindowOfficial(7, nums));
    }
}
