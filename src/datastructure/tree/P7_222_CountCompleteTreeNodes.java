package datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 完全二叉树的节点个数
 * 除了最底层叶子节点没有填满之外，其他层都填满了
 */
public class P7_222_CountCompleteTreeNodes {
    /**
     * 递归法
     *
     * @param root
     * @return
     */
    public static int countNodesRecursiveOfficial(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodesRecursiveOfficial(root.left) + countNodesRecursiveOfficial(root.right) + 1;
    }

    public static int countNodesIteratorOfficial(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                BinaryTreeNode cur = queue.poll();
                result++;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return result;
    }
}
