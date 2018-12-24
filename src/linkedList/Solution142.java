package linkedList;

import org.junit.Test;

public class Solution142 {
    public ListNode detectCycle(ListNode head) {
        if (head==null||head.next==null){
            return null;
        }
        ListNode slow=head;
        ListNode fast=head.next;
        int slowCount=1,fastCount=2;
        while (slow!=null&&fast!=null&&fast.next!=null){
            if (slow==fast){
                //cycleLen=fastCount-slowCount，一圈的长度
                return getCycleTail(head,fastCount-slowCount);
            }
            slow=slow.next;
            slowCount++;
            fast=fast.next.next;
            fastCount+=2;
        }
        return null;
    }
    //根据循环部分的长度来获取循环的头结点
    private ListNode getCycleTail(ListNode head,int cycleLen){
        //利用两个指针，间隔保持cycleLen
        ListNode slow=head;
        ListNode fast=head;
        for (int i = 0; i < cycleLen; i++) {
            fast=fast.next;
        }
        while (fast!=slow){
            fast=fast.next;
            slow=slow.next;
        }
        //当两个指针指向同一个结点的时候，表明快指针正好比慢指针快了整整一圈
        return fast;
    }

    @Test
    public void test(){
        /*3,2,0,-4*/
        ListNode l1 = new ListNode(3);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(0);
        ListNode l4 = new ListNode(4);
        l1.next=l2;
        l2.next=l3;
        l3.next=l4;
        l4.next=l2;
        ListNode res = detectCycle(l1);
        System.out.println(res.val);

    }


}
