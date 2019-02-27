package design;

import java.util.*;

/**
 实现一个数据结构支持以下操作：

 Inc(key) - 插入一个新的值为 1 的 key。或者使一个存在的 key 增加一，保证 key 不为空字符串。

 Dec(key) - 如果这个 key 的值是 1，那么把他从数据结构中移除掉。否者使一个存在的 key 值减一。

 如果这个 key 不存在，这个函数不做任何事情。key 保证不为空字符串。

 GetMaxKey() - 返回 key 中值最大的任意一个。如果没有元素存在，返回一个空字符串""。

 GetMinKey() - 返回 key 中值最小的任意一个。如果没有元素存在，返回一个空字符串""。

 挑战：以 O(1) 的时间复杂度实现所有操作。

 */
public class Solution432 {


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
}
