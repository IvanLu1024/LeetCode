package linkedList;

import org.junit.Test;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

     示例：

     给定一个链表: 1->2->3->4->5, 和 n = 2.

     当删除了倒数第二个节点后，链表变为 1->2->3->5.
     说明：

     给定的 n 保证是有效的。

     进阶：

     你能尝试使用一趟扫描实现吗？
 *
 * @author
 * @create 2018-11-05 21:24
 **/
public class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead=new ListNode(0);
        dummyHead.next=head;
        ListNode fastNode=dummyHead;
        ListNode slowNode=dummyHead;
        //快指针先移动n+1个位置，由于添加了一个虚拟头结点
        for (int i = 0; i <= n; i++) {
            fastNode=fastNode.next;
        }
        //两个指针同时运动
        while (fastNode!=null){
            fastNode=fastNode.next;
            slowNode=slowNode.next;
        }
        //此时慢指针指向的则是倒数第n个元素
        ListNode delNode=slowNode.next;
        slowNode.next=delNode.next;
        delNode=null;

        return dummyHead.next;

    }
    @Test
    public void test(){
        int[] arr={1,2,3,4,5};
        ListNode head = linkedList.Test.createLinkedList(arr, arr.length);
        linkedList.Test.printLinkedList(head);
        int n=2;
        ListNode res = removeNthFromEnd(head, n);
        linkedList.Test.printLinkedList(res);
    }
}
