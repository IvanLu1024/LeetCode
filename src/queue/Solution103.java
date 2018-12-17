package queue;

import org.junit.Test;
import tree.TreeNode;

import java.util.*;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回锯齿形层次遍历如下：
     *
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
 *
 * @author
 * @create 2018-11-11 21:27
 **/
public class Solution103 {
    private List<List<Integer>> res=new ArrayList<>();
    private Queue<TreeNode> queue=new LinkedList<>();
    private boolean flag=false;//记录是否翻转

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root==null){
            return res;
        }
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            while (size-->0){
                TreeNode cur = queue.poll();
                if (cur==null){
                    continue;
                }
                list.add(cur.val);
                queue.add(cur.left);
                queue.add(cur.right);
            }
            if (flag){
                Collections.reverse(list);
            }
            flag=!flag;
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
        n3.left=n4;
        n3.right=n5;
        List<List<Integer>> lists = zigzagLevelOrder(n1);
        System.out.println(lists);


    }
}
