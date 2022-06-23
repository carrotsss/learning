package zuo_leetcode.like;

/**
 * 正则表达式匹配
 * 给你一个字符串s和一个字符规律p，请你来实现一个支持‘.’和'*'的正则表达式匹配
 * ‘.’匹配任意字符
 * ‘*’匹配0个或多个前面的元素
 * 所谓匹配，是要涵盖整个字符串s，而不是部分字符串
 * <p>
 * 输入：s='aa' p = ‘a’
 * 输出：false
 * 解释：“a”无法匹配，"aa"整个字符串
 * <p>
 * 输入：s='aa' p = 'a*'
 * 输出：true
 * <p>
 * 输入：s='ab' p='.*'
 * 输出：true
 */
public class Problem_0010_RegularExpressionMatching_hard {

    public static boolean isMatch(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        if (!isValid(s, e)) {
            return false;
        }
        boolean[][] dp = initDPMap(s, e);
        for (int i = s.length - 1; i > -1; i--) {
            for (int j = e.length - 2; j > -1; j--) {
                if (e[j + 1] != '*') {
                    dp[i][j] = (s[i] == e[j] || e[j] == '.') && dp[i + 1][j + 1];
                } else {
                    int si = i;
                    while (si != s.length && (s[si] == e[j] || e[j] == '.')) {
                        if (dp[si][j + 2]) {
                            dp[i][j] = true;
                            break;
                        }
                        si++;
                    }
                    if (dp[i][j] != true) {
                        dp[i][j] = dp[si][j + 2];
                    }
                }
            }
        }
        return dp[0][0];
    }

    public static boolean isValid(char[] s, char[] e) {
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '*' || s[i] == '.') {
                return false;
            }
        }
        for (int i = 0; i < e.length; i++) {
            if (e[i] == '*' && (i == 0 || e[i - 1] == '*')) {
                return false;
            }
        }
        return true;
    }

    public static boolean[][] initDPMap(char[] s, char[] e) {
        int slen = s.length;
        int elen = e.length;
        boolean[][] dp = new boolean[slen + 1][elen + 1];
        dp[slen][elen] = true;
        for (int j = elen - 2; j > -1; j = j - 2) {
            if (e[j] != '*' && e[j + 1] == '*') {
                dp[slen][j] = true;
            } else {
                break;
            }
        }
        if (slen > 0 && elen > 0) {
            if ((e[elen - 1] == '.' || s[slen - 1] == e[elen - 1])) {
                dp[slen - 1][elen - 1] = true;
            }
        }
        return dp;
    }

    /**
     * 方法一：动态规划
     * 题目中的匹配是一个“逐步匹配”的过程：我们每次从字符串p中取出一个字符或者“字符+星号”的组合，并在s中进行匹配。对于p中一个字符而言，它只能在s中配皮一个字符，匹配的方法具有唯一性
     * 而对于p中字符+星号的组合而言，它可以在s中匹配人以自然数输个字符，并不具有唯一性。因此我们可以考虑动态规划，对匹配方案进行枚举。
     * 我们用f[i][j]表示s的前i个字符与p中前j个字符是否能够匹配。在进行状态转移时，我们考虑p的第j个字符的匹配情况：
     * 1、如果p的第j个字符是一个小写字母，那么我们必须在s中匹配一个相同的小写字母，即
     * f[i][j] ={
     * f[i-1][j-1] , s[i]=p[j]
     * false  , s[i] != p[j]
     * }
     * 也就是说，如果s的第i个字符与p的第j个字符不相同，那么无法进行匹配；否则我们可以匹配两个字符串的最后一个字符，
     * 完整的匹配结果取决于两个字符串前面的部分。
     * 2、如果p的第j个字符是*，那么久表示我们可以对p的第j-1个字符匹配任意自然数次。在匹配0次的情况下，我们有
     * f[i][j] = f[i][j-2] //aaaabaac -> a*baac
     * 也就是我们“浪费”了一个字符+星号的组合，没有匹配任何s中的字符
     * 在匹配1,2,3...次的情况下，类似的我们有
     * f[i][j] = f[i-1][i-2], if s[i] = p[j-1]
     * f[i][j] = f[i-2][j-2], if s[i-1] = s[i] = p[j-1]
     * f[i][j] = f[i-3][j-2], if s[i-2] = s[i-1] = s[i] = p[j-1]
     * ......
     * 如果我们通过这种方式进行转移，那么我们就需要枚举这个组合到底匹配了s中的几个字符，会导致时间复杂度增加，并且代码编写起来十分麻烦。
     * 我们不妨换个角度考虑这个问题：字母+星号的组合在匹配的过程中，本质上只会有两种情况：
     * 1、匹配s末位的一个字符，将该字符扔掉，而该组合还可以继续进行匹配
     * 2、不匹配字符，将该组合扔掉，不在进行匹配
     * 如果按照这个角度进行思考，我们可以写出很精巧的状态转移方程：
     * {
     * f[i][j] = {
     * f[i-1][j]   or  f[i][j-2]  ,s[i] = p[j-1]
     * f[i][j-2]  ,  s[i] != p[j-1]
     * }
     * }
     * 在任意情况下，只要p[j]一定匹配成功s中任意一个小写字母。
     * 最终状态转移方程是：
     * f[i][j] = {
     * if(p[j] != '*') = {
     * f[i-1][j-1] ,matches(s[i],p[j])
     * false, otherwise
     * }
     * otherwise{
     * f[i-1][j] or f[i][j-2], matches(s[i],p[j-1])
     * f[i][j-2], otherwise
     * }
     * }
     * 其中matches(x,y) 判断两个字符是否匹配的辅助函数，只有当y是.或者x和y本身相同时，这两个字符才会匹配
     * 细节，动态规划的边界条件为f[0][0]=true,即两个字符串是可以匹配的。最终的答案即为f[m][n]，其中m和n分别是字符串s和p的长度。
     * 由于大部分语言中，字符串的字符下标是从0开始的，因此在实现上面的状态转移方程时，需要注意状态中每一维下表与实际字符下表的对应关系。
     * <p>
     * 转移时，会先将a进行匹配（当p[j]为a时），再讲a*作为整体进行匹配（当p[j] 为* 时）。然而，在题目描述中，我们必须将a*看成整体，因此将a进行匹配是不符合要求的。
     * 看来我们进行了额外的状态转移，这样会对答案产生影响吗?留给大家思考。
     */
    private static boolean isMatchOfficial(String s, String p) {
        int m = s.length();
        int n = p.length();
        //s的前i个字符与p的前j个字符是否匹配
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        //非递归动态规划
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    public static void main(String[] args) {
        String s = "aab";
        String p = "a*b";
        if (isMatchOfficial(s, p)) {
            System.out.println("满足条件");
        } else {
            System.out.println("不满足条件");
        }
    }
}
