package datastructure.hash;

import java.util.HashMap;

/**
 * 四数之和2
 * 给你四个整数数组nums1，nums2，nums3，nums4，数组长度是n，请你计算有多少个元祖（i,j,k,l)能满足：
 * 1、0<=i,j,k,l<n
 * 2、nums1[i] + nums2[j] + nums3[k]+ nusm4[l] = 0
 * 输入：nums1【1,2】 nums2【-2.-1】 nums3【-1.2】 nums4 【0,2】
 * 输出：2
 * 【0001】 1 + -2 + -1 + 2  【1100】2 + -1 + -1 + 0
 */
public class P6_454_FourSum_ii {
    /**
     * o(n2 + n2）
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    public static int fourSumCountOfficial(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int temp = 0;
        int res = 0;
        //统计两个数组中的元素之和，同时统计出现的次数，放入map
        for (int i : nums1) {
            for (int j : nums2) {
                temp = i + j;
                if (map.containsKey(temp)) {
                    //将nums1及nums2的每个和出现的次数放入map，同一个和结果可以出现多次
                    map.put(temp, map.get(temp) + 1);
                } else {
                    map.put(temp, 1);
                }
            }
        }
        //统计剩余的两个元素的和，在map中找是否存在相加为0的情况，同时记录次数
        for (int i : nums3) {
            for (int j : nums4) {
                temp = i + j;
                if (map.containsKey(0 - temp)) {
                    res += map.get(0 - temp);
                }
            }
        }
        return res;
    }

    public static int fourSumCountSelf(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int temp = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for (int i : nums1) {
            for (int j : nums2) {
                temp = i + j;
                if (map.containsKey(temp)) {
                    map.put(temp, map.get(temp) + 1);
                } else {
                    map.put(temp, 1);
                }
            }
        }
        for (int i : nums3) {
            for (int j : nums4) {
                temp = i + j;
                if (map.containsKey(0 - temp)) {
                    result += map.get(temp);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
