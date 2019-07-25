package tree;

/**
 * @Author Ivan 16:26
 * @Description TODO
 */
public class Solution572 {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s==null){
            return false;
        }
        return isSubtreeWithRoot(s,t)||isSubtree(s.left,t)||isSubtree(s.right,t);
    }
    private boolean isSubtreeWithRoot(TreeNode s, TreeNode t){
        if (s==null&&t==null){
            return true;
        }
        if (s==null||t==null){
            return false;
        }
        if (s.val!=t.val){
            return false;
        }
        return isSubtreeWithRoot(s.left,t.left)&&isSubtreeWithRoot(s.right,t.right);

    }
}
