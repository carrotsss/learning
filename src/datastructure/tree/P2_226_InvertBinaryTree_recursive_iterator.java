package datastructure.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 反转一棵二叉树
 * 4
 * 27
 * 1369
 * -》
 * 4
 * 72
 * 9631
 */
public class P2_226_InvertBinaryTree_recursive_iterator {
    /**
     * 递归法
     * 前后序都可以
     * 中序不行，因先左孩子交换孩子，再跟交换孩子（做完后，有孩子已经变成了原来的左孩子），再右孩子交换孩子（此时其实是对原来的左孩子作交换）
     *
     * @param root
     * @return
     */
    public static BinaryTreeNode invertTreeRecursiveOfficial(BinaryTreeNode root) {
        if (root == null) {
            return null;
        }
        invertTreeRecursiveOfficial(root.left);
        invertTreeRecursiveOfficial(root.right);
        swapChildren(root);
        return root;
    }

    public static void swapChildren(BinaryTreeNode root) {
        BinaryTreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    /**
     * 迭代法
     * 使用队列序列化存储每一层
     */
    public static BinaryTreeNode invertTreeIteratorOfficial(BinaryTreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<BinaryTreeNode> dequeue = new LinkedList<>();
        dequeue.add(root);
        //迭代法每次迭代一层
        while (!dequeue.isEmpty()) {
            int size = dequeue.size();
            while (size-- > 0) {
                BinaryTreeNode node = dequeue.poll();
                //和层序遍历一样只是添加一层的时候交换两个子节点
                swap(node);
                if (node.left != null) {
                    dequeue.offer(node.left);
                }
                if (node.right != null) {
                    dequeue.offer(node.right);
                }
            }
        }
        return root;
    }

    public static void swap(BinaryTreeNode root) {
        BinaryTreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    public static void main(String[] args) {
        BinaryTreeNode binaryTreeNode1 = new BinaryTreeNode(4);
        BinaryTreeNode binaryTreeNode2 = new BinaryTreeNode(2);
        BinaryTreeNode binaryTreeNode3 = new BinaryTreeNode(7);
        BinaryTreeNode binaryTreeNode4 = new BinaryTreeNode(1);
        BinaryTreeNode binaryTreeNode5 = new BinaryTreeNode(3);
        BinaryTreeNode binaryTreeNode6 = new BinaryTreeNode(6);
        BinaryTreeNode binaryTreeNode7 = new BinaryTreeNode(9);
        binaryTreeNode1.left = binaryTreeNode2;
        binaryTreeNode1.right = binaryTreeNode3;
        binaryTreeNode2.left = binaryTreeNode4;
        binaryTreeNode2.right = binaryTreeNode5;
        binaryTreeNode3.left = binaryTreeNode6;
        binaryTreeNode3.right = binaryTreeNode7;
        /**
         * 4
         * 72
         * 9361
         */
        for (List<Integer> list : P1_102_BinaryTreeLevelOrderTraversal_recursive_iterator.levelOrderOfficial(binaryTreeNode1)) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
