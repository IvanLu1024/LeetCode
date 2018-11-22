package tree;

import org.junit.Test;

/**
 *计算给定二叉树的所有左叶子之和。

     示例：

      3
     / \
    9  20
     /  \
   15   7

     在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 *
 *
 */
public class Solution404 {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root==null){
            return 0;
        }
        //如何判断一个结点是否为左叶子结点，当其父亲结点的左孩子不空
        if (root.left!=null){
            //并且为叶子结点，则此时为左叶子结点
            if (root.left.left==null&&root.left.right==null){
                return
                        //继续遍历右边累积
                        sumOfLeftLeaves(root.right)+root.left.val;
            }
        }
        return sumOfLeftLeaves(root.left)+sumOfLeftLeaves(root.right);

    }

    @Test
    public void test(){
        int[] pre={3,9,20,15,7};
        int[] in={9,3,15,20,7};
        TreeNode root = TreeNodeUtils.ConstructBinaryTree(pre, in);
        int res = sumOfLeftLeaves(root);
        System.out.println(res);

    }

}
