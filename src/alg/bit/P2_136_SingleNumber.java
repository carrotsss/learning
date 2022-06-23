package alg.bit;

import zuo_leetcode.interview.followup.IndexTreeTest;

/**
 * 只出现一次的数字
 * 给定一个非空整数数组，除了某个元素之出现一次意外，其余每个元素均出现两次。找出那个只出现了一次的元素
 * 输入：【2,2,1】
 * 输出：1
 */
public class P2_136_SingleNumber {
    /**
     * 两个相同的数异或的结果为0，对所有数进行异或操作，最后结果就是单独出现的那个数。
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int ret = 0;
        for (int num : nums) {
            ret ^= num;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{2, 2, 1,1,3,4,4}));
    }
}
