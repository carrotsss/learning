package datastructure.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 对称二叉树
 * 给定一个二叉树，检查他是否是镜像对称的
 * 输入:
 * 1
 * 22
 * 3443
 * 输出：是的
 * 1
 * 22
 * 0303
 * 输出：不是的
 */
public class P3_101_SymmetricTree_recursive_iterator {
    /**
     * 递归法
     * @param root
     * @return
     */
    public boolean isSymmetricRecursiveOfficial(BinaryTreeNode root) {
        return compare(root.left, root.right);
    }

    public boolean compare(BinaryTreeNode left, BinaryTreeNode right) {
        if (left != null && right == null) {
            return false;
        }
        if (left == null && right != null) {
            return false;
        }
        if (left == null && right == null) {
            return true;
        }
        if (left.val == right.val) {
            return true;
        }
        //比较外侧
        boolean compareOutside = compare(left.left, right.right);
        //比较内侧
        boolean compareInside = compare(left.right, right.left);
        return compareInside && compareOutside;
    }

    /**
     * 迭代法
     * 使用双端队列，相当于两个栈
     * @param root
     * @return
     */
    public static boolean isSymmetricIterator1Official(BinaryTreeNode root) {
        Deque<BinaryTreeNode> deque = new LinkedList<>();
        deque.offer(root.left);
        deque.offer(root.right);
        while (!deque.isEmpty()) {
            BinaryTreeNode left = deque.poll();
            BinaryTreeNode right = deque.poll();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            deque.offer(left.left);
            deque.offer(right.right);
            deque.offer(left.right);
            deque.offer(right.left);
        }
        return true;
    }

    /**
     * 迭代法
     * 使用普通队列
     */
    public static boolean isSymmetricIterator2Official(BinaryTreeNode root) {
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            BinaryTreeNode leftNode = queue.poll();
            BinaryTreeNode rightNode = queue.poll();
            if (leftNode == null && rightNode == null) {
                continue;
            }
            if (leftNode == null || rightNode == null || leftNode.val != rightNode.val) {
                return false;
            }
            //这里顺序和使用dequeue不同
            queue.offer(leftNode.left);
            queue.offer(rightNode.right);
            queue.offer(leftNode.right);
            queue.offer(rightNode.left);
        }
        return true;
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
        if (isSymmetricIterator1Official(binaryTreeNode1)) {
            System.out.println("是对称的");
        }
    }
}
