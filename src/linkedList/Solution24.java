package linkedList;

import org.junit.Test;

/**
 *给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * 说明:
 *
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 */
public class Solution24 {

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next=head;
        ListNode p=dummy;
        //注意循环的条件
        while (p.next!=null&&p.next.next!=null){
            ListNode n1=p.next;
            ListNode n2=p.next.next;
            ListNode next=n2.next;
            p.next=n2;
            n2.next=n1;
            n1.next=next;
            //p现在需要指向下一次需要交换位置的前一个
            p=n1;
        }
        return dummy.next;

    }
    @Test
    public void test(){
        int[] arr={1,2,3,4};
        ListNode head = linkedList.Test.createLinkedList(arr, arr.length);
        linkedList.Test.printLinkedList(head);
        ListNode res = swapPairs(head);
        linkedList.Test.printLinkedList(res);



    }
}
