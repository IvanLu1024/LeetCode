package tree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 *给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。

         例如，从根到叶子节点路径 1->2->3 代表数字 123。

         计算从根到叶子节点生成的所有数字之和。

         说明: 叶子节点是指没有子节点的节点。

         示例 1:

         输入: [1,2,3]
         1
         / \
         2   3
         输出: 25
         解释:
         从根到叶子节点路径 1->2 代表数字 12.
         从根到叶子节点路径 1->3 代表数字 13.
         因此，数字总和 = 12 + 13 = 25.
         示例 2:

         输入: [4,9,0,5,1]
         4
         / \
         9   0
         / \
         5   1
         输出: 1026
         解释:
         从根到叶子节点路径 4->9->5 代表数字 495.
         从根到叶子节点路径 4->9->1 代表数字 491.
         从根到叶子节点路径 4->0 代表数字 40.
         因此，数字总和 = 495 + 491 + 40 = 1026.
 *
 *
 */
public class Solution129 {
    //和257相同的思路
    public int sumNumbers(TreeNode root) {
        List<String> res = RootToLeafPaths(root);
        int result=0;
        for (String s : res) {
            int i = Integer.valueOf(s);
            result+=i;
        }
        return result;

    }

    private List<String> RootToLeafPaths(TreeNode root) {
        List<String> res=new LinkedList<>();
        if (root==null){
            return res;
        }
        //当寻找到叶子结点的时候，就将叶子结点加入集合
        if (root.left==null&&root.right==null){
            res.add(String.valueOf(root.val));
        }
        //分别添加左右子树，注意递归调用的语义！

        //获得左子树结点从根到叶子所有的路径
        List<String> leftStr = RootToLeafPaths(root.left);
        for (String s : leftStr) {
            res.add(String.valueOf(root.val)+s);
        }
        //获得右子树结点从根到叶子所有的路径
        List<String> rightStr = RootToLeafPaths(root.right);
        for (String s : rightStr) {
            res.add(String.valueOf(root.val)+s);
        }

        return res;
    }


    //深度遍历
    public int sumNumbers1(TreeNode root) {
        if (root==null){
            return 0;
        }
        int results=dfs(root,0);
        return results;


    }
    private int dfs(TreeNode root,int sum){
        if (root==null){
            return 0;
        }
        if (root.right==null&&root.left==null){
            return sum*10+root.val;
        }
        return dfs(root.right,10*sum+root.val)+dfs(root.left,10*sum+root.val);
    }
    @Test
    public void test(){
        int[] pre={4,9,5,1,0};
        int[] in={5,9,1,4,0};
        TreeNode root = TreeNodeUtils.ConstructBinaryTree(pre, in);
        int res = sumNumbers1(root);
        System.out.println(res);

    }
}
