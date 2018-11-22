package tree;

import org.junit.Test;

import java.util.ArrayList;
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
        TreeNode head = new TreeNode(-1);
        head.right=root;
        TreeNode node = head;
        while (node.right!=null){
            node=node.right;
            if (node.left!=null){
                TreeNode end = node.left;
                while (end.right!=null){
                    end=end.right;
                }
                TreeNode t = node.right;
                node.right=node.left;
                node.left=null;
                end.right=t;
            }
        }

    }

    private List<TreeNode> dfs(TreeNode root){
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
        List<TreeNode> res = dfs(root);
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
