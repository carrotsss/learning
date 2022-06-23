package alg.dynamicProgram.rob;

import datastructure.tree.BinaryTreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * 在上一次打劫完一条街道之后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为”根“。除了”根“之外，
 * 每栋房子有且只有一个”父“房子与之相连。一番侦查之后，聪明的小偷意识到“这个地方的所有房屋类似于一棵二叉树。如果两个直接相连的房子在同一天被打劫，房屋将自动报警。
 * 计算在不触动警报的情况下，小偷能够获取的最高金额
 * 输入：【3，2，3，null，3，mull，1】
 * 输出：7
 * 说明：3 + 3 + 1 = 7
 */
public class P31_337_HouseRobber_iii {
    //1、递归去偷，超时
    public int robOfficial(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        int money = root.val;
        if (root.left != null) {
            money += robOfficial(root.left.left) + robOfficial(root.left.right);
        }
        if (root.right != null) {
            money += robOfficial(root.right.left) + robOfficial(root.right.right);
        }
        return Math.max(money, robOfficial(root.left) + robOfficial(root.right));
    }

    /**
     * 2、递归去偷，记录状态
     * 执行用时：3ms，在所有java提交中击败了56.24%用户
     */
    public int rob1(BinaryTreeNode root) {
        Map<BinaryTreeNode, Integer> memo = new HashMap<>();
        return robAction(root, memo);
    }

    int robAction(BinaryTreeNode root, Map<BinaryTreeNode, Integer> memo) {
        if (root == null) {
            return 0;
        }
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        int money = root.val;
        if (root.left != null) {
            money += robAction(root.left.left, memo) + robAction(root.left.right, memo);
        }
        if (root.right != null) {
            money += robAction(root.right.left, memo) + robAction(root.right.right, memo);
        }
        int res = Math.max(money, robAction(root.left, memo) + robAction(root.right, memo));
        memo.put(root, res);
        return res;
    }

    /**
     * 3、状态标记递归
     * 执行用时：0ms，在所有java提交中击败了100%的用户
     * 不偷：Math.Max(左孩子不偷，左孩子偷）+ Math.max（右孩子不偷，有孩子偷）
     * root[0] = Math.max(root.left[0], rob(root.left)[1]) + Math.max(rob(root.right)[0],rot(root, right)[1]
     * 偷：做孩子不偷 + 右孩子不偷 + 当前节点偷
     * root[1] = rob(root.left)[0] + rob(root.right)[0] + val
     */
    public static int rob3Official(BinaryTreeNode root) {
        int[] res = robAction1(root);
        return Math.max(res[0], res[1]);
    }

    static int[] robAction1(BinaryTreeNode root) {
        int res[] = new int[2];
        if (root == null) {
            return res;
        }
        int[] left = robAction1(root.left);
        int[] right = robAction1(root.right);
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        return res;
    }

    public static void main(String[] args) {
        BinaryTreeNode binaryTreeNode = new BinaryTreeNode(3);
        BinaryTreeNode binaryTreeNode1 = new BinaryTreeNode(2);
        BinaryTreeNode binaryTreeNode2 = new BinaryTreeNode(3);
        BinaryTreeNode binaryTreeNode3 = new BinaryTreeNode(3);
        BinaryTreeNode binaryTreeNode4 = new BinaryTreeNode(1);
        binaryTreeNode.left = binaryTreeNode1;
        binaryTreeNode.right = binaryTreeNode2;
        binaryTreeNode1.right = binaryTreeNode3;
        binaryTreeNode2.right = binaryTreeNode4;
        System.out.println(rob3Official(binaryTreeNode));
    }
}
