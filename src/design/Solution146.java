package design;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。

 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。

 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。

 进阶:

 你是否可以在 O(1) 时间复杂度内完成这两种操作？

 示例:

    LRUCache cache = new LRUCache( 2 //缓存容量 );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4


 */
public class Solution146 {
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

    //使用List和Map的解法
    class LRUCache1{
        private int capacity,num;
        private Map<Integer,Integer> map;
        private List<Integer> cache;    //缓存key，确保尾部是最近使用的key

        public LRUCache1(int capacity) {
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



/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
    @Test
    public void test(){
        LRUCache1 obj = new LRUCache1(2);
        obj.put(1,1);
        obj.put(2,2);
        System.out.println(obj.get(1));
        obj.put(3,3);
        System.out.println(obj.get(2));

    }

}
