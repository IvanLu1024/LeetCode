package tree;

import org.junit.Test;
import utils.LinkedListUtils;
import utils.ListNode;
import utils.TreeNodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 */
public class Solution109 {
    //首先将链表转化为数组或者集合，这样就转化为了108题
    public TreeNode sortedListToBST(ListNode head) {
        if (head==null){
            return null;
        }
        List<Integer> list=new ArrayList<>();
        ListNode cur = head;
        while (cur!=null){
            list.add(cur.val);
            cur=cur.next;
        }
        return createTree(list,0,list.size()-1);
    }
    private TreeNode createTree(List<Integer> list,int l,int r){
        //此时表明已经不存在这个结点
        if (l>r){
            return null;
        }
        int mid = l+(r-l)/2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left=createTree(list,l,mid-1);
        root.right=createTree(list,mid+1,r);
        return root;
    }
    @Test
    public void test(){
        int[] arr={-10, -3, 0, 5, 9};
        ListNode head = LinkedListUtils.createLinkedList(arr, arr.length);
        TreeNode root = sortedListToBST(head);
        ArrayList<ArrayList<Integer>> res = tree.TreeNodeUtils.Print(root);
        System.out.println(res);
    }
}
