package linkedList;

import org.junit.Test;

import java.util.Stack;
//时间复杂度：O（n）
//空间复杂度：O（n）
public class Solution445 {

    //与2题思想类似，本地使用了栈的数据结构
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1==null||l2==null){
            return l1==null?l2:l1;
        }
        Stack<Integer> stack1=new Stack<>();
        Stack<Integer> stack2=new Stack<>();
        Stack<Integer> resStack=new Stack<>();
        while (l1!=null||l2!=null){
            if (l1!=null){
                stack1.add(l1.val);
                l1=l1.next;
            }
            if (l2!=null){
                stack2.add(l2.val);
                l2=l2.next;
            }
        }
        int num1,num2;
        int count=0;
        while (!stack1.empty()||!stack2.empty()){

            if (!stack1.empty()){
                num1=stack1.pop();
            }else {
                num1=0;
            }
            if (!stack2.empty()){
                num2=stack2.pop();
            }else {
                num2=0;
            }
            int sum=num1+num2+count;
            resStack.add(sum%10);
            count=sum/10;
        }
        if (count!=0){
            resStack.add(count);
        }
        ListNode res = new ListNode(-1);
        ListNode resHead=res;

        while (!resStack.empty()){
            res.next=new ListNode(resStack.pop());
            res=res.next;
        }
        res.next=null;
        return resHead.next;
    }
    @Test
    public void test(){
        int[] arr={1,5};
        int[] arr1={5};
        ListNode l1 = linkedList.Test.createLinkedList(arr, arr.length);
        ListNode l2 = linkedList.Test.createLinkedList(arr1, arr1.length);
        ListNode listNode = addTwoNumbers(l1, l2);
        linkedList.Test.printLinkedList(listNode);
    }
}
