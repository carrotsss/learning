package datastructure.tree;


public class BinaryTree_recursive {
    public static void preOrderTraversal(BinaryTreeNode root){
        if(root != null){
            System.out.print(root.val);
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    public static void inOrder(BinaryTreeNode root){
        if(root != null){
            inOrder(root.left);
            System.out.print(root.val);
            inOrder(root.right);
        }
    }

    public static void backOrder(BinaryTreeNode root){
        if (root != null){
            backOrder(root.left);
            backOrder(root.right);
            System.out.print(root.val);
            return;
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
        preOrderTraversal(binaryTreeNode1);
    }

}
