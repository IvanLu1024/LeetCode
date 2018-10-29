package linkedList;

import org.junit.Test;

/**
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。

     你应当保留两个分区中每个节点的初始相对位置。

     示例:

     输入: head = 1->4->3->2->5->2, x = 3
     输出: 1->2->2->4->3->5
 *
 * @author
 * @create 2018-10-29 21:50
 **/
public class Solution86 {
    public ListNode partition(ListNode head, int x) {
        if (head==null){
            return head;
        }
        //左部分:记录小于x的结点
        ListNode left=new ListNode(-1);
        //右部分:记录大于或等于x的结点
        ListNode right=new ListNode(-1);
        //记录左右部分的头结点
        ListNode leftHead=left,rightHead=right;
        //
        while (head!=null){
            //遇到小于x的结点，用left记录
            if (head.val<x){
                left.next=head;
                left=left.next;
            }else {
                right.next=head;
                right=right.next;
            }
            head=head.next;
        }
        //链接左右两部分
        left.next=rightHead.next;
        //设置尾部为空
        right.next=null;
        return leftHead.next;


    }
    @Test
    public void test(){
        int[] arr={1,4,3,2,5,2};
        ListNode head = linkedList.Test.createLinkedList(arr, arr.length);
        int x=3;
        linkedList.Test.printLinkedList(head);
        ListNode partition = partition(head, x);


        linkedList.Test.printLinkedList(partition);

    }
}
