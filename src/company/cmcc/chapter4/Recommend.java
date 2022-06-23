package company.cmcc.chapter4;

import java.util.*;

import static java.lang.Math.abs;
import static java.lang.Math.min;

/**
 * leetcode 1755
 * 输入一个降序不重复正整数可选数字集和一个目标总数，必为正整数，输出数字集合的子集，使其最接近目标总数。
 *           例：输入数字集合，[10,9,8,4,3,1]，
 * 		      假设输入总数，20，则输出推荐数字组合[10,9,1]，
 * 			  假设输入总数，16，则输出推荐数字组合[10,4,1]，[10,4,3]
 * 			  假设输入总数，33，则输出推荐数字组合[10,9,8,4,1]
 */
public class Recommend {
    /**
     * @param choices  可选数字集
     * @param goal 目标总数
     * @return 返回最接近目标总数的推荐数字组合
     */
    List<Integer> subset = new ArrayList<>();
    HashMap<Integer,List<Integer>> subSetToSum = new HashMap<>();

    public List minAbsDifference(int[] nums, int goal) {
        int n = nums.length;
        int n1 = (n + 1) / 2, n2 = n - n1;
        int[] f1 = new int[(1 << n1)], f2 = new int[(1 << n2)];
        for (int i = 0; i < (1 << n1); ++i) {       //前半部分所有组合
            for (int j = 0; j < n1; ++j) {
                if (((i >> j) & 1) == 1) {
                    f1[i] += nums[j];
                    subset.add(nums[j]);
                }
            }
            subSetToSum.put(f1[i],subset);
            subset.clear();
        }
        for (int i = 0; i < (1 << n2); ++i) {       //后半部分所有组合
            for (int j = 0; j < n2; ++j) {
                if (((i >> j) & 1) == 1) {
                    f2[i] += nums[n1 + j];
                }
            }
            subSetToSum.put(f2[i],subset);
            subset.clear();
        }
        Arrays.sort(f2);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < f1.length; ++i) {
            int target = goal - f1[i];
            int index = this.lower_bound(f2, target);
            if (index < f2.length) {
                ans = min(ans, abs(f1[i] + f2[index] - goal));
            }
            if (index - 1 >= 0) {
                ans = min(ans, abs(f1[i] + f2[index - 1] - goal));
            }
        }
        return subSetToSum.get(ans);
    }
    //g中搜索≥target的第一个数字的位置[l,r)
    int lower_bound(int[] g, int target) {
        int l = 0, r = g.length;
        while (l + 1 < r) {
            int mid = l + (r - 1 - l) / 2;
            if (g[mid] < target) {
                l = mid + 1;
            } else if (g[mid] >= target) {
                r = mid + 1;
            }
        }
        return l;
    }
}