package linkedList;

import org.junit.Test;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。

     说明:
     1 ≤ m ≤ n ≤ 链表长度。

     示例:

     输入: 1->2->3->4->5->NULL, m = 2, n = 4
     输出: 1->4->3->2->5->NULL
 *
 * 反转链表（2）
 *
 * @author
 * @create 2018-10-28 20:38
 **/
public class Solution92 {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode newHead =new ListNode(0);
        newHead.next=head;
        ListNode pre=newHead;
        //首先寻找到开始反转的位置
        for (int i = 1; i < m; i++) {
            pre=pre.next;
        }
        ListNode cur=pre.next;

        for (int i = 0; i < n - m; i++) {
            ListNode post=cur.next;

            cur.next=post.next;
            post.next=pre.next;
            pre.next=post;
        }


        return newHead.next;
    }
    @Test
    public void test(){

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode head=n1;
        n1.next=n2;
        n2.next=n3;
        n3.next=n4;
        n4.next=n5;
        ListNode h = reverseBetween(head, 2, 4);
        System.out.println(h);

    }
}
