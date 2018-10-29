package linkedList;

import org.junit.Test;

/**给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

 示例 1:

 输入: 1->1->2
 输出: 1->2
 示例 2:

 输入: 1->1->2->3->3
 输出: 1->2->3
 * @author
 * @create 2018-10-29 20:38
 **/
public class Solution83 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head==null||head.next==null)
            return head;
        ListNode cur=head;
        ListNode post=head.next;

        while (post!=null){
            if (cur.val!=post.val){
                cur.next=post;
                cur=post;
            }
            post=post.next;
        }
        cur.next=null;

        return head;
    }


    public ListNode deleteDuplicates1(ListNode head){
        if (head==null||head.next==null){
            return head;
        }
        ListNode slow=head;
        ListNode fast=head.next;
        while (fast!=null){
            //当遇到重复元素的时候，只有快指针运动
            if (fast.val==slow.val){
                fast=fast.next;
            }
            //反之，删除重复的元素之后两个指针一起运动
            else {
                //删除重复元素
                slow.next=fast;
                //一起运动
                slow=slow.next;
                fast=fast.next;
            }
        }
        slow.next=null;
        return head;

    }
    @Test
    public void test(){
        int[] arr={1,1,2,3,3};
        ListNode origialLinkedList = linkedList.Test.createLinkedList(arr, arr.length);
        linkedList.Test.printLinkedList(origialLinkedList);
        ListNode res = deleteDuplicates1(origialLinkedList);

        linkedList.Test.printLinkedList(res);
    }
}
