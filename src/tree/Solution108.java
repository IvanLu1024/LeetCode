package tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 *
 */
public class Solution108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums==null||nums.length==0){
            return null;
        }
        return createTreeNode(nums,0,nums.length-1);
    }
    private TreeNode createTreeNode(int[]nums,int l,int h){
        //特别注意，边界条件
        if (l>h){
            return null;
        }
        int mid=l+(h-l)/2;
        TreeNode root=new TreeNode(nums[mid]);
        root.left=createTreeNode(nums,l,mid-1);
        root.right=createTreeNode(nums,mid+1,h);
        return root;
    }



}
