package datastructure.hash;

import java.util.HashSet;

/**
 * 两个数组的交集
 * 给定两个数组，编写一个函数来计算他们的交集
 * 输入：nums1 = 【1,2,2,1】，nams2 = 【2,2,】
 * 输出：【2】
 * 【9,2,4,4】 【9,2,5,6,4】
 */
public class P3_349_IntersectionOfTwoArrays {
    public static int[] intersectionOfficial(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> resSet = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }
        for (int i : nums2) {
            if (set1.contains(i)) {
                resSet.add(i);
            }
        }
        int[] resArr = new int[resSet.size()];
        int index = 0;
        for (Integer integer : resSet) {
            resArr[index++] = integer;
        }
        return resArr;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{9, 2, 3, 5, 4};
        int[] nums2 = new int[]{9, 4};
        for (int i : intersectionOfficial(nums1, nums2)) {
            System.out.print(i);
        }
    }
}
