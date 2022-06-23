package alg.dynamicProgram.bag;

/**
 * 给你一个值包含正整数的非空数组nums，请你判断是否可以将这个数组分隔成两个子集，使得两个子集的元素和相等。
 * 输入：nums=[1,5,11,5]
 * 输出：true
 * 输足可以分割成[1,5,5] 和 [11]
 */
public class P13_416_PartitionEqualSubsetSum {
    public boolean canPartiton(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        //总和为奇数，不能评分
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;//一定是二分
        //1、确定数组及下标含义，dp[j],背包总容量是j，最大可以凑成的自己总和为dp[j]
        int[] dp = new int[target + 1];
        //2、初始化
        dp[0] = 0;
        //4、确定遍历顺序
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                //2、确定递推公式
                //物品i的重量是nums【i]，其价值也是nums【i]
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        //5、确定返回值
        return dp[target] == target;
    }
}
