package tree;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 */
public class Solution105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
    }
    private TreeNode buildTree(int[] preorder,int[] inorder,int preStart,int preEnd,int inStart,int inEnd){
        if (preStart>preEnd||inStart>inEnd){
            return null;
        }
        int val = preorder[preStart];
        TreeNode root = new TreeNode(val);
        //根节点的下标
        int index=0;
        for (int i = inStart; i <= inEnd; i++) {
            if (val==inorder[i]){
                index=i;
                break;
            }
        }
        //左子树，preorder[preStart+1,preStart+(index-inStart)],inorder[inStart,index-1]
        // (index-inStart):左子树的结点数量
        root.left=buildTree(preorder,inorder,preStart+1,preStart+(index-inStart),inStart,index-1);
        //右子树，
        root.right=buildTree(preorder,inorder,preStart+(index-inStart)+1,preEnd,index+1,inEnd);
        return root;
    }
}
