package tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 1
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 3
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
 *
 *
 */
public class Solution230 {
    private List<Integer> res=new ArrayList<>();
    public int kthSmallest(TreeNode root, int k) {
        inOrder(root);
        return res.get(k-1);
    }
    private void inOrder(TreeNode root){
        if (root==null){
            return ;
        }
        inOrder(root.left);
        res.add(root.val);
        inOrder(root.right);
    }
    public int kthSmallest1(TreeNode root, int k){
        InOrder(root, k);
        return result;

    }
    private int count=0;
    private int result;

    private void InOrder(TreeNode root,int k){

        if (root.left!=null){
            InOrder(root.left,k);
        }
        count++;
        if (count==k){
            result=root.val;
            return;
        }
        if (root.right!=null){
            InOrder(root.right,k);
        }

    }
    @Test
    public void test(){
        int[] pre={5,3,2,1,4,6};
        int[] in={1,2,3,4,5,6};
        TreeNode root = TreeNodeUtils.ConstructBinaryTree(pre, in);
        int i = kthSmallest1(root, 3);
        System.out.println(i);
    }

}
