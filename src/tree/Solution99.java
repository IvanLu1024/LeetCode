package tree;

import org.junit.Test;

import java.util.ArrayList;

public class Solution99 {
    private TreeNode pre;
    private TreeNode first,second;

    public void recoverTree(TreeNode root) {
        pre=null;
        first=null;
        second=null;
        inOrder(root);
        int t=first.val;
        first.val=second.val;
        second.val=t;

    }
    private void inOrder(TreeNode root){
        if (root==null){
            return;
        }
        inOrder(root.left);
        //此时发生了逆序
        if (pre!=null&&pre.val>root.val){
            if (first==null){
                first=pre;
            }
            second=root;
        }
        pre=root;
        inOrder(root.right);
    }
    @Test
    public void test(){
        int[] pre={1,3,2};
        int[] in={3,2,1};
        TreeNode root = TreeNodeUtils.ConstructBinaryTree(pre, in);
        ArrayList<ArrayList<Integer>> r1 = TreeNodeUtils.Print(root);
        System.out.println(r1);
        recoverTree(root);
        ArrayList<ArrayList<Integer>> r2 = TreeNodeUtils.Print(root);
        System.out.println(r2);
    }

}
