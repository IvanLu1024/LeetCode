package tree;

import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 *
 */
public class Solution98 {
    private ArrayList<Integer> res=new ArrayList<>();
    public boolean isValidBST(TreeNode root) {
        if (root==null){
            return true;
        }
        inOrder(root);
        for (int i=0;i<res.size()-1;i++) {
            if (res.get(i)>=res.get(i+1)){
                return false;
            }
        }
        return true;
    }
    private void inOrder(TreeNode root){
        if (root==null){
            return;
        }
        inOrder(root.left);
        res.add(root.val);
        inOrder(root.right);

    }

    public boolean isValidBST1(TreeNode root){
        return isValid(root,Integer.MIN_VALUE,Integer.MAX_VALUE);

    }
    private boolean isValid(TreeNode root,int min,int max){
        if (root==null){
            return true;
        }
        //当前结点不满足的时候返回false
        if (root.val<min||root.val>max){
            return false;
        }
        if (root.left!=null){
            if (root.left.val>=root.val){
                return false;
            }
        }
        if (root.right!=null){
            if (root.right.val<=root.val){
                return false;
            }
        }
        //分别将左右子树进行递归，左子树满足小于当前的结点的值（max：val-1是要确保左子树中不重复出现当前结点的值），
        // 右子树同理
        return isValid(root.left,min,root.val-1)&&isValid(root.right,root.val+1,max);
    }

    private  Integer pre=null;
    public boolean isValidBST2(TreeNode root){
        return inOrder1(root);
    }
    private boolean inOrder1(TreeNode root){
        if (root==null){
            return true;
        }
        if (!inOrder1(root.left)){
            return false;
        }

        //验证当前结点值是否满足中序遍历
        if (pre!=null&&root.val>=pre){
            return false;
        }
        //记录前一个结点的值
        pre=root.val;

        if (!inOrder1(root.right)){
            return false;
        }
        return true;
    }

    @Test
    public void test(){
        /*5,1,4,3,6*/
        int[] pre={5,1,4,3,6};
        /*1,5,3,4,6*/
        int[] in={1,5,3,4,6};
        TreeNode root = TreeNodeUtils.ConstructBinaryTree(pre, in);
        boolean validBST = isValidBST2(root);
        System.out.println(validBST);
    }

}
