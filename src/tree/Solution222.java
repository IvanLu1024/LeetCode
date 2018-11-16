package tree;

import org.junit.Test;

/**
 * 给出一个完全二叉树，求出该树的节点个数。
 *
 * 说明：
 *
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
 * 并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 *
 * 示例:
 *
 * 输入:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 *
 * 输出: 6
 *
 */
public class Solution222 {
    public int countNodes(TreeNode root) {
        if (root==null){
            return 0;
        }

        //先统计左右子树的高度
        int leftHeight=getLeftHeight(root);
        int rightHeight=getRightHeight(root);
        //如果左右子树高度相等，则说明是满二叉树，则按公式来计算
        if (leftHeight==rightHeight)
            return (2<<leftHeight)-1;
        //如果不相等，则遍历计数
        else return countNodes(root.right)+countNodes(root.left)+1;

    }

    private int getRightHeight(TreeNode right) {
        int height=0;
        while (right.right!=null){
            right=right.right;
            height++;
        }
        return height;
    }

    private int getLeftHeight(TreeNode left) {
        int height=0;
        while (left.left!=null){
            left=left.left;
            height++;
        }
        return height;
    }


    @Test
    public void test(){
        TreeNode n0 = new TreeNode(1);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(4);
        TreeNode n4= new TreeNode(5);
        TreeNode n5 = new TreeNode(6);
        n0.left=n1;
        n0.right=n2;
        n1.left=n3;
        n1.right=n4;
        n2.left=n5;
        int i = countNodes(n0);
        System.out.println(i);

    }}
