package tree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Ivan 19:20
 * @Description TODO
 */
public class CompeteTree {

    //判断是否为完全二叉树
    /*任意的一个二叉树，都可以补成一个满二叉树。这样中间就会有很多空洞。在广度优先遍历的时候，如果是满二叉树，
    或者完全二叉树，这些空洞是在广度优先的遍历的末尾，所以，但我们遍历到空洞的时候，整个二叉树就已经遍历完成了。
    而如果，是非完全二叉树，我们遍历到空洞的时候，就会发现，空洞后面还有没有遍历到的值。这样，只要根据是否遍历到空洞，
    整个树的遍历是否结束来判断是否是完全的二叉树。
*/
    public boolean isCompete(TreeNode root){
        if (root==null)
            return true;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        TreeNode t;
        while ((t=queue.poll())!=null){
            queue.offer(t.left);
            queue.offer(t.right);
        }
        while (!queue.isEmpty()){
            t=queue.poll();
            if (t!=null){
                return false;
            }
        }
        return true;
    }
    @Test
    public void test(){
        int[] pre={1,2,4,3,5};
        int[] in={4,2,1,5,3};
        TreeNode root = TreeNodeUtils.ConstructBinaryTree(pre, in);
        boolean b = isCompete(root);
        System.out.println(b);
    }
}
