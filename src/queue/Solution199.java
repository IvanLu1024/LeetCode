package queue;

import org.junit.Test;
import tree.TreeNode;
import utils.TreeNodeUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 */
public class Solution199 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        if (root==null){
            return res;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            //统计这一层的结点数
            int currentNum = queue.size();
            while (currentNum>0){
                TreeNode poll = queue.poll();
                currentNum--;
                //入队顺序必须保持从左到右
                if (poll.left!=null){
                    queue.offer(poll.left);
                }
                if (poll.right!=null){
                    queue.offer(poll.right);
                }
                //此时最后一个出队的元素则是每层最右边的结点
                if (currentNum==0){
                    res.add(poll.val);
                }
            }
        }
        return res;
    }

    @Test
    public void test(){
        int[] pre={3,9,20,15,7};
        int[] in={9,3,15,20,7};
        TreeNode root = TreeNodeUtils.ConstructBinaryTree(pre, in);
        List<Integer> res = rightSideView(root);
        System.out.println(res);

    }
}
