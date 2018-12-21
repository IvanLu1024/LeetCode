package linkedList;

/**
 * 链表的测试类
 *
 * @author
 * @create 2018-10-28 22:04
 **/
public  class LinkedListUtils {

    public static ListNode createLinkedList(int[] arr,int n){
        if (n==0){
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode cur=head;
        for (int i = 1; i < n; i++) {
            cur.next=new ListNode(arr[i]);
            cur=cur.next;
        }
        return head;
    }

    public static void printLinkedList(ListNode head){
        ListNode cur=head;
        while (cur!=null){
            System.out.print(cur.val+" -> ");
            cur=cur.next;
        }
        System.out.println("Null");
    }
}
