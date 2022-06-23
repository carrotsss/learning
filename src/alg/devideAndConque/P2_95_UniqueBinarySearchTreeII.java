package alg.devideAndConque;

import java.util.ArrayList;
import java.util.List;

/**
 * 不同的二叉搜索树
 * 给定一个数组n，要求生成值为1.。。n的二叉搜索树
 * 输入：3
 * 输出：【【1，null,3,2】,【3,2，null，1】，【3,1，null，null，2】，【2,1,3】，【1，null，2，null，3】】
 */
public class P2_95_UniqueBinarySearchTreeII {
    public List<BinaryTreeNode> generateSubTrees(int s, int e) {
        List<BinaryTreeNode> res = new ArrayList<>();
        if (s > e) {
            res.add(null);
            return res;
        }
        for (int i = s; i <= e; i++) {
            List<BinaryTreeNode> leftSubTrees = generateSubTrees(s, i - 1);
            List<BinaryTreeNode> rightSubTrees = generateSubTrees(i + 1, e);
            for (BinaryTreeNode leftSubTree : leftSubTrees) {
                for (BinaryTreeNode rightSubTree : rightSubTrees) {
                    BinaryTreeNode root = new BinaryTreeNode(i);
                    root.left = leftSubTree;
                    root.right = rightSubTree;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
