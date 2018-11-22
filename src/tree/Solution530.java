package tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个所有节点为非负值的二叉搜索树，求树中任意两节点的差的绝对值的最小值。
 *
 * 示例 :
 *
 * 输入:
 *
 *    1
 *     \
 *      3
 *     /
 *    2
 *
 * 输出:
 * 1
 *
 * 解释:
 * 最小绝对差为1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 * 注意: 树中至少有2个节点。
 *
 *
 */
public class Solution530 {
    public int getMinimumDifference(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        inOrder(root,res);
        int min =Integer.MAX_VALUE;
        for (int i = 0; i < res.size() - 1; i++) {
            int num= Math.abs(res.get(i)-res.get(i+1));
            min=Math.min(min,num);
        }
        return min;
    }
    private void inOrder(TreeNode root, List<Integer> res){
        if (root==null){
            return;
        }
        inOrder(root.left,res);
        res.add(root.val);
        inOrder(root.right,res);
    }

    private int pre=Integer.MAX_VALUE;
    private int min=Integer.MAX_VALUE;
    public int getMinimumDifference1(TreeNode root){
        InOrder(root);
        return min;

    }
    private void InOrder(TreeNode root){
        if (root==null){
            return;
        }
        InOrder(root.left);
        int diff=Math.abs(pre-root.val);
        min=Math.min(diff,min);
        pre=root.val;
        InOrder(root.right);
    }

    @Test
    public void test(){
        int[] pre={1,5,3};
        int[] in={1,3,5};
        TreeNode root = TreeNodeUtils.ConstructBinaryTree(pre, in);
        int min = getMinimumDifference1(root);
        System.out.println(min);
    }
}
