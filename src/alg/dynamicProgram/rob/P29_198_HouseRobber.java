package alg.dynamicProgram.rob;

/**
 * 打家劫舍
 * 你死一个专业的小偷，计划偷窃沿街的房屋，每间房间内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果相邻的房屋装有相互联通的防盗系统，如果两间相邻的房屋在统一晚上被小偷闯入，系统会自动报警
 * 给定一个嗲表每个房屋存放金额的非负整数数组，计算你不触动报警装置的情况下，一夜之间能够盗窃的最高金额
 * 输入：【1，2，3，1】
 * 输出：4
 * 说明：盗窃房屋1和房屋3 合计4
 */
public class P29_198_HouseRobber {
    public static int rob(int[] nums) {
        //1、确定dp数组及下标含义
        // dp[i] 房间i之前可盗窃的最多价值
        int[] dp = new int[nums.length];
        //2、确定递推公式
        // dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1])
        //3、初始化dp数组
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], nums[0]);
        //确定遍历顺序
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println(rob(nums));
    }
}
