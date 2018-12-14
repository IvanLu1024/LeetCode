<!-- GFM-TOC -->
* [链表部分的笔记总结](#链表部分的笔记总结)
    * [链表，在节点间穿针引线](#链表，在节点间穿针引线)
    * [设立链表的虚拟头结点](#设立链表的虚拟头结点)
    * [复杂的穿针引线](#复杂的穿针引线)
    * [不仅仅是穿针引线](#不仅仅是穿针引线)
    * [链表与双指针](#链表与双指针)
* [参考资料](#参考资料)
<!-- GFM-TOC -->
# 链表部分的笔记总结
## 链表，在节点间穿针引线
相关题目：
* [206.反转链表](#206)
* [92.反转链表（2）](#92)
### 206
反转一个单链表。

示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
进阶:
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？

- 分析：

链表的反转的最终结果应该是如下图所示的效果，这样更加便于理解
![](../pict/linkedList_01.png)
此时，需要设置三个指针，pre：指向前一个结点，cur：指向当前结点，next：指向下一个结点
![](../pict/linkedList_02.png)
在链表的遍历过程中，不断地将当前结点(cur)指向前一个结点(pre),利用next记录了当前结点的下一个指针，
这样遍历的过程中就不会丢失下一个结点的记录。下一次循环将cur指向next，就这样不断循环下去，直到链表结尾。
**最后，pre指向的结点是原链表的尾节点，也就是翻转后的头结点。**
![](../pict/linkedList_03.png)

- 实现：
```java
public ListNode reverseList(ListNode head) {
        if (head==null||head.next==null){
            return head;
        }
        ListNode pre=null;
        ListNode cur=head;
        while (cur!=null){
            //这样可以确保next不抛出空指针异常
            ListNode next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;

        }
        return pre;
    }
```
### 92
反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。

说明:
1 ≤ m ≤ n ≤ 链表长度。

示例:

输入: 1->2->3->4->5->NULL, m = 2, n = 4
输出: 1->4->3->2->5->NULL
- 分析：

首先设置一个虚拟头结点，为了方便的操作头指针。

设置一个pre，用于指向需要翻转位置的前一个位置；

……
- 实现：
```java
public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode newHead =new ListNode(0);
        newHead.next=head;
        ListNode pre=newHead;
        //首先寻找到开始反转的位置
        for (int i = 1; i < m; i++) {
            pre=pre.next;
        }
        ListNode cur=pre.next;

        for (int i = 0; i < n - m; i++) {
            ListNode post=cur.next;

            cur.next=post.next;
            post.next=pre.next;
            pre.next=post;
        }


        return newHead.next;
    }
```
## 设立链表的虚拟头结点

## 复杂的穿针引线 

## 不仅仅是穿针引线 

## 链表与双指针

# 参考资料

[玩儿转算法面试 - 课程官方代码仓](https://github.com/liuyubobobo/Play-with-Algorithm-Interview)

