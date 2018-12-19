package tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Solution872 {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1=new ArrayList<>();
        List<Integer> l2=new ArrayList<>();
        leaves(root1,l1);
        leaves(root2,l2);
        if (l1.size()!=l2.size()){
            return false;
        }
        int n = l1.size();
        for (int i = 0; i < n; i++) {
            if (l1.get(i)!=l2.get(i)){
                return false;
            }
        }
        return true;

    }
    //获得树的所有叶子结点的集合
    private void leaves(TreeNode root,List<Integer> res){
        if (root==null){
            return ;
        }
        if (root.left==null&&root.right==null){
            res.add(root.val);
        }
        leaves(root.right,res);
        leaves(root.left,res);
    }
    @Test
    public void test(){
        int[] pre={1,2,4,3,5,6};
        int[] in={4,2,1,5,3,6};
        TreeNode root1 = TreeNodeUtils.ConstructBinaryTree(pre, in);
        List<Integer> l1=new ArrayList<>();
        leaves(root1,l1);
        System.out.println(l1);
    }

}
