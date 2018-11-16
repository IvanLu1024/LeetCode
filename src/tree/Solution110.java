package tree;

public class Solution110 {

    public boolean isBalanced(TreeNode root) {
        if (root==null){
            return true;
        }
        int right = height(root.right);
        int left = height(root.left);
        if (right-left>1||right-left<-1){
            return false;
        }else {
            return isBalanced(root.left)&&isBalanced(root.right);
        }

    }
    private int height(TreeNode root){
        if (root==null){
            return 0;
        }
        return Math.max(height(root.left),height(root.right))+1;
    }


}
