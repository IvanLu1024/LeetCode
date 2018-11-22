package tree;

import org.junit.Test;

import java.util.List;

/*
给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。
返回二叉搜索树（有可能被更新）的根节点的引用。

一般来说，删除节点可分为两个步骤：

首先找到需要删除的节点；
如果找到了，删除它。
说明： 要求算法时间复杂度为 O(h)，h 为树的高度。

示例:

    root = [5,3,6,2,4,null,7]
    key = 3

        5
       / \
      3   6
     / \   \
    2   4   7

    给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。

    一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。

        5
       / \
      4   6
     /     \
    2       7

    另一个正确答案是 [5,2,6,null,4,null,7]。

        5
       / \
      2   6
       \   \
        4   7

 */
public class Solution450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root==null){
            return null;
        }
        if (key<root.val){
            root.left=deleteNode(root.left,key);
            return root;
        }
        else if (key>root.val){
            root.right=deleteNode(root.right,key);
            return root;
        }
        else {
            //待删除结点左子树为空的时候，直接将其右子树返回
            if (root.left==null){
                TreeNode rightNode = root.right;
                root.right=null;
                return rightNode;
            }

            //待删除结点右子树为空的时候，直接将其左子树返回
            if (root.right==null){
                TreeNode leftNode = root.left;
                root.left=null;
                return leftNode;
            }

            //待删除结点两侧均有子树的时候，将其右子树中的最小值替换其位置即可
            TreeNode successor = minNode(root.right);   //寻找右子树中的最小值的结点
            successor.right=delMinNode(root.right);     //将该结点的右侧放入删除该结点的部分
            successor.left=root.left;       //将该结点的左侧放入待删除结点的左子树
            root.left=root.right=null;
            return successor;

        }
    }
    //寻找最小值
    private TreeNode minNode(TreeNode root){
        if (root==null){
            return null;
        }
        if (root.left==null){
            return root;
        }
        return minNode(root.left);
    }
    //删除最小值，并返回删除后的根节点
    private TreeNode delMinNode(TreeNode root){
        //当左侧为空的时候，删除当前结点
        if (root.left==null){
            TreeNode rightNode = root.right;
            root.right=null;
            return rightNode;
        }
        root.left = delMinNode(root.left);
        return root;
    }
    @Test
    public void test(){
        int[] pre={5,3,2,4,6};
        int[] in={2,3,4,5,6};
        TreeNode root = TreeNodeUtils.ConstructBinaryTree(pre, in);
        int k=3;
        TreeNode res = deleteNode(root, k);
        List<Integer> integers = TreeNodeUtils.inorderTraversal(res);
        System.out.println(integers);
    }
}
