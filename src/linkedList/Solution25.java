package linkedList;

/**
 * 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。

     k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。

     示例 :

     给定这个链表：1->2->3->4->5

     当 k = 2 时，应当返回: 2->1->4->3->5

     当 k = 3 时，应当返回: 3->2->1->4->5

     说明 :

     你的算法只能使用常数的额外空间。
     你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * @author
 * @create 2018-11-05 21:39
 **/
public class Solution25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head==null){
            return null;
        }
        ListNode dummyHead=new ListNode(0);
        dummyHead.next=head;
        ListNode pre=dummyHead;
        while (pre!=null){
            pre=reverse(pre, k);
        }
        return dummyHead.next;

    }
    //从后向前翻转链表
    private ListNode reverse(ListNode head,int k){
        ListNode last=head;
        for (int i = 0; i < k+1; i++) {
            last=last.next;
            if (i!=k&&last==null){
                return null;
            }
        }
        ListNode tail=head.next;
        ListNode cur=tail.next;
        //每次将元素提前的操作，第一个结点不动，从第二个结点开始，将其依次提前
        //**特别注意其实现过程**
        while (cur!=last){
            ListNode next=cur.next;
            cur.next=head.next;
            head.next=cur;
            tail.next=next;
            cur=next;
            /*ListNode next = cur.next;
            pre.next=cur;
            cur.next=tail;
            tail.next=next;
            cur=next;*/
        }
        return tail;
    }

    @org.junit.Test
    public void test(){
        int[] arr={1,2,3,4};
        ListNode head = linkedList.Test.createLinkedList(arr, arr.length);
        linkedList.Test.printLinkedList(head);
        int k=3;
        ListNode res = reverseKGroup(head,k);
        linkedList.Test.printLinkedList(res);



    }
}
