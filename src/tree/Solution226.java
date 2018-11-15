package tree;

/**
 * @author
 * @create 2018-11-15 21:32
 **/
public class Solution226 {

    public TreeNode invertTree(TreeNode root) {
        if (root==null){
            return null;
        }
        TreeNode t=root.right;
        root.right=root.left;
        root.left=t;
        invertTree(root.left);
        invertTree(root.right);
        return root;

    }
    private void swap(TreeNode t1,TreeNode t2){
        TreeNode t=t1;
        t1=t2;
        t2=t;
    }
}
