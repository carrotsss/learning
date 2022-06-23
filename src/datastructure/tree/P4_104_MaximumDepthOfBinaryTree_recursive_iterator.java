package datastructure.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个二叉树，找出其最大深度
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数
 * 输入：【3,9,20，null，null，15,7】
 * 3
 * 9 20
 * null null 15 7
 * 输出：最大深度3
 */
public class P4_104_MaximumDepthOfBinaryTree_recursive_iterator {
    /**
     * 递归法
     *
     * @param root
     * @return
     */
    public static int maxdepthRecursiveOfficial(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxdepthRecursiveOfficial(root.left);
        int rightDepth = maxdepthRecursiveOfficial(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 迭代法
     * @param root
     * @return
     */
    public static int maxdepthIteratorOfficial(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<BinaryTreeNode> deque = new LinkedList<>();
        deque.offer(root);
        int depth = 0;
        //层序遍历每结束一层深度加一
        while (!deque.isEmpty()) {
            int size = deque.size();
            depth++;
            for (int i = 0; i < size; i++) {
                BinaryTreeNode pollNode = deque.poll();
                if (pollNode.left != null) {
                    deque.offer(pollNode);
                }
                if (pollNode.right != null) {
                    deque.offer(pollNode);
                }
            }
        }
        return depth;
    }

    public static void main(String[] args) {
        BinaryTreeNode binaryTreeNode1 = new BinaryTreeNode(1);
        BinaryTreeNode binaryTreeNode2 = new BinaryTreeNode(2);
        BinaryTreeNode binaryTreeNode3 = new BinaryTreeNode(2);
        BinaryTreeNode binaryTreeNode4 = new BinaryTreeNode(3);
        BinaryTreeNode binaryTreeNode5 = new BinaryTreeNode(4);
        BinaryTreeNode binaryTreeNode6 = new BinaryTreeNode(4);
        BinaryTreeNode binaryTreeNode7 = new BinaryTreeNode(3);
        binaryTreeNode1.left = binaryTreeNode2;
        binaryTreeNode1.right = binaryTreeNode3;
        binaryTreeNode2.left = binaryTreeNode4;
        binaryTreeNode2.right = binaryTreeNode5;
        binaryTreeNode3.left = binaryTreeNode6;
        binaryTreeNode3.right = binaryTreeNode7;
        System.out.println("深度：" + maxdepthIteratorOfficial(binaryTreeNode1));
    }
}
