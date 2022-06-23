package datastructure.array.doublepointer;

/**
 * 给定一个n个元素有序的（升序）整形数组nums和一个目标值target，写一个函数搜索nums中的target，如果目标存在返回下标，否则返回-1
 * 输入：nums = [-1, 0, 3, 5, 9 ,12] target = 9
 * 输出：4
 */
public class P1_704_BinarySearch_dubblePointer {
    public static int binarySearchOfficial(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (right > left) {
            int middle = left + (right - left) / 2;
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    public static int binarySearchOfficial2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] > target) {
                right = middle;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
