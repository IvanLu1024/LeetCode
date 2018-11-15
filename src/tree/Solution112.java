package tree;

import org.junit.Test;

/**
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。

     说明: 叶子节点是指没有子节点的节点。

     示例:
     给定如下二叉树，以及目标和 sum = 22，

     5
     / \
     4   8
     /   / \
     11  13  4
     /  \      \
     7    2      1
     返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 * @author
 * @create 2018-11-15 21:45
 **/
public class Solution112 {
    public boolean hasPathSum(TreeNode root, int sum) {
        //注意递归的终止条件
        if (root==null){
            return false;
        }
        if (root.left==null&&root.right==null){
            if (root.val==sum){
            return true;
            }
            else {
                return false;
            }
        }
        return hasPathSum(root.right,sum-root.val)||hasPathSum(root.left,sum-root.val);
    }

    @Test
    public void test(){
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(1);
        TreeNode n4 = new TreeNode(1);
        TreeNode n5 = new TreeNode(1);
        n1.left=n2;
        n1.right=n3;
        n3.right=n4;
        n4.left=n5;

    }

}
