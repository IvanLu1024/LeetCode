package linkedList;

import org.junit.Test;
import utils.LinkedListUtils;

/**
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 示例 1:
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 */
public class Solution148 {
    //自顶向下的归并排序
    public ListNode sortList(ListNode head) {
        if (head==null||head.next==null){
            return head;
        }
        //首先将链表折半
        ListNode fast=head.next,slow=head;
        while (fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        ListNode head2=slow.next;
        //将前后断开连接
        slow.next=null;
        head = sortList(head);  //前半部分
        head2=sortList(head2);  //后半部分
        return merge(head,head2);
    }
    //合并两个已经排序好的链表
    private ListNode merge(ListNode a,ListNode b){
        ListNode dummyHead=new ListNode(-1);
        ListNode p1=a,p2=b,p=dummyHead;
        while (p1!=null&&p2!=null){
            if (p1.val<p2.val){
                p.next=p1;
                p=p.next;
                p1=p1.next;
                p.next=null;
            }else {
                p.next=p2;
                p=p.next;
                p2=p2.next;
                p.next=null;
            }
        }
        if (p1!=null){
            p.next=p1;
        }
        if (p2!=null){
            p.next=p2;
        }
        return dummyHead.next;

    }
   /* @Test
    public void test(){
        int[] arr={4,2,1,3};
        ListNode head = LinkedListUtils.createLinkedList(arr, arr.length);
        LinkedListUtils.printLinkedList(head);
        ListNode res = sortList(head);
        LinkedListUtils.printLinkedList(res);
    }*/
}
