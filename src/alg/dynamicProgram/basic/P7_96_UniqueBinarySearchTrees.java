package alg.dynamicProgram.basic;

/**
 * 96不同的二叉搜索树（较难)
 * 给你一个整数n，求恰由n个节点组成且节点值从1到n互不相同的二叉搜索树有多少种？返回满足提议的二叉搜索树的种树
 * 输入：3
 * 输出：5
 */
public class P7_96_UniqueBinarySearchTrees {
    public int numTree(int n) {
        //初始化dp数组
        int[] dp = new int[n + 1];
        //初始化0个节点和1个节点的情况
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {//选择根节点
            for (int j = 1; j <= i; j++) {
                //对于第i个节点，需要考虑1作为根节点直到i作为根节点的情况，所以需要累加
                //一共i个节点，对于根节点j时，左子树的节点个数为j-1，右子树的节点个数为i-j
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
