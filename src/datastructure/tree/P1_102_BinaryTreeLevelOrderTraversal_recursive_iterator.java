package datastructure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P1_102_BinaryTreeLevelOrderTraversal_recursive_iterator {
    public static List<List<Integer>> resList = new ArrayList<>();

    public static List<List<Integer>> levelOrderOfficial(BinaryTreeNode root) {
        checkFunIterator(root);
        return resList;
    }

    /**
     * 递归法
     * @param root
     * @param deep
     */
    public static void chekFunRecursive(BinaryTreeNode root, Integer deep) {
        if (root == null) {
            return;
        }
        deep++;
        if (resList.size() < deep) {
            List<Integer> item = new ArrayList<>();
            resList.add(item);
        }
        resList.get(deep - 1).add(root.val);
        chekFunRecursive(root.left, deep);
        chekFunRecursive(root.right, deep);
    }

    /**
     * 迭代法
     * @param root
     */
    public static void checkFunIterator(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        //queue先存储一层的数据，再存下一层的
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            //用于存储每一层节点
            List<Integer> itemList = new ArrayList<>();
            int len = queue.size();
            while (len > 0) {
                //队列取一个在数列里放一个
                BinaryTreeNode tempNode = queue.poll();
                itemList.add(tempNode.val);
                if (tempNode.left != null) {
                    queue.offer(tempNode.left);
                }
                if (tempNode.right != null) {
                    queue.offer(tempNode.right);
                }
                len--;
            }
            resList.add(itemList);
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode binaryTreeNode1 = new BinaryTreeNode(0);
        BinaryTreeNode binaryTreeNode2 = new BinaryTreeNode(1);
        BinaryTreeNode binaryTreeNode3 = new BinaryTreeNode(2);
        BinaryTreeNode binaryTreeNode4 = new BinaryTreeNode(3);
        BinaryTreeNode binaryTreeNode5 = new BinaryTreeNode(4);
        BinaryTreeNode binaryTreeNode6 = new BinaryTreeNode(5);
        BinaryTreeNode binaryTreeNode7 = new BinaryTreeNode(6);
        binaryTreeNode1.left = binaryTreeNode2;
        binaryTreeNode1.right = binaryTreeNode3;
        binaryTreeNode2.left = binaryTreeNode4;
        binaryTreeNode2.right = binaryTreeNode5;
        binaryTreeNode3.left = binaryTreeNode6;
        binaryTreeNode3.right = binaryTreeNode7;
        for (List<Integer> list :
                levelOrderOfficial(binaryTreeNode1)) {
            for (int i :
                    list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
