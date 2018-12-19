package tree;

import java.util.ArrayList;
import java.util.List;

public class Solution894 {
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> res=new ArrayList<>();
        //N为偶数则不存在这样的满二叉树
        if (N%2==0){
             return res;
        }
        //仅有一个根结点
        if (N==1){
            TreeNode root=new TreeNode(0);
            res.add(root);
            return res;
        }

        for (int i = 1; i < N; i+=2) {
            //获取左子树的集合
            List<TreeNode> leftNodes = allPossibleFBT(i);
            //获取右子树的集合，left+right=N-1
            List<TreeNode> rightNodes = allPossibleFBT(N - 1 - i);
            for (TreeNode left:leftNodes){
                for (TreeNode right:rightNodes){
                    //构造满二叉树
                    TreeNode root = new TreeNode(0);
                    root.left=left;
                    root.right=right;
                    res.add(root);
                }
            }
        }
        return res;
    }

}
