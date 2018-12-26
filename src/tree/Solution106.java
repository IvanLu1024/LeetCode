package tree;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 */
public class Solution106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder,postorder,0,inorder.length-1,0,postorder.length-1);
    }
    private TreeNode buildTree(int[] inorder, int[] postorder,int inStart,int inEnd,int postStart,int postEnd){
        if (inStart>inEnd||postStart>postEnd){
            return null;
        }
        int val = postorder[postEnd];
        TreeNode root = new TreeNode(val);
        int index=0;
        for (int i = inStart; i <=inEnd ; i++) {
            if (val==inorder[i]){
                index=i;
                break;
            }
        }
        //(index-inStart):左子树的结点数量
        root.left=buildTree(inorder,postorder,inStart,index-1,postStart,postStart+(index-inStart)-1);
        root.right=buildTree(inorder,postorder,index+1,inEnd,postStart+(index-inStart),postEnd-1);
        return root;
    }
}
