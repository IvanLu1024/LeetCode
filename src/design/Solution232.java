package design;

import org.junit.Test;

import java.util.Queue;
import java.util.Stack;

public class Solution232 {
    class MyQueue {
        Stack<Integer> stack1;
        Stack<Integer> stack2;

        /** Initialize your data structure here. */
        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stack1.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (!stack2.empty()){
                return stack2.pop();
            }
            while (!stack1.empty()){
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (!stack2.empty()){
                return stack2.peek();
            }
            while (!stack1.empty()){
                stack2.push(stack1.pop());
            }
            return stack2.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack1.empty()&&stack2.empty();
        }
    }
    @Test
    public void test(){
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.pop());
        queue.push(3);
        queue.push(4);
        System.out.println(queue.pop());
        System.out.println(queue.peek());


    }
}
