package tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Solution897 {
    public TreeNode increasingBST(TreeNode root) {
        if (root==null){
            return root;
        }
        List<Integer> res=new ArrayList<>();
        inOrder(root,res);
        root=new TreeNode(res.get(0));
        TreeNode head=root;
        for (int i = 1; i < res.size(); i++) {
            root.right=new TreeNode(res.get(i));
            root.left=null;
            root=root.right;
        }
        return head;

    }
    private void inOrder(TreeNode root,List<Integer> res){
        if (root==null){
            return;
        }
        inOrder(root.left,res);
        res.add(root.val);
        inOrder(root.right,res);
    }
    @Test
    public void test(){
        int[] pre={5,3,2,4,6};
        int[] in={2,3,4,5,6};
        TreeNode root = TreeNodeUtils.ConstructBinaryTree(pre, in);
        TreeNode res = increasingBST(root);
        ArrayList<ArrayList<Integer>> r = TreeNodeUtils.Print(res);
        System.out.println(r);

    }
}
