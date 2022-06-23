package datastructure.array.doublepointer;

import java.util.Arrays;

/**
 *
 * 给一个按费递减顺序排序的整数数组，nums，返回每个数字的平方组成的新数组，要求也按非递减顺序排序
 * 输入：nums = [-4,-1, 0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组为【16,1,0,9,100】
 * 排序后，数组变为【0，1,9,16,100】
 */
public class P3_977_SquaresOfSortedArray_dubblePointer {
    /**
     * 暴力排序 o(nlogn)
     * @param nums
     * @return
     */
    private static int[] sortedSquaresOfficial(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    /**
     * 双指针法 o(n)
     */
    private static int[] sortedSquaresDoublePointerOfficial(int[] nums) {
        int k = nums.length - 1;
        int[] result = new int[nums.length];
        for (int i = 0, j = k; i <= j; ) {
            if (nums[i] * nums[i] < nums[j] * nums[j]) {
                result[k--] = nums[j] * nums[j];
                j--;
            }else{
                result[k--] = nums[i] * nums[i];
                i++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-4, -1, 0, 3, 10};
        ;
        for (int i:
        sortedSquaresDoublePointerOfficial(nums)) {
            System.out.println(i);
        }
    }
}
