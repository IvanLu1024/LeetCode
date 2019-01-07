package tree;

import org.junit.Test;

/**
 * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
 *
 * 二叉树的根是数组中的最大元素。
 * 左子树是通过数组中最大值左边部分构造出的最大二叉树。
 * 右子树是通过数组中最大值右边部分构造出的最大二叉树。
 * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
 *
 * Example 1:
 *
 * 输入: [3,2,1,6,0,5]
 * 输入: 返回下面这棵树的根节点：
 *
 *       6
 *     /   \
 *    3     5
 *     \    /
 *      2  0
 *       \
 *        1
 * 注意:
 *
 * 给定的数组的大小在 [1, 1000] 之间。
 *
 */
public class Solution654 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int n = nums.length;
        if (n==0){
            return null;
        }
        //将整个数组构建出根为较大值的二叉树
        return construct(nums,0,n-1);
    }
    //利用数组构建二叉树，start：起始位置，end：结束位置
    private TreeNode construct(int[] nums,int start,int end){
        //此时nums[start,end]中不存在元素，则返回空
        if (end<start){
            return null;
        }

        int i = maxNumIndex(nums, start, end);
        //选择当前范围的最大值来作为根节点
        TreeNode root = new TreeNode(nums[i]);
        //再递归地调用从最大值的左侧生成左子树
        root.left=construct(nums,start,i-1);
        //同理生成右子树
        root.right=construct(nums,i+1,end);
        return root;
    }
    //获得数组指定范围内最大值的下标
    private int maxNumIndex(int[] nums,int start,int end){
        int index=start;
        int max=0;
        for (int i = start; i <=end ; i++) {
            if (nums[i]>max){
                max=nums[i];
                index=i;
            }
        }
        return index;
    }
    @Test
    public void test(){
        int[] nums={3,2,1,6,0,5};
        TreeNode root = constructMaximumBinaryTree(nums);
        System.out.println(root.val);
    }
}
