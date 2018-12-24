package linkedList;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Solution725 {
    public ListNode[] splitListToParts(ListNode root, int k) {
        int len=listNodeLen(root);
        int average_size = len / k;
        int left = len % k;
        ListNode[] res=new ListNode[k];
        List<ListNode> list=new ArrayList<>();
        for (int i = 0; i < k; i++) {
            //将余数均分
            int size=average_size+(i<left?1:0);
            if (size==0){
                list.add(null);
            }else {

                ListNode tail = getTail(root, size);
                //记录一下，下一次循环的起点
                ListNode newRoot = tail.next;
                tail.next=null;
                list.add(root);
                root=newRoot;
            }

        }
        for (int i = 0; i < k; i++) {
            res[i]=list.get(i);
        }
        return res;
    }
    private int listNodeLen(ListNode root){
        int len=0;
        while (root!=null){
            len++;
            root=root.next;
        }
        return len;
    }

    //获取新的头结点
    private ListNode getTail(ListNode root,int size){
        for (int i = 1; i < size; i++) {
            assert root!=null;
            root=root.next;
        }
        return root;
    }
    @Test
    public void test(){
        int[] arr={1,2,3,4,5,6,7};
        ListNode root = LinkedListUtils.createLinkedList(arr, arr.length);
        ListNode[] listNodes = splitListToParts(root, 3);
        for (ListNode listNode:listNodes){
            System.out.println(listNode);
        }
        //System.out.println(listNodes);

    }
}
