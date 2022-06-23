package datastructure.array.doublepointer;

/**
 * 移除元素
 * 给你一个数组nums和一个值val，你需要原地移除所有数值等于val的元素并返回移除后数组的新长度。
 * <p>不要使用额外的输足空间，你必须仅使用o(1)额外空间，并原地修改输入数组
 * 元素的顺序可以改变，你不需要考虑数组中超出新长度后面的元素
 * 说明：
 * 为什么返回数值是整数，但是输出的答案是数组呢
 * <p>
 * 示例1：
 * 输入：nums = [3,2,2,3] , val = 3
 * 输出：2 ，nums = [2,2]
 */
public class P2_27_RemoveElement_dubblePointer {
    /**
     * 暴力解法: o(n2)依次比较每一个元素后面有没有重复元素
     * @param nums
     * @param val
     * @return
     */
    public static int removeElementOfficial(int[] nums, int val) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if (nums[i] == val) {
                for (int j = i + 1; j < size; j++) {
                    nums[j - 1] = nums[j];
                }
                i--;
                size--;
            }
        }
        return size;
    }

    /**
     * 双指针法：
     * @param args
     */
    private static int removeElementDubblePointerOfficial(int[] nums, int val) {
        int showIndex = 0;
        for(int fastIndex = 0; fastIndex < nums.length; fastIndex++) {//循环是先加后执行
            if (val != nums[fastIndex]) {
                nums[showIndex++] = nums[fastIndex];
            }
        }
        return showIndex;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1,4,6,2, 3,5};
//        System.out.println(removeElementOfficial(nums, 2));
        System.out.println(removeElementDubblePointerOfficial(nums, 2));
        for (int i : nums) {
            System.out.print(i);
        }
    }
}
