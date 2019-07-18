package tree;

import org.junit.Test;
import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution95 {
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> res=new ArrayList<>();
        if (n<=0){
            return res;
        }
        res=generare(1,n);
        return res;

    }
    private List<TreeNode> generare(int start,int end){
        List<TreeNode> treeNodes=new ArrayList<>();
        if (start>end){
            treeNodes.add(null);
            return treeNodes;
        }
        for (int i = start; i <=end ; i++) {
            List<TreeNode> left = generare(start, i-1);
            List<TreeNode> right = generare(i + 1, end);
            if (left.isEmpty()){
                left.add(null);
            }
            if (right.isEmpty()){
                right.add(null);
            }

            for (TreeNode l:left){
                for (TreeNode r:right){
                    TreeNode root = new TreeNode(i);
                    root.right=r;
                    root.left=l;
                    treeNodes.add(root);
                }
            }
        }
        return treeNodes;
    }
    @Test
    public void test(){
        List<TreeNode> res = generateTrees(3);
        System.out.println(res);
    }
}
