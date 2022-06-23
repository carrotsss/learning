package datastructure.tree.base;

public class AVLNode<T extends Comparable> {
    public AVLNode<T> parent;
    public AVLNode<T> leftChild;
    public AVLNode<T> rightChild;
    public int data;

    public AVLNode(int data) {
        this(null, null, null, data);
    }

    public AVLNode(AVLNode<T> parent, AVLNode<T> leftChild, AVLNode<T> rightChild, int data) {
        super();
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.data = data;
    }

    public AVLNode(AVLNode<T> node, int val) {
        this(node, null, null, val);
    }
}
