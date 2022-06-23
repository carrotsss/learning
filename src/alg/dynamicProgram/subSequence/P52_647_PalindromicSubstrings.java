package alg.dynamicProgram.subSequence;

/**
 * 回文子串
 * 给你一个字符串s,请你统计并返回这个字符串中回文子串的数目
 * 输入：”abc“
 * 输出：3
 * 说明："a”，“b“，”c":
 *
 * 输入：”aaa“
 * 输出：6
 * 说明：a,a,a,aa,aa, aaa,
 */
public class P52_647_PalindromicSubstrings {
    public static int countSubstrings(String s) {
        int len, ans = 0;
        if (s == null || (len = s.length()) < 1) {
            return 0;
        }
        //dp[i][j], s字符窜下标i到j的子串是一个回文串，即s[i,j]
        boolean[][] dp = new boolean[len][len];
        for (int j = 0; j < len; j++) {
            for (int i = 0; i < j; j++) {
                //当两端字母一样时，才可以两端收缩进一步判断
                if (s.charAt(i) == s.charAt(j)) {
                    //i++ ,j-- 即两端收缩之后i，j指针指向同一个字符或者i超过j了，必然是一个回文串
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        //否则通过收缩之后的子串判断
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    //两端字符不一样，不是回文串
                    dp[i][j] = false;
                }
            }
        }
        //遍历每一个子串，统计回文串个数
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (dp[i][j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static int countSubStrings(String s) {
        int len=0, ans = 0;
        if (s == null || (s.length() - 1) < 1) {
            return 0;
        }
        //总共有2 * len -1 个中心点
        for (int i = 0; i < 2 * len - 1; i++) {
            //通过遍历每个回文中心，向两边扩散，并判断是否是回文串
            //有两种情况，left==right，right == left + 1 ,这两种回文中心是不一样的
            int left = 1 / 2, right = left + 1;
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                //如果当前是一个回文串，则记录数量
                ans++;
                left++;
                right--;
            }
        }
        return ans;
    }
}
