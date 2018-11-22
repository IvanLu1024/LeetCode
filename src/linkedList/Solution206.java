package linkedList;

import org.junit.Test;

/**
 * 反转链表
 *
 * @author
 * @create 2018-10-28 20:20
 **/
public class Solution206 {

    public ListNode reverseList(ListNode head) {
        if (head==null||head.next==null){
            return head;
        }
        ListNode pre=null;
        ListNode cur=head;
        while (cur!=null){
            //这样可以确保next不抛出空指针异常
            ListNode next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;

        }
        return pre;
    }
    @Test
    public void test(){
        int[] arr={1,2,3,4,5};
        int n=arr.length;
        ListNode head = linkedList.Test.createLinkedList(arr, n);
        linkedList.Test.printLinkedList(head);
        ListNode res = reverseList(head);

        linkedList.Test.printLinkedList(res);
    }
}
