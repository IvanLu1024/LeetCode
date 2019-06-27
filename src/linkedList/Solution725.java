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
        for(int i=0;i<k;i++){
            //将余数均分
            int size=average_size+(i<left?1:0);
            if(size==0){
                res[i]=null;
            }else{
                ListNode tail=getTail(root,size);
                ListNode newRoot=tail.next;
                //将当前插入结果中的链表与原链表断开连接
                tail.next=null;
                res[i]=root;
                //从下一个位置继续
                root=newRoot;
            }
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

    //根据长度获取链表的相应的尾指针
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
