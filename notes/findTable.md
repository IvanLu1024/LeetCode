<!-- GFM-TOC -->
* [查找表部分笔记总结](#查找表部分笔记总结)
    * [set的使用](#set的使用)
    * [map的使用](#map的使用)
    * [set和map不同底层实现的区别](#set和map不同底层实现的区别)
    * [灵活选择键值 ](#灵活选择键值)
    * [查找表和滑动窗口](#查找表和滑动窗口)
    * [二分搜索树底层实现的顺序性 ](#二分搜索树底层实现的顺序性)
* [参考资料](#参考资料)
<!-- GFM-TOC -->
# 查找表部分笔记总结
![](../pict/find_01.png)
## set的使用
相关题目：
* [349.两个数组的交集](#349)
* [202.快乐数](#202)
### 349
给定两个数组，编写一个函数来计算它们的交集。

示例 1:

输入: nums1 = [1,2,2,1], nums2 = [2,2]
输出: [2]
示例 2:

输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出: [9,4]
说明:

输出结果中的每个元素一定是唯一的。
我们可以不考虑输出结果的顺序。
- 分析：

利用Set的性质：每个元素都是唯一的。
分别设置两个set，一个用来存放nums1的元素，另一个用来存放nums2的元素；
由于结果要求输出的是int数组,所以最后需要做一个转化。
- 实现：
```java
public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set=new HashSet<>();
        Set<Integer> res=new HashSet<Integer>();
        for (int i:nums1){
            set.add(i);
        }
        for (int i:nums2){
           if ( set.contains(i)){
               res.add(i);
           }
        }
        
        int[] reslut = new int[res.size()];
        int j=0;
        for (Integer i:res){
            reslut[j++]=i;
        }
        return reslut;

    }
```
### 202
编写一个算法来判断一个数是不是“快乐数”。

一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。

示例: 

输入: 19
输出: true
解释: 
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
- 分析：

使用set来避免死循环的出现，当n重复出现的时候表明出现了环路（死循环），这就表明n不可能转化为1了。

- 实现：
```java
public boolean isHappy(int n) {
        if (n<1){
            return false;
        }
        Set<Integer> set=new HashSet<>();
        while (!set.contains(n)){
            if (n==1){
                return true;
            }
            set.add(n);
            n=getHappy(n);
        }
        return false;
    }

    private int getHappy(int n) {
        int t,sum=0;
        while (n>0){
            t=n%10;
            n/=10;
            sum+=t*t;
        }
        return sum;
    }
```
## map的使用
相关题目：
* [350. 两个数组的交集（2）](#350)
### 350
给定两个数组，编写一个函数来计算它们的交集。

示例 1:

输入: nums1 = [1,2,2,1], nums2 = [2,2]
输出: [2,2]
示例 2:

输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出: [4,9]
说明：

输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
我们可以不考虑输出结果的顺序。
进阶:

如果给定的数组已经排好序呢？你将如何优化你的算法？

如果 nums1 的大小比 nums2 小很多，哪种方法更优？

如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？

- 分析:

因为题目中要求出现次数和原数组中一致，那么这是一个键值对（K-V）查找问题。

创建一个map来统计nums1中元素出现的频次，然后再遍历一遍nums2，当元素频次大于0便记录下来并且使得频次减一。



- 实现:
```java
public int[] intersect(int[] nums1, int[] nums2) {
        //K:数组元素；V：出现次数
        Map<Integer,Integer> record=new HashMap<>();
        for (int i:nums1){
            Integer count = record.get(i);
            record.put(i,count==null?1:count+1);
        }
        List<Integer> res=new ArrayList<>();
        for (int i:nums2){
            int c=record.get(i)==null?0:record.get(i);
            if (c>0){
                res.add(i);
                record.put(i,record.get(i)-1);
            }
        }
        int[] reslut = new int[res.size()];
        for (int i=0;i<reslut.length;i++){
            reslut[i]=res.get(i);
        }
        return reslut;
    }
```

## 灵活选择键值
## 查找表和滑动窗口
## 二分搜索树底层实现的顺序性
# 参考资料

[玩儿转算法面试 - 课程官方代码仓](https://github.com/liuyubobobo/Play-with-Algorithm-Interview)

