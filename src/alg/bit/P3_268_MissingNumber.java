package alg.bit;

/**
 * 找出数组中缺失的那个数
 * 给定一个包含【0.n]中n个数的数组，nums，找出【0.n]这个范围内没有出现在数组中的那个数
 * 输入：nums = 【3，0,1】
 * 输出：2
 * 说明：n=3,因为有3个数字，所以所有的数字都在范围【0,3】内。2是丢失数字。
 */
public class P3_268_MissingNumber {
    public static int missingNumbers(int[] nums) {
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            ret = ret ^ i ^ nums[i];
    }
        return ret ^ nums.length;
    }
}
