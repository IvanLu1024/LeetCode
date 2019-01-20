<!-- GFM-TOC -->
* [链表部分的笔记总结](#链表部分的笔记总结)
    * [链表，在节点间穿针引线](#链表，在节点间穿针引线)
    * [设立链表的虚拟头结点](#设立链表的虚拟头结点)
    * [复杂的穿针引线](#复杂的穿针引线)
    * [不仅仅是穿针引线](#不仅仅是穿针引线)
    * [链表与双指针](#链表与双指针)
    * [Floyd环检测算法](#Floyd环检测算法)
* [参考资料](#参考资料)
<!-- GFM-TOC -->
# 链表部分的笔记总结
## 链表，在节点间穿针引线
相关题目：
* [206.反转链表](#206)
* [92.反转链表（2）](#92)   
* [83.](#83)
* [86.](#86)
* [328.](#328)
* [2.两数相加](#2)
* [445.](#445)
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
### 83 
- 删除排序链表中的重复元素

给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

示例 1:

输入: 1->1->2
输出: 1->2
示例 2:

输入: 1->1->2->3->3
输出: 1->2->3
- 实现：
```java
public ListNode deleteDuplicates(ListNode head) {
        if (head==null||head.next==null)
            return head;
        ListNode cur=head;
        ListNode post=head.next;

        while (post!=null){
            if (cur.val!=post.val){
                cur.next=post;
                cur=post;
            }
            post=post.next;
        }
        cur.next=null;

        return head;
    }
```
### 86 
- 分隔链表

给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。

你应当保留两个分区中每个节点的初始相对位置。

示例:

输入: head = 1->4->3->2->5->2, x = 3
输出: 1->2->2->4->3->5

- 实现：
```java
public ListNode partition(ListNode head, int x) {
        if (head==null){
            return head;
        }
        //左部分:记录小于x的结点
        ListNode left=new ListNode(-1);
        //右部分:记录大于或等于x的结点
        ListNode right=new ListNode(-1);
        //记录左右部分的头结点
        ListNode leftHead=left,rightHead=right;
        //
        while (head!=null){
            //遇到小于x的结点，用left记录
            if (head.val<x){
                left.next=head;
                left=left.next;
            }else {
                right.next=head;
                right=right.next;
            }
            head=head.next;
        }
        //链接左右两部分
        left.next=rightHead.next;
        //设置尾部为空
        right.next=null;
        return leftHead.next;
    }
```
### 328 
- 奇偶链表

给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。

请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。

示例 1:

输入: 1->2->3->4->5->NULL
输出: 1->3->5->2->4->NULL
示例 2:

输入: 2->1->3->5->6->4->7->NULL 
输出: 2->3->6->7->1->5->4->NULL
说明:

应当保持奇数节点和偶数节点的相对顺序。
链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。

- 实现：
```java
//与86题类似
    public ListNode oddEvenList(ListNode head) {
        if (head==null||head.next==null){
            return head;
        }
        ListNode odd = new ListNode(-1);
        ListNode even = new ListNode(-1);
        ListNode oddHead=odd;
        ListNode evenHead=even;
        int count=1;
        while (head!=null){
            if (count%2==1){
                odd.next=head;
                odd=odd.next;
            }else {
                even.next=head;
                even=even.next;
            }
            head=head.next;
            count++;
        }
        odd.next=evenHead.next;
        even.next=null;
        return oddHead.next;
    }
```
### 2 

- 两数相加

给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807

- 实现：
```java
public ListNode addTwoNumbers2(ListNode l1, ListNode l2){
        if (l1==null||l2==null){
            return l1==null?l2:l1;
        }
        int sum=0;
        ListNode cur = new ListNode(-1);
        ListNode head=cur;
        while (l1!=null||l2!=null||sum!=0){
            if (l1!=null){
                sum+=l1.val;
                l1=l1.next;
            }
            if (l2!=null){
                sum+=l2.val;
                l2=l2.next;
            }
            cur.next=new ListNode(sum%10);
            cur=cur.next;
            sum/=10;
        }
        return head.next;
    }
```
### 445
- 两数相加（2）

给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。

 

你可以假设除了数字 0 之外，这两个数字都不会以零开头。

进阶:

如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。

示例:

输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
输出: 7 -> 8 -> 0 -> 7

- 实现：
```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        Stack<Integer> resStack = new Stack<>();
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                stack1.add(l1.val);
                l1 = l1.next;
            }
            if (l2 != null) {
                stack2.add(l2.val);
                l2 = l2.next;
            }
        }
        int num1, num2;
        int count = 0;
        while (!stack1.empty() || !stack2.empty()) {

            if (!stack1.empty()) {
                num1 = stack1.pop();
            } else {
                num1 = 0;
            }
            if (!stack2.empty()) {
                num2 = stack2.pop();
            } else {
                num2 = 0;
            }
            int sum = num1 + num2 + count;
            resStack.add(sum % 10);
            count = sum / 10;
        }
        if (count != 0) {
            resStack.add(count);
        }
        ListNode res = new ListNode(-1);
        ListNode resHead = res;

        while (!resStack.empty()) {
            res.next = new ListNode(resStack.pop());
            res = res.next;
        }
        res.next = null;
        return resHead.next;
    }
```

## 设立链表的虚拟头结点

## 复杂的穿针引线 

## 不仅仅是穿针引线 

## 链表与双指针

## Floyd环检测算法
相关题目：
* [141.环形链路](#141)
* [142.环形链路（2）](#142)
### 141
给定一个链表，判断链表中是否有环。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。

示例 1：

输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。

实现：
```java
public boolean hasCycle(ListNode head) {
        if (head==null||head.next==null){
            return false;
        }
        ListNode slow=head;
        ListNode fast=head.next;
        while (slow!=null&&fast!=null&&fast.next!=null){
            if (slow==fast){
                return true;
            }
            slow=slow.next;
            fast=fast.next.next;
        }
        return false;
    }
```
### 142
给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。

说明：不允许修改给定的链表。

- 实现：
```java
public ListNode detectCycle(ListNode head) {
        if (head==null||head.next==null){
            return null;
        }
        ListNode slow=head;
        ListNode fast=head.next;
        int slowCount=1,fastCount=2;
        while (slow!=null&&fast!=null&&fast.next!=null){
            if (slow==fast){
                //cycleLen=fastCount-slowCount，一圈的长度
                return getCycleTail(head,fastCount-slowCount);
            }
            slow=slow.next;
            slowCount++;
            fast=fast.next.next;
            fastCount+=2;
        }
        return null;
    }
    //根据循环部分的长度来获取循环的头结点
    private ListNode getCycleTail(ListNode head,int cycleLen){
        //利用两个指针，间隔保持cycleLen
        ListNode slow=head;
        ListNode fast=head;
        for (int i = 0; i < cycleLen; i++) {
            fast=fast.next;
        }
        while (fast!=slow){
            fast=fast.next;
            slow=slow.next;
        }
        //当两个指针指向同一个结点的时候，表明快指针正好比慢指针快了整整一圈
        return fast;
    }
```

# 参考资料

[玩儿转算法面试 - 课程官方代码仓](https://github.com/liuyubobobo/Play-with-Algorithm-Interview)

