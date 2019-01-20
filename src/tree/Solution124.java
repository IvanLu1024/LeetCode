package tree;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 给定一个非空二叉树，返回其最大路径和。

 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。

 示例 1:

 输入: [1,2,3]

   1
  / \
 2   3

 输出: 6

 示例 2:

 输入: [-10,9,20,null,null,15,7]

  -10
  / \
 9  20
   /  \
  15   7

 输出: 42

 */
public class Solution124 {
    private int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    //深度遍历
    private int dfs(TreeNode node){
        if (node==null){
            return 0;
        }
        int cur = node.val;     //当前路径的值
        int left = dfs(node.left);
        int right = dfs(node.right);
        if (left>0){
            cur+=left;
        }
        if (right>0){
            cur+=right;
        }
        //记录路径的最大值
        maxSum = Math.max(cur,maxSum);
        //最多只在孩子里面选取一个结点
        //记录从当前结点所经过能得到的最大值
        int curMax = Math.max(node.val,Math.max(node.val+left,node.val+right));
        return curMax;
    }
    @Test
    public void test(){
        int[] pre={-10,9,20,15,7};
        int[] in={9,-10,15,20,7};
        TreeNode root = TreeNodeUtils.ConstructBinaryTree(pre, in);
        int i = maxPathSum(root);
        System.out.println(i);
    }
}
