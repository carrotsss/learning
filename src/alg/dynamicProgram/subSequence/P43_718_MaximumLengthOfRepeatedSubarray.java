package alg.dynamicProgram.subSequence;

/**
 * 最长重复序列
 * 给两个素组A和B，返回两个数组中公共的长度最长的子序列
 * 输入：nums1 = 【1,2,3,2,1】 nums2 = 【3,2,1，4,7】
 * 输出：3
 * 说明：长度最长的公共子序列数组是【3.2.1】
 */
public class P43_718_MaximumLengthOfRepeatedSubarray {
    /**
     * 1.dp[i][j] 表示以i为结尾的A和以j为结尾的B，最长公共子序列长度
     * 2.递推公式dp[i][j] = dp[i-1][j-1] + 1
     * 3.初始化dp【i][0] = 0 ,dp[0][j] = 0
     * 4.遍历顺序两个数组双层遍历
     * 5.结论
     * @param nums1
     * @param nums2
     * @return
     */
    public static int findLengthOfficial(int[] nums1, int[] nums2) {
        int result = 0;
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i < nums1.length + 1; i++) {
            for (int j = 1; j < nums2.length + 1; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    result = Math.max(result, dp[i][j]);
                }
            }
        }
        return result;
    }

    public int findLengthSelf(int[] nums1, int[] nums2) {
        int result = 0;
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i < nums1.length + 1; i++) {
            for (int j = 1; j < nums2.length + 1; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    result = Math.max(result, dp[i][j]);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 2, 1}, nums2 = {3, 2, 1, 4, 7};
        System.out.println(findLengthOfficial(nums1, nums2));
    }
}
