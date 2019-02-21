package linkedList;

import org.junit.Test;

/**
 * 插入排序算法：
 *
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 *
 *
 * 示例 1：
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2：
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 */
public class Solution147 {
    public ListNode insertionSortList(ListNode head) {
        if(head == null||head.next == null)
                         return head;
        //虚拟头节点指向已排序好的部分
        ListNode sortedlisthead = new ListNode(0);
        //指向当前位置的指针
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            //pre:指向待排序的部分的头指针
            ListNode pre = sortedlisthead;
            //在已排序的部分寻找可以插入的位置
            while (pre.next != null && cur.val > pre.next.val) {
                pre = pre.next;
            }
            //将待排序的结点插入已排序的部分
            cur.next = pre.next;
            //继续指向未排序的部分
            pre.next = cur;
            cur = next;
        }
        return sortedlisthead.next;
    }
   /* @Test
    public void test(){
        int[] arr={4,2,1,3};
        ListNode head = LinkedListUtils.createLinkedList(arr, arr.length);
        ListNode res = insertionSortList(head);
        LinkedListUtils.printLinkedList(res);
    }*/
}
