# 设计部分的总结笔记
相关题目：
* [146.LRU缓存机制](#146)
* [155.最小栈](#155)
* [225.用队列实现栈](#225)
* [232.用栈实现队列](#232)
### 146
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
- 实现：

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

### 155
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
- 实现：

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
### 225

### 232
    
