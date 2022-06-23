package zuo_leetcode.like;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串s,请你找出其中不含有重复字符的最长子串的长度
 *
 * 输入：s = "abcabcbb"
 * 输出：3
 * 解释：因为无重复字符的最长子串是"abc"，所以其长度为3
 */
public class Problem_0003_LongestSubstringWithoutRepeatingCharacters_middle {

    public static int lengthOfLongestSubstring(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chas = str.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        int len = 0;
        int pre = -1;
        int cur = 0;
        for (int i = 0; i != chas.length; i++) {
            pre = Math.max(pre, map[chas[i]]);
            cur = i - pre;
            len = Math.max(len, cur);
            map[chas[i]] = i;
        }
        return len;
    }

    /**
     * official 解法
     * 使用滑动窗口方法从字符串第一个依次往后移动，并通过滑动窗口拉长窗口大小，保留当前字符往后最长的窗口知道最后一个字符。
     * @param str
     * @return
     */

    public static int lengthOfLongestSubstringOfficial(String str) {
        char[] chars = str.toCharArray();
        //哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<>();
        int n = str.length();
        //右指针，初始值为-1，相当于我们在字符串的做便捷的左侧，还没有开始移动。rk是无重复串的截止下标。
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                //左指针向右移动一格，移除一个字符
                occ.remove(str.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(str.charAt(rk + 1))) {
                //不断向右移动右指针
                occ.add(str.charAt(rk + 1));
                ++rk;
            }
            //第i到rk个字符是一个最长的无重复字符子串，ans是之前最长，rk-i是目前无重复子串长度
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    public static int lengthOfLongestSubstringSelf(String string) {
        HashSet<Character> hashSet = new HashSet();
        int rk = -1;
        int ans = 0;
        int n = string.length();
        for (int i = 0; i < n; ++i) {
            if (i > 0) {
                //string.charAt()是从0开始
                hashSet.remove(string.charAt(i - 1));
            }
            //滑动窗口每次前进一个数字知道遇到重复字符，或者超过总长度
            while (rk + 1 < n && !hashSet.contains(string.charAt(rk + 1))) {
                hashSet.add(string.charAt(rk + 1));
                ++rk;
            }
            //之前最长子串长度和当前子串长度对比
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        String str = new String("abcabcbb");
        System.out.println(lengthOfLongestSubstring(str));
        System.out.println(lengthOfLongestSubstringOfficial(str));
        System.out.println(lengthOfLongestSubstringSelf(str));
    }

}
