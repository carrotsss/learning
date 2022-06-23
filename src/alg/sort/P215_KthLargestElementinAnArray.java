package alg.sort;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 返回数组第k大的数据
 */
public class P215_KthLargestElementinAnArray {
    /**
     * 时间复杂度O(nlogn) 空间复杂度O(1)
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargestSortOfficial(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * 时间复杂度O(nlogk) 空间复杂度O(k)
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargestHeapOfficial(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int num : nums) {
            priorityQueue.add(num);//小顶堆
            if (priorityQueue.size() > k) {//维护堆的大小为k
                priorityQueue.poll();
            }
        }
        return priorityQueue.peek();
    }

    /**
     * 时间复杂度O(n) 空间复杂度O(1)
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargestQuickSortOfficial(int[] nums, int k) {
        k = nums.length - k;
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int j = partition(nums, 1, h);
            if (j == k) {
                break;
            } else if (j < k) {
                l = j + 1;
            } else {
                h = j - 1;
            }
        }
        return nums[k];
    }

    public static int partition(int[] a, int l, int h) {
        int i = l, j = h + 1;
        while (true) {
            while (a[++i] < a[l] && i < h) {
            }
            while (a[--j] > a[l] && j > l) {
            }
            if (i >= j) {
                break;
            }
            swap(a, l, j);
        }
        swap(a, l, j);
        return j;
    }

    public static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
