package linkedList;

import org.junit.Test;
/**
 注意：

 如果两个链表没有交点，返回 null.
 在返回结果后，两个链表仍须保持原有的结构。
 可假定整个链表结构中没有循环。
 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。

 */
public class Solution160 {
    //思路：由于是单链表，当出现公共结点后，其后部分均为相同。（因为2个链表用公共的尾部）
    //当长度相同的时候，同时同步遍历一定能够找到相交部分
    // 当长度不同的时候，转化为长度相同的事情，让长链表的指针先移动|sizeA-sizeB|个结点，
    //接着两个指针同时开始移动。
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA==null||headB==null){
            return null;
        }
        int sizeA=0;
        int sizeB=0;
        ListNode curA=headA;
        ListNode curB=headB;
        while (curA!=null){
            sizeA++;
            curA=curA.next;
        }
        while (curB!=null){
            sizeB++;
            curB=curB.next;
        }
        curA=headA;
        curB=headB;
        if (sizeA==sizeB){
            return commonNode(curA,curB);
        }else if (sizeA>sizeB){
            int count = sizeA - sizeB;
            for (int i = 0; i < count; i++) {
                curA = curA.next;
            }
            return commonNode(curA,curB);
        }else {
            int count = sizeB - sizeA;
            for (int i = 0; i < count; i++) {
                curB = curB.next;
            }
            return commonNode(curA,curB);
        }
    }
    //当长度相同的时候寻找两个链表的公共部分
    private ListNode commonNode(ListNode headA, ListNode headB){
        if (headA==null||headB==null){
            return null;
        }
        ListNode curA=headA;
        ListNode curB=headB;
        while (curA!=null){
            if (curA.val==curB.val){
                return curA;
            }
            curA=curA.next;
            curB=curB.next;
        }
        return null;
    }
    @Test
    public void test(){
        int[] arrA={4};
        int[] arrB={4};
        ListNode headA = LinkedListUtils.createLinkedList(arrA, arrA.length);
        ListNode headB = LinkedListUtils.createLinkedList(arrB, arrB.length);
        ListNode intersectionNode = getIntersectionNode(headA, headB);
        LinkedListUtils.printLinkedList(intersectionNode);
    }
}
