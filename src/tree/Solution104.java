package tree;

/**
 * @author
 * @create 2018-11-15 20:48
 **/
public class Solution104 {


    public int maxDepth(TreeNode root) {
        if (root==null){
            return 0;
        }

        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;



    }
}
