package linkedList;

/**
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。

     现有一个链表 -- head = [4,5,1,9]，它可以表示为:

     4 -> 5 -> 1 -> 9
     示例 1:

     输入: head = [4,5,1,9], node = 5
     输出: [4,1,9]
     解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * @author
 * @create 2018-11-05 20:58
 **/
public class Solution237 {

    public void deleteNode(ListNode node) {
        if (node==null)
            return;
        ListNode cur=node;
        if (cur.next==null){
            node=null;
        }
        ListNode next=cur.next;
        cur.val=next.val;
        cur.next=next.next;
        next.next=null;
    }


}
