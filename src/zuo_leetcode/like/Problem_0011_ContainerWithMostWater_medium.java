package zuo_leetcode.like;

/**
 * 给你n个非负整数，a1,a2,...,an，每个数代表坐标中的一个点（i,ai)。在坐标内画n条垂直线，垂直线i的两个端点分别为（i,ai)和（i,0)。
 * 找出其中的两条线，使得他们与x轴共同构成的容器可以容纳最多的水。
 * 说明：不能倾斜容器
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * <p>
 * 输入：[1,1]
 * 输出：1
 * <p>
 * 输入：[4,3,2,1,4]
 * 输出：16
 * <p>
 * 输入：[1,2,1]
 * 输出：2
 */
public class Problem_0011_ContainerWithMostWater_medium {

    public static int maxArea(int[] h) {
        int max = 0;
        int l = 0;
        int r = h.length - 1;
        while (l < r) {
            max = Math.max(max, Math.min(h[l], h[r]) * (r - l));
            if (h[l] > h[r]) {
                r--;
            } else {
                l++;
            }
        }
        return max;
    }

    /**
     * 双指针
     * 双指针代表的是可以作为容器边界的所有位置的范围。在一开始，双指针指向数组边界的两端，数组中所有位置都可以作为容器的边界。
     * 我们每次将对应的数字最小的的那个指针，往另一个指针的方向移动一个位置，就表示我们认为这个指针不可能作为容器的边界了。
     * @param ints
     * @return
     */
    public static int maxAreaOfficial(int[] ints) {
        int max = 0;
        int left = 0;
        int right = ints.length - 1;
        while (left < right) {
            int a = Math.min(ints[left], ints[right]) * (right - left);
            max = Math.max(a, max);
            if (ints[left] < ints[right]) {
                right--;
            } else {
                left++;
            }
        }
        return max;
    }

    public static int maxAreaSelf(int[] ints) {
        int max = 0;
        int left = 0;
        int right = ints.length - 1;
        while (left < right) {
            max = Math.max(max, Math.min(ints[left], ints[right]) * (right - left));
            if (ints[left] > ints[right]) {
                right--;
            } else {
                left++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxAreaSelf(new int[]{4, 2, 1, 6, 3, 1, 5}));
    }
}
