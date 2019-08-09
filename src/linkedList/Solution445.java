package linkedList;

import java.util.*;

import org.junit.Test;
//时间复杂度：O（n）
//空间复杂度：O（n）
    //与2题思想类似，本地使用了栈的数据结构

/**
 * 给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字。
 * 将这两数相加会返回一个新的链表。



     你可以假设除了数字 0 之外，这两个数字都不会以零开头。

     进阶:

     如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。

     示例:

     输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
     输出: 7 -> 8 -> 0 -> 7
 * @author
 * @create 2018-10-30 21:54
 **/
public class Solution445 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        Stack<Integer> resStack = new Stack<>();
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                stack1.add(l1.val);
                l1 = l1.next;
            }
            if (l2 != null) {
                stack2.add(l2.val);
                l2 = l2.next;
            }
        }
        int num1, num2;
        int count = 0;
        while (!stack1.empty() || !stack2.empty()) {

            if (!stack1.empty()) {
                num1 = stack1.pop();
            } else {
                num1 = 0;
            }
            if (!stack2.empty()) {
                num2 = stack2.pop();
            } else {
                num2 = 0;
            }
            int sum = num1 + num2 + count;
            resStack.add(sum % 10);
            count = sum / 10;
        }
        if (count != 0) {
            resStack.add(count);
        }
        ListNode res = new ListNode(-1);
        ListNode resHead = res;

        while (!resStack.empty()) {
            res.next = new ListNode(resStack.pop());
            res = res.next;
        }
        res.next = null;
        return resHead.next;
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        Stack<Integer> stack1=new Stack<>();
        Stack<Integer> stack2=new Stack<>();
        Stack<Integer> stack=new Stack<>();
        while(l1!=null||l2!=null){
            if(l1!=null){
                stack1.push(l1.val);
                l1=l1.next;
            }
            if(l2!=null){
                stack2.push(l2.val);
                l2=l2.next;
            }
        }
        int sum=0;
        int num1=0,num2=0;
        while(!stack1.empty()||!stack2.empty()){
            if(!stack1.empty()){
                num1=stack1.pop();
            }else{
                num1=0;
            }
            if(!stack2.empty()){
                num2=stack2.pop();
            }else{
                num2=0;
            }
            sum+=num1+num2;
            stack.push(sum%10);
            sum/=10;
        }
        if(sum!=0){
            stack.push(sum%10);
        }
        ListNode newHead=new ListNode(0);
        ListNode cur=newHead;
        while(!stack.empty()){
            cur.next=new ListNode(stack.pop());
            cur=cur.next;
        }
        cur.next=null;
        return newHead.next;
    }

    @Test
    public void test() {
        int[] arr = {1, 5};
        int[] arr1 = {5};
        String s ="";
        s.toCharArray();
        Queue<Integer> queue=new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        ListNode l1 = linkedList.Test.createLinkedList(arr, arr.length);
        ListNode l2 = linkedList.Test.createLinkedList(arr1, arr1.length);
        ListNode listNode = addTwoNumbers1(l1, l2);
        linkedList.Test.printLinkedList(listNode);


    }
}
