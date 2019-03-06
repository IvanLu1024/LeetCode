package tree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，原地将它展开为链表。
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 *
 */
public class Solution114 {


    public void flatten(TreeNode root) {
        if(root==null){
            return ;
        }
        TreeNode node=root;
        //不断地将右子树与放入左子树中与根节点相邻的结点的右边
        while (node!=null){
            if (node.left!=null) {
                TreeNode left = node.left;
                //寻找左子树中最右边的结点就是和根节点相邻的结点
                while (left.right != null) {
                    left = left.right;
                }

                //将右子树放入该节点的右侧
                left.right = node.right;
                //将左子树整体放入根节点的右边
                node.right = node.left;
                node.left = null;
            }
            node = node.right;
        }
    }

    private List<TreeNode> bfs(TreeNode root){
        List<TreeNode> res=new LinkedList<>();
        if (root==null){
            return res;
        }
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        while (!stack.empty()){
            TreeNode node = stack.pop();
            res.add(node);
            if (node.right!=null){
                stack.push(node.right);
            }
            if (node.left!=null){
                stack.push(node.left);
            }
            node.left=null;

        }
        return res;
    }

    public void flatten1(TreeNode root) {
        List<TreeNode> res = bfs(root);
        for (int i = 0; i < res.size()-1; i++) {
            res.get(i).right=res.get(i+1);
        }

    }

    @Test
    public void test(){
        int[] pre={1,2,3,4,5,6};
        int[] in={3,2,4,1,5,6};
        TreeNode root = TreeNodeUtils.ConstructBinaryTree(pre, in);
        flatten1(root);
        List<Integer> integers = TreeNodeUtils.preorderTraversal(root);
        System.out.println(integers);
    }

}
