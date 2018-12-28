package queue;

import org.junit.Test;

public class Solution622 {
    class MyCircularQueue {

        private int[] data;
        private int front,rear;

        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueue(int k) {
            data =new int[k+1];
            front=0;
            rear=0;
        }

        /** Insert an element into the circular queue. Return true if the operation is successful. */
        public boolean enQueue(int value) {
            if (isFull()){
                return false;
            }else {
                data[rear]=value;
                rear=(rear+1)%data.length;
                return true;
            }
        }

        /** Delete an element from the circular queue. Return true if the operation is successful. */
        public boolean deQueue() {
            if (isEmpty()){
                return false;
            }else {
                front=(front+1)%data.length;
                return true;
            }
        }

        /** Get the front item from the queue. */
        public int Front() {
            if(isEmpty())
                return -1;
            return data[front];
        }

        /** Get the last item from the queue. */
        public int Rear() {
            if(isEmpty())
                return -1;
            //获得队尾元素需要注意的是，队尾元素是尾指针的前一个元素
            int index = rear - 1;
            //注意为负数的情况
            if(index < 0)
                index += data.length;
            return data[index];
        }

        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
            return front==rear;
        }

        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
            return  (rear+1)%data.length==front;
        }
    }

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
@Test
public void test(){
    MyCircularQueue queue = new MyCircularQueue(3);
    System.out.println(queue.enQueue(1));
    System.out.println(queue.enQueue(2));
    System.out.println(queue.enQueue(3));
    System.out.println(queue.enQueue(4));
    System.out.println(queue.Rear());
}

}
