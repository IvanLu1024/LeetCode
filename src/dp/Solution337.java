package dp;

import org.junit.Test;
import tree.TreeNode;
import tree.TreeNodeUtils;


public class Solution337 {


    public int rob(TreeNode root) {
        return tryRob(root,true);

    }

    private int tryRob(TreeNode root,boolean include){
        if (root==null){
            return 0;
        }

        //不选择当前结点
        int res=tryRob(root.right,true)+tryRob(root.left,true);
        if (include){
            //选择当前结点
            res=Math.max(res,root.val+tryRob(root.left,false)+tryRob(root.right,false));
        }
        return res;

    }

    public int rob1(TreeNode root) {
        int[] res = tryRob(root);
        return Math.max(res[0],res[1]);

    }

    private int[] tryRob(TreeNode root){
        if (root==null){
            return new int[2];
        }
        int[] leftResult = tryRob(root.left);
        int[] rightResult = tryRob(root.right);
        int[] res=new int[2];

        //下标为0，表示不选当前结点
        res[0]=leftResult[1]+rightResult[1];
        //下标为1，表示选择当前结点
        res[1]=Math.max(res[0],root.val+leftResult[0]+rightResult[0]);
        return res;
    }
    @Test
    public void test(){
        int[] pre={3,2,3,3,1};
        int[] in={2,3,3,3,1};
        TreeNode root = TreeNodeUtils.ConstructBinaryTree(pre, in);
        int r = rob1(root);
        System.out.println(r);
    }
}
