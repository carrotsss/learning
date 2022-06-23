package alg.dynamicProgram.subSequence;

import java.util.Arrays;

/**
 * 最长递增子序列
 * 给你一个整数数组nums，找到其中最长严格递增子序列的长度
 * 子序列是有数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序，例如【3,6,2,7,】是数组【0,3,1,6,2,2,7】的子序列
 * 输入：【10,9,2,5,3,7,101,18】
 * 输出：4
 * 解释：【2,3,7,101】
 */
public class P41_300_LongestIncreasingSubSequence {
    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                //要求升序，升序则加一
                if (nums[i] > nums[j]) {
                    //位置i的最长升序子序列等于j从0到i-1各个位置的最长升序子序列+1的最大值
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));
    }
}
