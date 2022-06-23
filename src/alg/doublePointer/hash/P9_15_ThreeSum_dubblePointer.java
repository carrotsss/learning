package alg.doublePointer.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 给你一个包含n个证书的数组nums，判断nums中是否存在三个元素啊，吧，a,b,c,是的a+b+c= 0？请你找出所有和为0,且不重复的三元组
 * 注：答案中不存在重复的三元组
 * 输入:nums = [-1, 0 ,1,2,-1,-4]
 * 输出：【【-1，-1,2,】，【-1,0,1】】
 */
public class P9_15_ThreeSum_dubblePointer {

    /**
     * hash法
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSumHashOfficial(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {//三元组元素a去重
                continue;
            }
            HashSet<Integer> set = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 2 && nums[j] == nums[j - 1] && nums[j - 1] == nums[j - 2]) {//三元组元素b去重
                    continue;
                }
                int c = 0 - (nums[i] + nums[j]);
                if (set.contains(c)) {
                    List list = new ArrayList();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(c);
                    result.add(list);
                    set.remove(c);//三元组元素c去重
                } else {
                    set.add(nums[j]);
                }
            }
        }
        return result;
    }

    /**
     * 双指针法
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSumDoublePointerOfficial(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return result;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (right > left) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (right > left && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    while (right > left && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    right--;
                    left++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        for (List<Integer> list :
                threeSumDoublePointerOfficial(nums)) {
            for (int i :
                    list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        ;
    }
}
