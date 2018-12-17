package queue;

import linkedList.ListNode;
import org.junit.Test;
import utils.LinkedListUtils;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 */
public class Solution23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length==0){
            return null;
        }
        ListNode dummyHead=new ListNode(-1);
        ListNode cur=dummyHead;
        //使用优先队列对链表进行排列
        PriorityQueue<ListNode> queue=new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val-o2.val;
            }
        });
        for (ListNode list:lists){
            if (list!=null){
                queue.offer(list);
            }
        }
        while (!queue.isEmpty()){
            //从队列中取出队首元素，就是队列中最小的元素
            ListNode nextNode = queue.poll();
            //将这个元素插入当前结点的后面
            cur.next=nextNode;
            //将这个元素的后面的结点也放入队列中
            if (nextNode.next!=null){
                queue.offer(nextNode.next);
            }
            //清理局部变量
            nextNode.next=null;
            //继续遍历
            cur=cur.next;
        }
        return dummyHead.next;
    }
    @Test
    public void test(){
        int[] a1={1,4,5};
        int[] a2={1,3,4};
        int[] a3={2,6};
        ListNode l1 = linkedList.LinkedListUtils.createLinkedList(a1, a1.length);
        ListNode l2 = linkedList.LinkedListUtils.createLinkedList(a2, a2.length);
        ListNode l3 = linkedList.LinkedListUtils.createLinkedList(a3, a3.length);
        ListNode[] lists=new ListNode[]{l1,l2,l3};
        ListNode ret = mergeKLists(lists);
        linkedList.LinkedListUtils.printLinkedList(ret);
    }
}
