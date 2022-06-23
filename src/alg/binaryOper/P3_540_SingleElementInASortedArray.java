package alg.binaryOper;

/**
 * 有序数组的singleElement
 * 一个有序数组只有一个数不出现两次，找出这个数
 * 输入：[1,1,2,3,3,4,4,4]
 * 输出：2
 */
public class P3_540_SingleElementInASortedArray {
    public static int singleNonDuplicate(int[] nums) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - 1) / 2;
            if (m % 2 == 1) {
                m--;
            }
            if (nums[m] == nums[m + 1]) {
                l = m + 2;
            } else {
                h = m;
            }
        }
        return nums[l];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2, 3, 3, 4, 4};
        System.out.println(singleNonDuplicate(nums));;
    }
}

