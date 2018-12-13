package linkedList;

import org.junit.Test;

/**
 *
 */
public class Solution143 {
    public void reorderList(ListNode head) {
        if (head==null||head.next==null||head.next.next==null){
            return ;
        }
        ListNode fast=head,slow=head;
        while (fast.next!=null&&fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        ListNode secend=slow.next;
        slow.next=null;
        secend=reverse(secend);

        ListNode first=head;
        while (secend!=null){
            ListNode next=first.next;
            first.next=secend;
            secend=secend.next;
            first = first.next;
            first.next = next;
            first = first.next;
        }
    }

    private ListNode reverse(ListNode head){
        if (head==null||head.next==null){
            return head;
        }
        ListNode pre=null;
        ListNode cur=head;
        while (cur!=null){
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
        ListNode head = LinkedListUtils.createLinkedList(arr, arr.length);
        reorderList(head);
        LinkedListUtils.printLinkedList(head);
    }
}
