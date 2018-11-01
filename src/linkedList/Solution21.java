package linkedList;

import org.junit.Test;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class Solution21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //合并的新链表
        ListNode dummy=new ListNode(-1);
        ListNode cur=dummy;
        while (l1!=null&&l2!=null){
                if (l1.val<l2.val){
                    cur.next=l1;
                    l1=l1.next;
                }else {
                    cur.next=l2;
                    l2=l2.next;
                }
                cur=cur.next;
        }
        //当链表长度不同的时候，将剩余的链表直接插入新的链表中
        if (l1!=null){
            cur.next=l1;
        }
        if (l2!=null){
            cur.next=l2;
        }
        return dummy.next;
    }
    @Test
    public void test(){
        int[] arr1={1,2,4,5,8};
        int[] arr2={};
        ListNode l1 = linkedList.Test.createLinkedList(arr1, arr1.length);
        ListNode l2 = linkedList.Test.createLinkedList(arr2, arr2.length);
        ListNode res = mergeTwoLists(l1, l2);
        linkedList.Test.printLinkedList(res);
    }
}
