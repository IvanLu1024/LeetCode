package tree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。

 说明: 叶子节点是指没有子节点的节点。

 示例:

 输入:

 1
 /   \
 2     3
 \
 5

 输出: ["1->2->5", "1->3"]

 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 *
 */
public class Solution257 {
    public List<String> binaryTreePaths(TreeNode root) {
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
        List<String> leftStr = binaryTreePaths(root.left);
        for (String s : leftStr) {
            res.add(String.valueOf(root.val)+"->"+s);
        }
        //获得右子树结点从根到叶子所有的路径
        List<String> rightStr = binaryTreePaths(root.right);
        for (String s : rightStr) {
            res.add(String.valueOf(root.val)+"->"+s);
        }
        return res;
    }
    @Test
    public void test(){
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(1);
        n1.left=n2;
        n1.right=n3;
        n2.right=n4;
        List<String> strings = binaryTreePaths(n1);
        System.out.println(strings);
    }

}
