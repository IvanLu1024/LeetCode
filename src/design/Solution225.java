package design;

import java.util.LinkedList;

public class Solution225 {

    class MyStack {

        private LinkedList<Integer> data;

        /** Initialize your data structure here. */
        public MyStack() {
            data = new LinkedList<>();

        }

        /** Push element x onto stack. */
        public void push(int x) {
            data.add(x);
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            int res = data.getLast();
            data.removeLast();
            return res;
        }

        /** Get the top element. */
        public int top() {
            return data.getLast();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return data.isEmpty();
        }
    }
}
