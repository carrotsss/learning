package alg.dynamicProgram.subSequence;

/**
 * 最长连续递增序列
 * 给定一个未经铺叙的数组，找对最长且  连续的递增子序列，并返回长度
 * 输入：【1,3,5,,4,7】
 * 输出：【1,3,5】
 * 说明：最长连续递增子序列是【1,3,5】长度为3，尽管【1,3,5,7】也是升序的子序列，但是它不是连续的，因为5和7中间隔着4
 */
public class P42_674_LongestContinuousIncreasingSubsequense {
    /**
     * 1.dp[i]代表当前下标之前最大连续值
     * 2.递推公式 if(nums[i + 1] > nums[i]) dp[i +1 ] = dp[i] + 1
     * 3.初始化都为1
     * 4.遍历方向，从其那往后
     * 5.结果推导
     * @param nums
     * @return
     */
    public int findLengthOfLCISOfficial(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }
        int res = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] > nums[i]) {
                dp[i + 1] = dp[i] + 1;
            }
            res = res > dp[i + 1] ? res : dp[i + 1];
        }
        return res;
    }
}
