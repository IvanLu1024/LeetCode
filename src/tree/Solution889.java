package tree;

import org.junit.Test;

/**
 * 返回与给定的前序和后序遍历匹配的任何二叉树。
 *
 *  pre 和 post 遍历中的值是不同的正整数。
 *
 *
 *
 * 示例：
 *
 * 输入：pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
 * 输出：[1,2,3,4,5,6,7]
 *
 *
 * 提示：
 *
 * 1 <= pre.length == post.length <= 30
 * pre[] 和 post[] 都是 1, 2, ..., pre.length 的排列
 * 每个输入保证至少有一个答案。如果有多个答案，可以返回其中一个。
 *
 */
public class Solution889 {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return build(pre,post,0,pre.length-1,0,post.length-1);
    }
    private TreeNode build(int[] pre,int[] post,int preStart,int preEnd,int postStart,int postEnd){
        if (preStart>preEnd){
            return null;
        }
        
        if (preStart==preEnd){
            return new TreeNode(pre[preStart]);
        }

        int val = pre[preStart];
        TreeNode root = new TreeNode(val);
        //在前序中寻找左子树的范围
        int prePost=preStart;
        for (int i = preStart; i <=preEnd ; i++) {
            if (post[postEnd-1]==pre[i]){
                prePost=i;
                break;
            }
        }

        //在中序中寻找左子树的范围
        int postPost=postStart;
        for (int i = postStart; i <= postEnd; i++) {
            if (pre[preStart+1]==post[i]){
                postPost=i;
                break;
            }
        }
        //这时只含有左子树的时候
        if (pre[prePost]==post[postPost]){
            root.left=build(pre,post,preStart+1,preEnd,postStart,postEnd-1);
        }
        //存在左右子树的时候
        else {
            root.left=build(pre,post,preStart+1,prePost-1,postStart,postPost);
            root.right=build(pre,post,prePost,preEnd,postPost+1,postEnd-1);
        }

        return root;
    }
    @Test
    public void test(){
        int[] pre={1,2,4,5,3,6,7};
        int[] post={4,5,2,6,7,3,1};
        TreeNode root = constructFromPrePost(pre, post);
        System.out.println(root.val);
    }
}
