package company.cmcc.chapter4;

import java.util.*;

/**
 * 输入一个降序不重复正整数可选数字集和一个目标总数，必为正整数，输出数字集合的子集，使其最接近目标总数。
 *           例：输入数字集合，[10,9,8,4,3,1]，
 * 		      假设输入总数，20，则输出推荐数字组合[10,9,1]，
 * 			  假设输入总数，16，则输出推荐数字组合[10,4,1]，[10,4,3]
 * 			  假设输入总数，33，则输出推荐数字组合[10,9,8,4,1]
 */
public class Recommend4 {
    /**
     * @param choices  可选数字集
     * @param goal 目标总数
     * @return 返回最接近目标总数的推荐数字组合
     */
    public List<Integer[]> recommend (Integer[] choices, int goal) {
        List<Integer[]> recommend = null;
        Deque<Integer> deque = new LinkedList<>();
        // todo 待实现
        List<Integer> l1 = new ArrayList<>();//存储左半边数字和
        List<Integer> l2 = new ArrayList<>();//存储右半边数字和
        List<Integer> subset = new ArrayList<>();//存储子序列
        int len = choices.length >> 1;//原数组划分为左、右两部分
        getSum(l1, choices, 0, 0, len);
        getSum(l2, choices, 0, len, choices.length);
        Collections.sort(l1);//排序
        Collections.sort(l2);//排序
        int i1 = 0, n1 = l1.size(), i2 = l2.size() - 1;//l1从最小出发，l2从最大出发
        int ans = Math.abs(goal);
        HashMap<Integer,List> res = new HashMap<>();//存储结果
        while (i1 < n1 && i2 >= 0) {//双指针寻找最接近goal的和
            int num = l1.get(i1) + l2.get(i2) - goal;
            if (num > 0) {
                i2--;
                ans = Math.min(ans, num);
            } else if (num < 0) {
                i1++;
                ans = Math.min(ans, -num);
            } else
                return null;
//                return 0;
        }
        return null;
//        return ans;
    }

    public void getSum(List<Integer> col, Integer[] nums, int sum, int i, int end) {//回溯，得到所有可能的数字和
        if (i >= end) {
            col.add(sum);
            return;
        }
        getSum(col, nums, sum + nums[i], i + 1, end);
        getSum(col, nums, sum, i + 1, end);
    }

}