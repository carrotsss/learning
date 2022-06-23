package alg.backtrack.partition;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 分割回文串
 * 给你一个字符串s，请你将s分割成一些子串，是每个子串都是回文串。返回s所有可能的分割方案
 * 输入：s = "aab"
 * 输出：【【a,a,b],["aa","b"]]
 *
 * aab
 * a|ab aa|b aab
 * a|a|b aa|b| aab|
 * a|a|b|
 */
public class P9_131_PalindromePartitioning {
    private static List<List<String>> lists = new ArrayList<>();
    private static Deque<String> deque = new LinkedList<>();
    public static List<List<String>> patition(String s) {
        backTracking(s, 0);
        return lists;
    }

    private static void backTracking(String s, int startIndex) {
        //递归终止条件：如果其位置大于s的大小，说明找到了一组分割方案，切割通过substring来实现
        if (startIndex >= s.length()) {
            lists.add(new ArrayList<>());
            return;
        }
        //溯搜索遍历过程
        for (int i = 0; i < s.length(); i++) {
            //回判断是回文串，则记录。
            if (isPalindrome(s, startIndex, i)) {
                String string = s.substring(startIndex, i + 1);
                deque.addLast(string);
            } else {
                continue;
            }
            //其实位置后移，保证不重复
            backTracking(s, i + 1);
            deque.removeLast();
        }
    }

    private static boolean isPalindrome(String s, int startIndex, int end) {
        for (int i = startIndex, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        for (List<String> list : patition("aab")) {
            for (String i : list) {
                System.out.print(i);
            }
            System.out.println();
        }
    }
}
