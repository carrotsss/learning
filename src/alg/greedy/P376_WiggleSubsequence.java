package alg.greedy;

/**
 * 最长摆动子序列
 * 如果连续数字之间的差严格的在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者两个不等元素的序列也称为摆动序列
 * 例如【1,7,4,9,2,5】是一个摆动序列，因为差值【6，-3,5，-7,3】是正负交替的
 * 子序列可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
 * 给你一个而数组nums,返回nums中作为摆动序列的最长子序列的长度
 */
public class P376_WiggleSubsequence {
    public static int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums.length;
        }
        //当前差值
        int curDiff = 0;
        //上一个差值
        int preDiff = 0;
        int count = 1;
        for (int i = 0; i < nums.length; i++) {
            //得到当前差值
            curDiff = nums[i + 1] - nums[i];
            //如果当前差值和上一个差值为一正一负
            //等于0的情况表示初始的preDiff
            if ((curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)) {
                count++;//长度
                preDiff = curDiff;
            }
        }
        return count;
    }
}
