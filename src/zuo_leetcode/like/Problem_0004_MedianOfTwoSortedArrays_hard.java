package zuo_leetcode.like;

/**
 * 4寻找两个正序数组中的中位数
 * 给定两个大小分别为m和n的正序（从小到大）数组nums1和nums2,。请你找出并返回这两个数组的中位数
 * 示例1：
 * 输入：nums = [1,3], nums2 = [2]
 * 输出：2.0000
 * 合并数组 = [1,2,3]，中位数 2
 */
public class Problem_0004_MedianOfTwoSortedArrays_hard {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        boolean even = (size & 1) == 0;
        if (nums1.length != 0 && nums2.length != 0) {
            if (even) {
                return (double) (findKthNum(nums1, nums2, size / 2) + findKthNum(nums1, nums2, size / 2 + 1)) / 2D;
            } else {
                return findKthNum(nums1, nums2, size / 2 + 1);
            }
        } else if (nums1.length != 0) {
            if (even) {
                return (double) (nums1[(size - 1) / 2] + nums1[size / 2]) / 2;
            } else {
                return nums1[size / 2];
            }
        } else if (nums2.length != 0) {
            if (even) {
                return (double) (nums2[(size - 1) / 2] + nums2[size / 2]) / 2;
            } else {
                return nums2[size / 2];
            }
        } else {
            return 0;
        }
    }

    public static int findKthNum(int[] arr1, int[] arr2, int kth) {
        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
        int l = longs.length;
        int s = shorts.length;
        if (kth <= s) {
            return getUpMedian(shorts, 0, kth - 1, longs, 0, kth - 1);
        }
        if (kth > l) {
            if (shorts[kth - l - 1] >= longs[l - 1]) {
                return shorts[kth - l - 1];
            }
            if (longs[kth - s - 1] >= shorts[s - 1]) {
                return longs[kth - s - 1];
            }
            return getUpMedian(shorts, kth - l, s - 1, longs, kth - s, l - 1);
        }
        if (longs[kth - s - 1] >= shorts[s - 1]) {
            return longs[kth - s - 1];
        }
        return getUpMedian(shorts, 0, s - 1, longs, kth - s, kth - 1);
    }

    public static int getUpMedian(int[] a1, int s1, int e1, int[] a2, int s2, int e2) {
        int mid1 = 0;
        int mid2 = 0;
        int offset = 0;
        while (s1 < e1) {
            mid1 = (s1 + e1) / 2;
            mid2 = (s2 + e2) / 2;
            offset = ((e1 - s1 + 1) & 1) ^ 1;
            if (a1[mid1] > a2[mid2]) {
                e1 = mid1;
                s2 = mid2 + offset;
            } else if (a1[mid1] < a2[mid2]) {
                s1 = mid1 + offset;
                e2 = mid2;
            } else {
                return a1[mid1];
            }
        }
        return Math.min(a1[s1], a2[s2]);
    }

    /**
     * official解法
     * 方法一：二分查找
     * 给定两个有序数组，要求找到两个有序数组的中位数，最直观的思路有两种：
     * 1.使用归并的方式，合并两个有序数组得到一个大的数组。大的有序数组的中间位置的元素，即为中位数。
     * 2.不需要合并两个有序数组，只要找到中位数即可。由于两个数组的长度已知。因此中位数对应的两个数组的下标之和也是已知的。
     * 维护两个指针，初始时分别指向两个数组的下表0的位置，每次将指向较小值得指针后移一位（如果一个指针已经达到数组末尾，则只需要移动领一个数组的指针），直到到达中位数的位置。
     * 假设两个有序数组的长度分别为 m 和 n，上述两种思路的复杂度如何？
     * <p>
     * 第一种思路的时间复杂度是 O(m+n)O(m+n)，空间复杂度是 O(m+n)O(m+n)。第二种思路虽然可以将空间复杂度降到 O(1)O(1)，但是时间复杂度仍是 O(m+n)O(m+n)。
     * 如何把时间复杂度降低到 O(\log(m+n))O(log(m+n)) 呢？如果对时间复杂度的要求有 \loglog，通常都需要用到二分查找，这道题也可以通过二分查找实现。
     * 根据中位数的定义，当 m+nm+n 是奇数时，中位数是两个有序数组中的第 (m+n)/2(m+n)/2 个元素，当 m+nm+n 是偶数时，中位数是两个有序数组中的第 (m+n)/2(m+n)/2 个元素和第 (m+n)/2+1 个元素的平均值。因此，这道题可以转化成寻找两个有序数组中的第 k 小的数，其中 k 为 (m+n)/2 或 (m+n)/2+1。
     * 假设两个有序数组分别是  A 和  B。要找到第 kk 个元素，我们可以比较  A[k/2−1] 和  B[k/2−1]，其中 // 表示整数除法。由于  A[k/2−1] 和  B[k/2−1] 的前面分别有  A[0..k/2−2] 和  B[0..k/2−2]，即 k/2−1 个元素，对于  A[k/2−1] 和  B[k/2−1] 中的较小值，最多只会有 (k/2−1)+(k/2−1)≤k−2 个元素比它小，那么它就不能是第 k 小的数了。
     * 因此我们可以归纳出三种情况：
     * 如果A[k/2−1]<B[k/2−1]，则比A[k/2−1] 小的数最多只有A 的前k/2−1 个数和B 的前k/2−1 个数，即比A[k/2−1] 小的数最多只有k−2 个，因此  A[k/2−1] 不可能是第 k 个数， A[0] 到 A[k/2−1] 也都不可能是第 k个数，可以全部排除。
     * 如果A[k/2−1]>B[k/2−1]，则可以排除B[0] 到B[k/2−1]。
     * 如果A[k/2−1]=B[k/2−1]，则可以归入第一种情况处理。
     * <p>
     * 可以看到，比较  A[k/2−1] 和  B[k/2−1] 之后，可以排除 k/2 个不可能是第 k 小的数，查找范围缩小了一半。同时，我们将在排除后的新数组上继续进行二分查找，并且根据我们排除数的个数，减少 k 的值，这是因为我们排除的数都不大于第 k 小的数。
     * 有以下三种情况需要特殊处理：
     * 如果  A[k/2−1] 或者  B[k/2−1] 越界，那么我们可以选取对应数组中的最后一个元素。在这种情况下，我们必须根据排除数的个数减少 k 的值，而不能直接将 k 减去 k/2。
     * 如果一个数组为空，说明该数组中的所有元素都被排除，我们可以直接返回另一个数组中第 k 小的元素。
     * 如果 k=1，我们只要返回两个数组首元素的最小值即可。
     * 用一个例子说明上述算法。假设两个有序数组如下：
     * A: 1 3 4 9
     * B: 1 2 3 4 5 6 7 8 9
     * 两个有序数组的长度分别是 4 和 9，长度之和是 13，中位数是两个有序数组中的第 7 个元素，因此需要找到第 k=7 个元素。
     * 比较两个有序数组中下标为 k/2−1=2 的数，即  A[2] 和  B[2]，如下面所示：
     * A: 1 3 4 9
     *        ↑
     * B: 1 2 3 4 5 6 7 8 9
     *        ↑
     * 由于  A[2]>B[2]，因此排除  B[0] 到  B[2]，即数组  B 的下标偏移（offset）变为 3，同时更新 kk 的值：k=k−k/2=4。
     * 下一步寻找，比较两个有序数组中下标为 k/2−1=1 的数，即  A[1] 和  B[4]，如下面所示，其中方括号部分表示已经被排除的数。
     * A: 1 3 4 9
     *        ↑
     * B: [1 2 3] 4 5 6 7 8 9
     *            ↑
     * 由于A[1]<B[4]，因此排除A[0] 到A[1]，即数组  A 的下标偏移变为 2，同时更新 k 的值：k=k−k/2=2。
     * 下一步寻找，比较两个有序数组中下标为 k/2−1=0 的数，即比较A[2] 和B[3]，如下面所示，其中方括号部分表示已经被排除的数。
     * A: [1 3] 4 9
     *          ↑
     * B: [1 2 3] 4 5 6 7 8 9
     *            ↑
     * 由于A[2]=B[3]，根据之前的规则，排除  A 中的元素，因此排除A[2]，即数组  A 的下标偏移变为 3，同时更新 k 的值：k=k−k/2=1。
     * 由于k 的值变成 1，因此比较两个有序数组中的未排除下标范围内的第一个数，其中较小的数即为第k 个数，由于A[3]>B[3]，因此第 k 个数是B[3]=4。
     * A: [1 3 4] 9
     *            ↑
     * B: [1 2 3] 4 5 6 7 8 9
     *            ↑
     * 复杂度分析
     * 时间复杂度：O(log(m+n))，其中 m 和 n 分别是数组nums1和nums2的长度。初始时有 k=(m+n)/2 或 k=(m+n)/2+1，每一轮循环可以将查找范围减少一半，因此时间复杂度是 O(log(m+n))。
     * 空间复杂度：O(1)。
     */
    public static double findMedianSortedArraysOfficial(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totallength = length1 + length2;
        if (totallength % 2 == 1) {
            int midIndex = totallength / 2;
            double median = getKthElementOfficial(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = totallength / 2 - 1, midIndex2 = totallength / 2;
            double median = (getKthElementOfficial(nums1, nums2, midIndex1 + 1) + getKthElementOfficial(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    public static int getKthElementOfficial(int[] nums1, int[] nums2, int k) {
        /**
         * 主要思路：
         * 要找到第k（k>1)小的元素，那么就取pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1]进行比较
         * nums1中小于等于pivot1的元素有nums1[0...k/2-2]共计k/2-1个
         * nums2中小于等于pivot2的元素有nums2[0...k/2-2]共计k/2-1个
         * 取pivot = min(pivot1, pivot2),两个数组中小于等于pivot的元素共计不会超过（k/2-1) + (k/2-1) <= k-2个
         * 这样pivot本身最大也可能是第k-1小的元素
         * 如果pivot = pivot1，那么nums1[0...k/2-1]都不可能是第k小的元素。把这些元素全部"删除"，剩下的作为新的nums1数组
         * 如果pivot = pivot2，那么nums2[0...k/2-1]都不可能是第k小的元素。把这些元素全部"删除"，剩下的作为新的nums2数组
         * 由于我们“删除”了一些元素（这些元素都比第k小的元素要小），因此需要修改k的值，减去删除的数的个数。
         */
        int length1 = nums1.length, length2 = nums2.length;
        int kthElement = 0;
        int index1 = 0, index2 = 0;
        while (true) {
            //边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            //正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index2 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }

    /**
     * 自己的解法
     */
    public static int findMedianOfTwoSortedArraySelf(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length + nums2.length];
        nums = contactSelf(nums1, nums2);
        int length = nums.length;
        int middle = length / 2;
        quickSortSelf(nums);
        int result = nums[middle];
        return result;
    }

    static void quickSortSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        sort(nums, 0, nums.length - 1);
    }

    private static void sort(int[] nums, int start, int end) {
        int par = patictionSelf(nums, start, end);
        if (par > start + 1) {
            //排序左边
            sort(nums, start, par - 1);
        }
        if (par < end - 1) {
            //排序右边
            sort(nums, par + 1, end);
        }
    }

    /**
     * 一趟快拍：确定一个基准，把小雨基准的数据移到前面去，大于基准的移到后面，确定基准的位置
     */
    static int patictionSelf(int[] nums, int low, int high) {
        //以第一个为基准，基准值赋值给temp
        int temp = nums[low];
        while (low < high) {
            //从后往前，大于基准的数据不管
            while (low < high && nums[high] >= temp) {
                --high;
            }
            /**
             * 出了上面的循环，high指针肯定指向它找到的后面的第一个相遇基准的数据
             * 把该数据移到前面
             */
            nums[low] = nums[high];
            //从前往后，小于基准的不管，大于基准的移动到后面去
            while (low < high && nums[low] <= temp) {
                ++low;
            }
            /**
             * 出了上面的循环，low指针肯定指向它找到的前面的一个大于基准的数据
             * 把该数据移动到后面
             */
            nums[high] = nums[low];
        }
        /**
         * 最后出来，low和high指针在同一位置，都指向基准应该放置的位置
         * 小于他的都在前面，大于他的都在后面
         */
        nums[low] = temp;
        /**
         * 把基准最后的位置返回出去，基准的位置已经确定，即基准已经有序
         * 后面分别在这个基准的位置两边重复上面的操作，都以排序当前基准两边的数据
         */
        return low;
    }

    /**
     * 连接两个数组这他妈的极其复杂啊，先连接两个数组然后排序，复杂度O(n) + O()
     *
     * @param var1
     * @param var2
     * @return
     */
    public static int[] contactSelf(int[] var1, int[] var2) {
        int[] result = new int[var1.length + var2.length];
        for (int i = 0; i < result.length; i++) {
            if (i < var1.length) {
                result[i] = var1[i];
            } else {
                result[i] = var2[i - var1.length];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 3, 4, 5, 7};
        int[] nums2 = new int[]{1, 3, 4, 6, 9, 12, 14, 32};
        int[] nums = contactSelf(nums1, nums2);
        for (int n : nums) {
            System.out.print(n + " ");
        }
        System.out.println();
        quickSortSelf(nums);
        for (int n : nums) {
            System.out.print(n + " ");
        }
        System.out.println();
        System.out.println(findMedianOfTwoSortedArraySelf(nums1, nums2));
        System.out.println(findMedianSortedArrays(nums1, nums2));
        System.out.println(findMedianSortedArraysOfficial(nums1, nums2));
    }
}
