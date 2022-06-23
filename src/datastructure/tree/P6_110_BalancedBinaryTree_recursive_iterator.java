package datastructure.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 平衡二叉树
 * 判断一个树是否是平衡二叉树
 */
public class P6_110_BalancedBinaryTree_recursive_iterator {
    /**
     * 递归法
     *
     * @param root
     * @return
     */
    public static boolean isBalancedRecursive(BinaryTreeNode root) {
        return getHeightRecursive(root) != -1;
    }

    private static int getHeightRecursive(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeightRecursive(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = getHeightRecursive(root.right);
        if (rightHeight == -1) {
            return -1;
        }
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 暴力迭代法
     * @param root
     * @return
     */
    public static boolean isBalancedIterator(BinaryTreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            BinaryTreeNode inNode = stack.peek();
            //右节点为null或已经遍历过
            if (inNode.right == null || inNode.right == pre) {
//                if (Math.abs(getHeightIterator(inNode.left) - getHeightIterator(inNode.right)) > 1) {
                /**
                 * 针对暴力迭代的获取高度的方法进行优化，用节点的值作为高度
                 */
                if (Math.abs(getHeightIterator2(inNode.left) - getHeightIterator2(inNode.right)) > 1) {
                    return false;
                }
                stack.pop();
                pre = inNode;
                root = null;//当前节点下，没有要遍历的节点了
            } else {
                root = inNode.right;//右节点还没遍历，遍历右节点
            }
        }
        return true;
    }

    /**
     * 层序遍历计算高度
     * @param root
     * @return
     */
    public static int getHeightIterator(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<BinaryTreeNode> deque = new LinkedList<>();
        deque.offer(root);
        int depth = 0;
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

    /**
     * 针对暴力迭代的获取高度的方法进行优化，用节点的值作为高度
     * @param root
     * @return
     */
    public static int getHeightIterator2(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = root.left != null ? root.left.val : 0;
        int rightHeight = root.right != null ? root.right.val : 0;
        int height = Math.max(leftHeight, rightHeight);
        root.val = height;
        return height;
    }
}
