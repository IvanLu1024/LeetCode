package design;

import org.junit.Test;

import java.util.*;

/**
 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。

 push(x) -- 将元素 x 推入栈中。
 pop() -- 删除栈顶的元素。
 top() -- 获取栈顶元素。
 getMin() -- 检索栈中的最小元素。
 示例:

 MinStack minStack = new MinStack();
 minStack.push(-2);
 minStack.push(0);
 minStack.push(-3);
 minStack.getMin();   --> 返回 -3.
 minStack.pop();
 minStack.top();      --> 返回 0.
 minStack.getMin();   --> 返回 -2.

 */
public class Solution155 {

    class MinStack {
        class Ele{
            int val;
            int min;
            Ele next;

            public Ele(int val,int min){
                this.val=val;
                this.min=min;
            }

        }

        private Ele top;


        /** initialize your data structure here. */
        public MinStack() {


        }

        public void push(int x) {
            if (top==null){
                top = new Ele(x, x);
            }else {
                Ele ele = new Ele(x, Math.min(x, top.min));
                ele.next = top;
                top = ele;
            }

        }


        public void pop() {
            if (top==null){
                return;
            }
            Ele t = top.next;
            top.next=null;
            top=t;
        }

        public int top() {
            if (top==null){
                return -1;
            }
            return top.val;
        }

        public int getMin() {
            if (top==null){
                return -1;
            }
            return top.min;
        }
    }

    @Test
    public void test(){
        /**
         * ["MinStack","push","push","push","push","pop","getMin","pop","getMin","pop","getMin"]
         * [[],[512],[-1024],[-1024],[512],[],[],[],[],[],[]]
         */
        MinStack minStack = new MinStack();
        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-3);
        minStack.pop();
        minStack.pop();
        minStack.push(-5);
        minStack.push(5);
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
