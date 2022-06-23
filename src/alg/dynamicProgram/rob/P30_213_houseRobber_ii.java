package alg.dynamicProgram.rob;

import java.util.function.Function;

/**
 * 打家劫舍II
 * 你是一个专业的小偷，计划盗窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你再不触动警报的情况下，能够盗窃到的最高金额
 * 输入：【2，3，2】
 * 输出：3
 * 说明：你不能先盗窃房屋1的东西然后再盗窃房屋3，因为他们是相连的，链表是一个环形
 */
public class P30_213_houseRobber_ii {
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        return Math.max(robAction(nums, 0, length - 1), robAction(nums, 0, length));
    }

    public static int robAction(int[] nums, int start, int end) {
        int x = 0, y = 0, z = 0;
        for (int i = start; i < end; i++) {
            y = z;
            z = Math.max(y, x + nums[i]);
            x = y;
        }
        return z;
    }
}
