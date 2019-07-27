相关题目：
* [146.LRU缓存机制](#146)
* [155.最小栈](#155)
* [225.用队列实现栈](#225)
* [232.用栈实现队列](#232)
* [432.全O(1)的数据结构](#432)
## 146**

LRU缓存机制

### 描述

 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。

 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。

 进阶:

 你是否可以在 O(1) 时间复杂度内完成这两种操作？

 示例:

 ```java
LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
 
 cache.put(1, 1);
 cache.put(2, 2);
 cache.get(1);       // 返回  1
 cache.put(3, 3);    // 该操作会使得密钥 2 作废
 cache.get(2);       // 返回 -1 (未找到)
 cache.put(4, 4);    // 该操作会使得密钥 1 作废
 cache.get(1);       // 返回 -1 (未找到)
 cache.get(3);       // 返回  3
 cache.get(4);       // 返回  4
 ```
### 分析

思路1：使用双向链表+map的解法

首先设置缓存中存储的每个节点都是双向链表中的一个节点，另外还需要设置两个指针，**head指向最近使用的节点，tail指向最久使用的节点**；每次使用到缓存key1（get(key1)或put(key1,value1)），都需要将当前key1对应的节点，将该节点移动到双链表的头部（先将头指针指向该节点[move2Head]，再从双链表中删除该节点[removeNode]）。

思路2：使用List+map的解法

设置一个List，这个List的特点是尾部是最近使用的节点，头部是最久使用的节点。与上面的思路类似，每当使用到缓存key1就需要将当前key1对应的节点，移动到List的尾部（先从List删除该key1，再从尾部插入key1）。

### 实现

解法1：
```java
//使用双链表+map的解法
    class LRUCache {
        class node{
            int key;
            int value;
            node pre;
            node next;

            public node(int key, int value) {
                this.key = key;
                this.value = value;
                this.pre = null;
                this.next = null;
            }
        }
        private Map<Integer,node> map;
        private int capacity,count;
        private node head;   //表示最近使用的结点
        private node tail;   //表示最久使用的结点

        public LRUCache(int capacity) {
            map = new HashMap<>();
            this.capacity = capacity;
            this.head = new node(-1,-1);
            this.tail = new node(-1,-1);
            tail.pre = head;
            head.next = tail;
        }

        public int get(int key) {
            if (!map.containsKey(key)){
                return -1;
            }
            node curNode = map.get(key);
            int res = curNode.value;
            removeNode(curNode);
            move2Head(curNode);
            return res;
        }

        public void put(int key, int value) {
            if (map.get(key)!=null){
                node curNode = map.get(key);
                curNode.value = value;
                removeNode(curNode);
                move2Head(curNode);
            }else {
                node curNode = new node(key,value);
                map.put(key,curNode);
                if (count>=capacity){
                    map.remove(tail.pre.key);
                    removeNode(tail.pre);
                    move2Head(curNode);
                }else {
                    count++;
                    move2Head(curNode);
                }
            }
        }

        //将某结点移动到头结点
        private void move2Head(node n){
            n.next = head.next;
            head.next.pre = n;
            n.pre = head;
            head.next = n;
        }

        //
        private void removeNode(node n){
            n.pre.next = n.next;
            n.next.pre = n.pre;
        }
    }

```
解法2：
```java
//使用List和Map的解法
    class LRUCache{
        private int capacity,num;
        private Map<Integer,Integer> map;
        private List<Integer> cache;    //缓存key，确保尾部是最近使用的key

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            this.num = 0;
            cache = new ArrayList<>();
        }

        public int get(int key) {
            if (!cache.contains(key)){
                return -1;
            }
            //确保尾部的最近使用的key
            cache.remove((Integer)key);
            cache.add(key);

            return map.get(key);
        }

        public void put(int key, int value) {
            //当有缓存的时候
            if (cache.contains(key)){
                cache.remove((Integer)key);
                cache.add(key);
                map.put(key,value);
            }else {
                //当没有缓存的时候
                //当缓存满的时候
                if (num==capacity){
                    map.remove(cache.get(0));
                    cache.remove(0);
                    map.put(key,value);
                    cache.add(key);
                }else {
                    map.put(key,value);
                    cache.add(key);
                    num++;
                }
            }
        }
    }
```

## 155

最小栈

### 描述

设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。

push(x) -- 将元素 x 推入栈中。
pop() -- 删除栈顶的元素。
top() -- 获取栈顶元素。
getMin() -- 检索栈中的最小元素。
示例:

```java
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.
```
### 分析

思路1：使用**带有记录当前最小值**的单链表实现

设置一个栈顶指针top，每次出栈和入栈都是操作栈顶指针。

思路2：两个栈实现

一个正常的栈，一个用于记录最小值的栈，栈顶始终记录着当前栈中的最小元素。

正常的栈的操作没有什么特殊操作，对于最小值栈，当入栈的时候，若当前值小于栈顶元素则压入栈；当出栈的时候，若正常栈的栈顶元素和最小值栈相同，则同时出栈，否则只需要操作正常栈出栈，即可。



### 实现

解法1：
```java
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
        //用于记录栈顶元素
        private Ele top;


        /** initialize your data structure here. */
        public MinStack() {


        }

        public void push(int x) {
            //当栈为空的时候，直接新建一个元素
            if (top==null){
                top = new Ele(x, x);
            }else {
                //当栈不为空的时候
                //新创建的元素要确保其最小值
                Ele ele = new Ele(x, Math.min(x, top.min));
                //并且连接上栈顶元素
                ele.next = top;
                //最后将其作为栈顶元素
                top = ele;
            }
        }
        //弹栈的时候，只需要将栈顶元素下移一个位置即可
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
```
解法2：
```java
//利用两个栈实现
    class MinStack1{
        //用于记录最小值的栈
        private Stack<Integer> minStack;
        //主栈
        private Stack<Integer> stack;

        /** initialize your data structure here. */
        public MinStack1() {
            minStack=new Stack<>();
            stack=new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            //当最小值栈为空或者有最小值入栈的时候，加入最小值栈中
            if (minStack.empty()||x<=minStack.peek()){
                minStack.push(x);
            }
        }

        public void pop() {
            int t = stack.pop();
            if (t==minStack.peek()){
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }

    }
```
## 225

用队列实现栈

### 描述

使用队列实现栈的下列操作：

- push(x) -- 元素 x 入栈
- pop() -- 移除栈顶元素
- top() -- 获取栈顶元素
- empty() -- 返回栈是否为空

**注意:**

- 你只能使用队列的基本操作-- 也就是 `push to back`, `peek/pop from front`, `size`, 和 `is empty` 这些操作是合法的。
- 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
- 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。

### 分析

首先分析一下，队列和栈的区别在于出入元素顺序的不同。那么，利用队列实现先进先出（FIFO），就需要在入队的时候，将入队的元素放置到队头的位置。这里可以借鉴**循环队列**的思想，为了达到与入队序列逆序，那么需要将队列循环左移（n-1位）。例如：queue:[1,2,3]->push(4):[1,2,3,4]->[2,3,4,1]->...[4,3,2,1]。

### 实现

```java
class MyStack {

   Queue<Integer> queue;
        /** Initialize your data structure here. */
        public MyStack() {
            queue =new LinkedList<>();
        }

        /** Push element x onto stack. */
    	//将入队顺序逆序
        public void push(int x) {
            queue.offer(x);
            for (int i = 0; i < queue.size() - 1; i++) {
                queue.offer(queue.poll());
            }
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return queue.poll();
        }

        /** Get the top element. */
        public int top() {
            return queue.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue.isEmpty();
        }
}
```





## 232

用栈实现队列

### 描述

使用栈实现队列的下列操作：

push(x) -- 将一个元素放入队列的尾部。
pop() -- 从队列首部移除元素。
peek() -- 返回队列首部的元素。
empty() -- 返回队列是否为空。
示例:

MyQueue queue = new MyQueue();

queue.push(1);
queue.push(2);  
queue.peek();  // 返回 1
queue.pop();   // 返回 1
queue.empty(); // 返回 false
说明:

你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。

### 分析

使用两个栈来实现队列，一个栈负责入队stack1，一个负责出队stack2，每当出队的栈stack2为空的时候就将入队栈stack2的元素压入，而当出队栈stack2不为空的时候，直接弹栈即可。

### 实现

```java
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
```



### 432
实现一个数据结构支持以下操作：

Inc(key) - 插入一个新的值为 1 的 key。或者使一个存在的 key 增加一，保证 key 不为空字符串。

Dec(key) - 如果这个 key 的值是 1，那么把他从数据结构中移除掉。否者使一个存在的 key 值减一。如果这个 key 不存在，这个函数不做任何事情。key 保证不为空字符串。

GetMaxKey() - 返回 key 中值最大的任意一个。如果没有元素存在，返回一个空字符串""。

GetMinKey() - 返回 key 中值最小的任意一个。如果没有元素存在，返回一个空字符串""。

挑战：以 O(1) 的时间复杂度实现所有操作。  

- 实现：
```java
class AllOne{
        private Map<String,Node> map;
        //使用一个最小堆和最大堆来存储最小值和最大值
        private PriorityQueue<Node> minQ;
        private PriorityQueue<Node> maxQ;

        class Node{
            String key;
            int value;

            public Node(String key) {
                this.key = key;
                this.value = 1;
            }

            public void inc() {
                this.value++;
            }

            public void dec() {
                this.value--;
            }

        }

        /** Initialize your data structure here. */
        public AllOne() {
            map=new HashMap<>();
            minQ=new PriorityQueue<>(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.value-o2.value;
                }
            });
            maxQ=new PriorityQueue<>(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o2.value-o1.value;
                }
            });
        }

        /** Inserts a new key <Key> with value 1. Or increments an existing key by 1.
         * 插入一个新的值为 1 的 key。或者使一个存在的 key 增加一，保证 key 不为空字符串。*/
        public void inc(String key) {
            if (map.containsKey(key)){
                Node node = map.get(key);
                //当值发生变化的时候，重新加入堆
                minQ.remove(node);
                maxQ.remove(node);
                node.inc();
                minQ.add(node);
                maxQ.add(node);
            }else {
                Node node = new Node(key);
                map.put(key,node);
                minQ.add(node);
                maxQ.add(node);
            }
        }

        /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
         * 如果这个 key 的值是 1，那么把他从数据结构中移除掉。否者使一个存在的 key 值减一。 */
        public void dec(String key) {
            if (!map.containsKey(key)){
                return;
            }
            Node node = map.get(key);
            if (node.value==1){
                map.remove(key);
                minQ.remove(node);
                maxQ.remove(node);
                node=null;
            }else {
                minQ.remove(node);
                maxQ.remove(node);
                node.dec();
                minQ.add(node);
                maxQ.add(node);
            }
        }

        /** Returns one of the keys with maximal value.
         * 返回 key 中值最大的任意一个。如果没有元素存在，返回一个空字符串""。*/
        public String getMaxKey() {
            if (!maxQ.isEmpty()){
                return maxQ.peek().key;
            }
            return "";
        }

        /** Returns one of the keys with Minimal value. */
        public String getMinKey() {
            if (!minQ.isEmpty()){
                return minQ.peek().key;
            }
            return "";
        }
    }
```
