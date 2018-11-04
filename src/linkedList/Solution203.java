package linkedList;

import org.junit.Test;

/**
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
public class Solution203 {

    //虚拟头指针法
    public ListNode removeElements(ListNode head, int val) {
        if (head==null||head.next==null)
            return head;
        //设置一个虚拟头指针，这样原始链表中的头结点可以和其他结点一样处理了
        ListNode dummyHead=new ListNode(-1);
        dummyHead.next=head;
        ListNode cur=dummyHead;
        while (cur.next!=null){
            //当遇到目标值的时候，进行删除操作
            if (cur.next.val==val){
                ListNode delNode=cur.next;
                cur.next=delNode.next;
                //释放delNode的空间
                delNode.next=null;
            }else {
                //正常遍历结点
                cur=cur.next;
            }
        }
        return dummyHead.next;
    }
    @Test
    public void test(){
        int[] arr={1,1,1,3,4,5,6};
        int val=1;
        ListNode head = linkedList.Test.createLinkedList(arr, arr.length);
        linkedList.Test.printLinkedList(head);
        ListNode res = removeElements(head, val);
        linkedList.Test.printLinkedList(res);

    }
}
