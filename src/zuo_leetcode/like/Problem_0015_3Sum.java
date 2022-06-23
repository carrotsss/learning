package zuo_leetcode.like;

import java.util.*;

/**
 * 给你一个包含n个整数的数组nums，判断nums中是否存在三个元素s,b,c,是的a+b+c =0?请找出所有和为0且不重复的三元组。
 * 注意：不能包含重复的三元组
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 */
public class Problem_0015_3Sum {

    public static List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                List<List<Integer>> nexts = twoSum1(nums, i + 1, -nums[i]);
                for (List<Integer> cur : nexts) {
                    cur.add(0, nums[i]);
                    ans.add(cur);
                }
            }
        }
        return ans;
    }

    public static List<List<Integer>> twoSum1(int[] nums, int begin, int target) {
        int L = begin;
        int R = nums.length - 1;
        List<List<Integer>> ans = new ArrayList<>();
        while (L < R) {
            if (nums[L] + nums[R] > target) {
                R--;
            } else if (nums[L] + nums[R] < target) {
                L++;
            } else {
                if (L == begin || nums[L - 1] != nums[L]) {
                    List<Integer> cur = new ArrayList<>();
                    cur.add(nums[L]);
                    cur.add(nums[R]);
                    ans.add(cur);
                }
                L++;
            }
        }
        return ans;
    }

    public static List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        int N = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = N - 1; i > 1; i--) {
            if (i == N - 1 || nums[i] != nums[i + 1]) {
                List<List<Integer>> nexts = twoSum2(nums, i - 1, -nums[i]);
                for (List<Integer> cur : nexts) {
                    cur.add(nums[i]);
                    ans.add(cur);
                }
            }
        }
        return ans;
    }

    public static List<List<Integer>> twoSum2(int[] nums, int end, int target) {
        int L = 0;
        int R = end;
        List<List<Integer>> ans = new ArrayList<>();
        while (L < R) {
            if (nums[L] + nums[R] > target) {
                R--;
            } else if (nums[L] + nums[R] < target) {
                L++;
            } else {
                if (L == 0 || nums[L - 1] != nums[L]) {
                    List<Integer> cur = new ArrayList<>();
                    cur.add(nums[L]);
                    cur.add(nums[R]);
                    ans.add(cur);
                }
                L++;
            }
        }
        return ans;
    }

    /**
     * 暴力解法：三重循环判断o(n3)
     * 双指针法：
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSumOfficial(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        //枚举a
        for (int first = 0; first < n; ++first) {
            //需要和上一次的枚举数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            //c对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            //枚举b
            for (int second = first; second < n; ++second) {
                //需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                //需要保证b的指针在c指针的左侧
                while (second < first + 1 && nums[second] + nums[third] > target) {
                    --third;
                }
                //如果指针重合，随着b后续的增加
                //就不会有满足a+b+c=0并且b<c的c了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    /**
     * 哈希法
     * 时间复杂度：O(n2)
     *
     * @return
     */
    public List<List<Integer>> threeSumCarlOfficial(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        //找出a + b + c
        //a = nums[i], b = nums[i], c = -(a + b)
        for (int i = 0; i < nums.length; i++) {
            //排序之后如果第一个元素已经大于零，那么不可能凑成三元组
            if (nums[i] > 0) {
                continue;
            }
            //三元组a元素去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            Set<Integer> set = new HashSet<Integer>();
            for (int j = i + 1; j < nums.length; j++) {
                //三元组元素b去重
//                if (j < i + 2 && nums[j] == nums[j - 1] && nums[j - 1] == nums[j - 2]) {
                if (j < i + 2 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int c = 0 - (nums[i] + nums[j]);
                //三元组c去重
                if (set.contains(c)) {//set.find(c) != set.end()
                    result.add(Arrays.asList(nums[i], nums[j], c));
                    set.remove(c);//set.erase(c)
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
    public static List<List<Integer>> threeSumCarlOfficial2(int[] nums) {
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
        int[] nums = new int[]{-1, 0, 1, 2, 2, -1, -4};
        for (List<Integer> integers : threeSumCarlOfficial2(nums)) {
            for (Integer i : integers) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
