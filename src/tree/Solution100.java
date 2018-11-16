package tree;

import org.junit.Test;

/**
 *给定两个二叉树，编写一个函数来检验它们是否相同。
 *
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 * 示例 1:
 *
 * 输入:       1         1
 *           / \       / \
 *          2   3     2   3
 *
 *         [1,2,3],   [1,2,3]
 *
 * 输出: true
 * 示例 2:
 *
 * 输入:      1          1
 *           /           \
 *          2             2
 *
 *         [1,2],     [1,null,2]
 *
 * 输出: false
 * 示例 3:
 *
 * 输入:       1         1
 *           / \       / \
 *          2   1     1   2
 *
 *         [1,2,1],   [1,1,2]
 *
 * 输出: false
 *
 *
 */
public class Solution100 {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null&&q==null){
            return true;
        }else if (p==null||q==null){
            return false;
        }

        if (p.val!=q.val){
            return false;
        }
        //当该结点对应相等的时候，继续比较其子结构
        else
            return isSameTree(p.right,q.right)&&isSameTree(p.left,q.left);

    }
    @Test
    public void test(){
        /*[10,5,15]
[10,5,null,null,15]*/
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(1);
        TreeNode n5 = new TreeNode(2);
        TreeNode n6 = new TreeNode(3);
        n1.left=n2;
        n1.right=n3;

        n4.left=n5;
        n4.right=n6;
        boolean sameTree = isSameTree(n1, n4);
        System.out.println(sameTree);


    }
}
