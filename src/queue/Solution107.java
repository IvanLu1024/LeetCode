package queue;

import javafx.util.Pair;
import org.junit.Test;
import tree.TreeNode;
import utils.TreeNodeUtils;

import java.util.*;

/**
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *
 */
public class Solution107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res=new ArrayList<>();
        if (root==null){
            return res;
        }
        Queue<Pair<TreeNode,Integer>> queue=new LinkedList<>();
        queue.add(new Pair<>(root,0));
        while (!queue.isEmpty()){
            Pair<TreeNode, Integer> poll = queue.poll();
            TreeNode node = poll.getKey();
            Integer level = poll.getValue();
            if (level==res.size()){
                res.add(new ArrayList<>());
            }
            res.get(level).add(node.val);
            if (node.left!=null){
                 queue.add(new Pair<>(node.left,level+1));
            }
            if (node.right!=null){
                 queue.add(new Pair<>(node.right,level+1));
            }

        }
        Collections.reverse(res);
        return res;
    }
    @Test
    public void test(){
        int[] pre={3,9,20,15,7};
        int[] in={9,3,15,20,7};
        TreeNode root = TreeNodeUtils.ConstructBinaryTree(pre, in);
        List<List<Integer>> res = levelOrderBottom(root);
        System.out.println(res);

    }
}
