package tree;

import org.junit.Test;

/**
 *给定一个二叉树，它的每个结点都存放着一个整数值。
 *
 * 找出路径和等于给定数值的路径总数。
 *
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 *
 * 示例：
 * 1,null,2,null,3,null,4,null,5
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * 返回 3。和等于 8 的路径有:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 *
 *
 */
public class Solution437 {

    // 在以root为根节点的二叉树中,寻找和为sum的路径,返回这样的路径个数
    public int pathSum(TreeNode root, int sum) {
        if (root==null){
            return 0;
        }
        int res=findPathSum(root,sum);
        res+=pathSum(root.left,sum);
        res+=pathSum(root.right,sum);
        return res;
    }
    // 在以node为根节点的二叉树中,寻找包含node的路径,和为sum
    // 返回这样的路径个数
    private int findPathSum(TreeNode node,int sum){
        if (node==null){
            return 0;
        }
        int count=0;
        if (node.val==sum){
            count+=1;
        }
        count+=findPathSum(node.left,sum-node.val);
        count+=findPathSum(node.right,sum-node.val);
        return count;
    }
    @Test
    public void test(){
        int[] pre={10,5,3,3,-2,2,1,-3,11};
        int[] in={3,3,-2,5,2,1,10,-3,11};
        TreeNode root = TreeNodeUtils.ConstructBinaryTree(pre, in);
        int sum=8;
        int res = pathSum(root, sum);
        System.out.println(res);


    }


}
