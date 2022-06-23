package alg.doublePointer.hash;

/**
 * 两数之和2 - 输入有序数组
 * 给定一个按照非递减顺序排列的整数数组numbers，请你从数组中找出两个数满足相加之和等于目标数target
 * 函数应该以长度为2的整数数组形式返回这两个数的下标值，numbers的下标从1开始计数，所以答案数组应当满足1<=answer[0]<=answer[1]<=numbers.length
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素
 * <p>
 * 示例：输入：numbers = [2,7,11,15] target = 9
 * 输出：[1,2]
 */
public class P11_167_TwoSumiiInputArrayIsSorted {
    public static int[] twoSumOfficial(int[] nums, int target) {
        int i = 0, j = nums.length - 1;

        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == target) {
                return new int[]{i, j};
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nusm = new int[]{2, 7, 8, 12};
        for (int i : twoSumOfficial(nusm, 9)) {
            System.out.println(i);
        }
    }
}
