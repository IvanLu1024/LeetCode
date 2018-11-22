package tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

     说明: 叶子节点是指没有子节点的节点。

     示例:
     给定如下二叉树，以及目标和 sum = 22，

           5
          / \
         4   8
         /   / \
         11  13  4
         /  \    / \
         7    2  5   1
     返回:

     [
     [5,4,11,2],
     [5,8,4,5]
     ]
 *
 *
 */
public class Solution113 {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res=new ArrayList<>();

        if (root==null){
            return res;
        }
        List<Integer> tres=new LinkedList<>();
        dfs(root,sum,0,tres,res);
        return res;
    }

    //深度遍历，回溯法
    private void dfs(TreeNode root,int sum,int tSum,List<Integer> tres,List<List<Integer>> res){
        if (root == null){
            return;
        }
        tres.add(root.val);
        tSum+=root.val;

        //当寻找叶子结点的时候
        if (root.left==null&&root.right==null){
            //**注意：需要实例化
            //当有目标值的时候，将其添加到结果集合中
            if (tSum==sum){
                res.add(new ArrayList<>(tres));
            }
        }else {
            //遍历左子树
            if (root.left!=null){
                dfs(root.left,sum,tSum,tres,res);
            }
            //遍历右子树
            if (root.right!=null){
                dfs(root.right,sum,tSum,tres,res);
            }

        }
        //回溯
        tres.remove(tres.size()-1);
    }

    @Test
    public void test(){
        int[] pre={5,4,11,7,2,8,13,4,5,1};
        int[] in={7,11,2,4,5,13,8,5,4,1};
        TreeNode root = TreeNodeUtils.ConstructBinaryTree(pre, in);
        int sum=22;
        List<List<Integer>> res = pathSum(root, sum);
        System.out.println(res);
    }

}
