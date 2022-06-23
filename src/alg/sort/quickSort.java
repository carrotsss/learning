package alg.sort;

public class quickSort {
    static void quickSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        sort(nums, 0, nums.length - 1);
    }

    private static void sort(int[] nums, int start, int end) {
        int par = patiction(nums, start, end);
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
    static int patiction(int[] nums, int low, int high) {
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

}
