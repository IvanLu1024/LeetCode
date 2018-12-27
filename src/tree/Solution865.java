package tree;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 提示：
 *
 * 树中结点的数量介于 1 和 500 之间。
 * 每个结点的值都是独一无二的。
 */
public class Solution865 {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        //存储每个结点到底部的深度（从根到结点的最短距离）
        Map<TreeNode,Integer> depthMap=new HashMap<>();
        dfs(root,0,depthMap);
        int maxDepth=0;
        for (int i:depthMap.values()){
            maxDepth=Math.max(maxDepth,i);
        }
        TreeNode res=getRes(root,maxDepth,depthMap);
        return res;
    }
    //计算每个结点从根结点到该结点的距离
    private void dfs(TreeNode root,int d,Map<TreeNode,Integer> depthMap){
        if (root==null){
            return;
        }
        depthMap.put(root,d);
        dfs(root.left,d+1,depthMap);
        dfs(root.right,d+1,depthMap);
    }
    //获得最小子树
    private TreeNode getRes(TreeNode node,int maxDepth,Map<TreeNode,Integer> depthMap){
        if (node==null){
            return null;
        }
        //递归终止条件，寻找到最大深度的结点
        if (depthMap.get(node)==maxDepth){
            return node;
        }
        TreeNode left = getRes(node.left,maxDepth,depthMap);
        TreeNode right = getRes(node.right,maxDepth,depthMap);

        //若左右子树均不空则返回其公共祖先
        if (left!=null&&right!=null){
            return node;
        }
        if (left!=null){
            return left;
        }
        if (right!=null){
            return right;
        }
        return null;
    }


    public TreeNode subtreeWithAllDeepest1(TreeNode root){
        if (root==null){
            return null;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        if (left==right){
            return root;
        }else if (left>right){
            return subtreeWithAllDeepest1(root.left);
        }else {
            return subtreeWithAllDeepest1(root.right);
        }
    }


    private int maxDepth(TreeNode root) {
        if (root==null){
            return 0;
        }
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }
    @Test
    public void test(){
        int[] in={6,5,7,2,4,3,0,1,8};
        int[] pre={3,5,6,2,7,4,1,0,8};
       /* int[] in={1,2,0,3};
        int[] pre={0,1,2,3};*/
        TreeNode root = TreeNodeUtils.ConstructBinaryTree(pre, in);
        TreeNode treeNode = subtreeWithAllDeepest1(root);
        System.out.println(treeNode.val);
    }
}
