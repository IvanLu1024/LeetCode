package tree;

import org.junit.Test;

/**
 * @author
 * @create 2018-11-15 21:00
 **/
public class Solution111 {
    public int minDepth(TreeNode root) {
        if (root==null){
            return 0;
        }
        //当左右子树均不为空的时候，取左右子树中的最小值
        if (root.left!=null&&root.right!=null){
            return Math.min(minDepth(root.right),minDepth(root.left))+1;
        }
        //当左右子树不是都有结点的时候，取左右子树的最大深度
        return Math.max(minDepth(root.right),minDepth(root.left))+1;

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
        int i = minDepth(n4);
        System.out.println(i);
    }
}
