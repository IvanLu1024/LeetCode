package linkedList;

import org.junit.Test;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 *
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 */
public class Solution61 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head==null||head.next==null){
            return head;
        }
        ListNode fast=head;
        ListNode slow=head;
        int count=0;
        //首先计算当前链表的长度
        while (slow!=null){
            slow=slow.next;
            count++;
        }
        slow=head;
        //注意循环的情况，所以取模
        k=k%count;
        //让快指针先开始k个位置
        for (int i = 0; i < k; i++) {
            fast=fast.next;
        }
        //两个指针同时移动直到链表最后一个结点
        while (fast.next!=null){
            fast=fast.next;
            slow=slow.next;
        }
        //遍历结束后，此时，slow便指向新的头结点

        //将链表连成环路
        fast.next=head;
        //记录此时的头节点
        head=slow.next;
        //注意将尾指针置为空，不然会出现环路
        slow.next=null;
        return head;
    }
    /*@Test
    public void test(){
        int[] arr={1,2,3,4,5};
        ListNode head = LinkedListUtils.createLinkedList(arr, arr.length);
        ListNode res = rotateRight(head, 2);
        LinkedListUtils.printLinkedList(res);

    }*/
}
