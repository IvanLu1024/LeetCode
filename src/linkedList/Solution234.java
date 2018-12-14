package linkedList;

import org.junit.Test;
import utils.LinkedListUtils;

/**
 * 请判断一个链表是否为回文链表。

 示例 1:

 输入: 1->2
 输出: false
 示例 2:

 输入: 1->2->2->1
 输出: true
 */
public class Solution234 {
    public boolean isPalindrome(ListNode head) {
        if (head==null||head.next==null){
            return true;
        }
        //首先，将链表对半分
        ListNode fast=head,slow=head;
        //找到后半部分的前一个位置
        while (fast.next!=null&&fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        //将后半部分翻转
        slow.next=reverse(slow.next);
        //指向后半部分的第一个结点
        slow=slow.next;
        ListNode cur=head;
        //遍历链表，确保前半部分与后半部分是相同的
        while (slow!=null){
            if (slow.val!=cur.val){
                return false;
            }
            slow=slow.next;
            cur=cur.next;
        }
        return true;

    }
    private ListNode reverse(ListNode head){
        if (head==null||head.next==null){
            return head;
        }
        ListNode pre=null;
        ListNode cur=head;
        while (cur!=null){
            ListNode next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        return pre;
    }
    @Test
    public void test() {
        int[] arr={1,1,1,1};
        ListNode head = linkedList.Test.createLinkedList(arr, arr.length);
        boolean res = isPalindrome(head);
        System.out.println(res);

    }

    }
