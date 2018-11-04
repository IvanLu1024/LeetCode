package linkedList;

/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 *
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 *
 */
public class Solution82 {

    //三指针的方法，搜索到需要删除的结点的前一个结点pre，将pre与后面链接
    public ListNode deleteDuplicates(ListNode head) {
        if (head==null||head.next==null)
            return head;

        ListNode pre=null;
        ListNode cur=head;
        ListNode post=head.next;
        boolean isDelete=false;
        while (post!=null){
            if (cur.val==post.val){
                cur=post;
                post=post.next;
                isDelete=true;
            }else {
                //当前后结点不等的时候，存在需要删除结点的情况
                if (isDelete){
                    //当pre还没有移动的时候，直接把头部删除
                    if (pre==null){
                        head=post;
                    }
                    //常规的删除操作：将pre指向post结点
                    else {
                        pre.next=post;
                    }
                    //指针继续后移
                    cur=post;
                    post=post.next;
                    isDelete=false;
                }else {
                    //三个指针一起后移
                    pre=cur;
                    cur=post;
                    post=post.next;
                }
            }
        }
        //尾部可能存在重复未删除的结点
        if (isDelete){
            //删除尾部结点
            if (pre!=null){
                pre.next=null;
            }
            //此时链表中没有不重复的元素
            else {
                head=null;
            }

        }
        return head;
    }
    public ListNode deleteDuplicates1(ListNode head){
        ListNode dummy=new ListNode(-1);
        dummy.next=head;
        ListNode pre=dummy;
        ListNode cur=head;
        while (cur!=null){
            //当出现重复结点的时候
            if (cur.next!=null&&cur.val==cur.next.val){
                //跳过重复的结点
                while (cur.next!=null&&cur.val==cur.next.val){
                    cur=cur.next;
                }
            }
            //记录不重复的结点
            else {
                pre=cur;
            }
            //正常遍历
            cur=cur.next;
            pre.next=cur;

        }

        return dummy.next;
    }

    @org.junit.Test
    public void test(){
        int[] arr={-3,-3,-2,-1,-1,0,0,0,0,0};
        ListNode head = linkedList.Test.createLinkedList(arr, arr.length);
        linkedList.Test.printLinkedList(head);
        ListNode res = deleteDuplicates1(head);
        linkedList.Test.printLinkedList(res);

    }
}
