package zuo_leetcode.like;

/**
 * 给你一个字符串s,找到s中最长的回文子串
 * <p>
 * 输入：s = “babad”
 * 输出：“bab”
 */
public class Problem_0005_LongestPalindromicSubstring_middle {

    public static String longestPalindrome(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length];
        int index = -1;
        int pR = -1;
        int max = Integer.MIN_VALUE;
        int mid = 0;
        for (int i = 0; i != charArr.length; i++) {
            pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            if (i + pArr[i] > pR) {
                pR = i + pArr[i];
                index = i;
            }
            if (max < pArr[i]) {
                max = pArr[i];
                mid = i;
            }
        }
        mid = (mid - 1) / 2;
        max = max - 1;
        return str.substring((max & 1) == 0 ? mid - (max / 2) + 1 : mid - (max / 2), mid + (max / 2) + 1);
    }

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    /**
     * 如果一个字符串是回文串，那么将他的首位字符去掉，他仍然是一个回文串。根据这样的思路，我们就可以用动态规划的方法解决本题。
     * 我们用P(i,j)表示字符串s的第i到第j个字母组成的字符串是否为回文串。
     * <p>
     * P746_MinCostClimbingStairs(i,j) = { true      如果子串si...sj是回文串
     * { false     其他情况
     * <p>
     * 我们就找到状态转移方程：
     * <p>
     * P746_MinCostClimbingStairs(i,j) = P746_MinCostClimbingStairs(i+1,j-1)&&(Si==Sj)
     * 也就是说，只有s[i+1,j-1]是回文串，并且s的第i和j个字母相同时，s[i:j]才是回文串
     * 上文的所有讨论是建立在子串长度大于2的前提下的，我们还需要考虑动态规划中的边界条件，即子串长度为1或者2,因此边界条件为：
     * <p>
     * { P746_MinCostClimbingStairs(i,i) = true
     * { p(i,i+1) = （Si == Si+1)
     * <p>
     * 根据这个思路，我们就可以完成动态规划了，最终的答案即为所有P(i,j) = true 中j-i+1（即子串长度）的最大值。
     * 注意：在状态转移方程中，我们是从长度较短的字符串向长度较长的字符串进行转移的，因此一定要注意动态规划的循环顺序
     *
     * @param str
     * @return
     */
    public static String longestPalindromeOfficial(String str) {
        int len = str.length();
        if (len < 2) {
            return str;
        }
        int maxlen = 1;
        int begin = 0;
        //designPattern[i][j] 表示s[i...j]是否为回文串
        boolean[][] dp = new boolean[len][len];
        //初始化：所有长度为1的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        char[] chars = str.toCharArray();
        //递推开始
        //先枚举子串长度
        for (int L = 2; L <= len; L++) {
            //枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                //根据子串长度和左边界可以确定右边界，即j - i + 1 = L 得
                // 固定长度的子串，依次从左往右移动窗口
                int j = L + i - 1;
                //如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                //只要dp[i][j] == true成立，就表示子串s[i...L]是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxlen) {
                    maxlen = j - i + 1;
                    begin = i;
                }
            }
        }
        return str.substring(begin, begin + maxlen);
    }

    public static String longestPalindromeSelf(String str) {
        int len = str.length();
        boolean[][] dp = new boolean[len][len];
        char[] chars = str.toCharArray();
        int maxLen = 1;
        int begin = 0;
        if (len < 2) {
            return str;
        }
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int L = 2; L <= len; L++) {
            for (int i = 0; i < len; i++) {
                int j = L + i - 1;
                if (j >= len) {
                    break;
                }
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        //状态转移方程
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return str.substring(begin, begin + maxLen);
    }

    public static void main(String[] args) {
        String str = "3223babad234234";
        System.out.println(
                longestPalindromeOfficial(str));
        System.out.println(
                longestPalindromeSelf(str));
    }
}
