package linkedList;

import org.junit.Test;

/**
 * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。
 * 将两数相加返回一个新的链表。

     你可以假设除了数字 0 之外，这两个数字都不会以零开头。

     示例：

     输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     输出：7 -> 0 -> 8
     原因：342 + 465 = 807
 * @author
 * @create 2018-10-30 20:23
 **/
public class Solution2 {

    //会出现越界现象
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1==null||l2==null){
            return l1==null?l2:l1;
        }
        ListNode cur1=l1;
        ListNode cur2=l2;
        long num1=getNumByListNode(l1);
        long num2 = getNumByListNode(l2);
        long res=num1+num2;
        return getListNodeByNum(res);

    }

    private long getNumByListNode(ListNode head){
        int index=0;
        long res=0;
        while (head!=null){
            res+=head.val*Math.pow(10,index++);
            head=head.next;
        }
        return res;
    }
    private ListNode getListNodeByNum(long num){
        if (num==0){
            return new ListNode(0);
        }
        ListNode cur=new ListNode(-1);
        ListNode head=cur;
        while (num!=0){
            int n = (int) (num % 10);
            num= num / 10;
            ListNode node = new ListNode(n);
            cur.next=node;
            cur=cur.next;
        }
        cur.next=null;
        return head.next;
    }
    //实现1
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        if (l1==null||l2==null){
            return l1==null?l2:l1;
        }
        int num1=0,num2=0;
        int count=0;
        ListNode cur = new ListNode(-1);
        ListNode head=cur;
        while (l1!=null||l2!=null){
           if (l1!=null){
               num1=l1.val;
               l1=l1.next;
           }else {
               num1=0;
           }
           if (l2!=null){
               num2=l2.val;
               l2=l2.next;

           }else {
               num2=0;
           }
           int n=num1+num2+count;
           cur.next=new ListNode(n%10);
           cur=cur.next;
           count=n/10;
        }
        if (count/10!=0){
            cur.next=new ListNode(count/10);
        }
        return head.next;
    }

    //实现2
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2){
        if (l1==null||l2==null){
            return l1==null?l2:l1;
        }
        int sum=0;
        ListNode cur = new ListNode(-1);
        ListNode head=cur;
        while (l1!=null||l2!=null||sum!=0){
            if (l1!=null){
                sum+=l1.val;
                l1=l1.next;
            }
            if (l2!=null){
                sum+=l2.val;
                l2=l2.next;
            }
            cur.next=new ListNode(sum%10);
            cur=cur.next;
            sum/=10;
        }
        return head.next;
    }

    @Test
    public void test(){
        int[] arr={2,4,3};
        int[] arr1={5,6,4};
        ListNode l1 = linkedList.Test.createLinkedList(arr, arr.length);
        ListNode l2 = linkedList.Test.createLinkedList(arr1, arr1.length);


        /*2147483647*/

        ListNode res = addTwoNumbers2(l1, l2);
        linkedList.Test.printLinkedList(res);

    }
}
