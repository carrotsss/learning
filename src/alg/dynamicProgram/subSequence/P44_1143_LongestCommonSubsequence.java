package alg.dynamicProgram.subSequence;

/**
 * 最长公共子序列
 * 给定两个字符串text，text2，返回这两个字符串的最长公共子序列的长度
 * 例如，“ace“是”abcde“的子序列，但”aec“不是”abcde“的子序列
 */
public class P44_1143_LongestCommonSubsequence {
    public int longestCommonSubSequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i < text1.length(); i++) {
            char char1 = text1.charAt(i - 1);
            for (int j = 1; j < text2.length(); j++) {
                char char2 = text2.charAt(j - 1);
                if (char1 == char2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }
}
