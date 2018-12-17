package queue;

import javafx.util.Pair;
import org.junit.Test;
import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。

         例如:
         给定二叉树: [3,9,20,null,null,15,7],

         3
         / \
         9  20
         /  \
         15   7
         返回其层次遍历结果：

         [
         [3],
         [9,20],
         [15,7]
         ]
 *
 * @author
 * @create 2018-11-11 21:27
 **/
public class Solution102 {

    public List<List<Integer>> levelOrder(TreeNode root) {
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
            //当层数和结果的集合大小相等的时候，说明集合中还不存在这一层，所以需要新创建一个List
            if (level==res.size()){
                res.add(new ArrayList<Integer>());
            }
            res.get(level).add(node.val);
            //分别将左右子树入队
            if (node.left!=null){
                queue.offer(new Pair<>(node.left,level+1));
            }
            if (node.right!=null){
                queue.offer(new Pair<>(node.right,level+1));
            }

        }
        return res;
    }
    private List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> levelOrder1(TreeNode root){
        if (root==null){
            return  res;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            ArrayList<Integer> list = new ArrayList<>();
            int size = queue.size();
            while (size-->0){
                TreeNode cur = queue.poll();
                if (cur.left!=null){
                    queue.offer(cur.left);
                }
                if (cur.right!=null){
                    queue.offer(cur.right);
                }
                list.add(cur.val);
            }
            if (list.size()>0){
                res.add(list);
            }

        }
        return res;

    }






    @Test
    public void test(){
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(9);
        TreeNode n3 = new TreeNode(20);
        TreeNode n4 = new TreeNode(15);
        TreeNode n5 = new TreeNode(7);
        n1.left=n2;
        n1.right=n3;
        n2.left=n4;
        n3.right=n5;
        List<List<Integer>> lists = levelOrder1(n1);
        System.out.println(lists);
    }
}
