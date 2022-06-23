package datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树最小深度
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量
 * 3
 * 9 20
 * null null 15 7
 * [3,9,20,null,null,15,7]
 * 输出：2
 */
public class P5_111_MinimumDepthOfBinaryTree_recursive_iterator {
    /**
     * 递归法，相比求MaxDepth要复杂点
     * 因为最小深度是从根节点到最近的叶子节点的最短路径上的节点数量
     * @param root
     * @return
     */
    public static int minDepthRecursiveOfficial(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = minDepthRecursiveOfficial(root);
        int rightDepth = minDepthRecursiveOfficial(root);
        if (root.left == null) {
            return rightDepth + 1;
        }
        if (root.right == null) {
            return leftDepth + 1;
        }
        return Math.min(leftDepth, rightDepth) + 1;
    }

    /**
     * 迭代法，层序遍历
     * @param root
     * @return
     */
    public static int minDepthIteratorOfficial(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        int depth = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                BinaryTreeNode pollNode = queue.poll();
                if (pollNode.right == null && pollNode.left == null) {
                    return depth;
                }
                if (pollNode.left != null) {
                    queue.offer(root.left);
                }
                if (pollNode.right != null) {
                    queue.offer(root.right);
                }
            }
        }
        return depth;
    }
}
