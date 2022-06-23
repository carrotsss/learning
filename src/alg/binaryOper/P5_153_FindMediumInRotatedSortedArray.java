package alg.binaryOper;

/**
 * 旋转数组的最小数字
 * 已知一个长度为n的数组，预先按照生序排列，经由l到n次旋转后，得到输入数组。例如，nums = 【0,1,2,4，5,6,7】，在变化后可能得到：
 * 若旋转4次，则可以得到【4,5,6,7，0,1,2】
 * 输入：【4,5,6,7，0,1,2】
 * 输出：0
 */
public class P5_153_FindMediumInRotatedSortedArray {
    public static int findMin(int[] nums) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - 1) / 2;
            if (nums[m] <= nums[h]) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return nums[l];
    }
}
