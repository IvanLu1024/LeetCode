<!-- GFM-TOC -->
* [数组部分总结笔记](#数组部分总结笔记)
    * [二分查找](#二分查找)
    * [简单的面试题](#简单的面试题)
    * [三路快排思想的应用](#三路快排思想的应用)
    * [双指针](#双指针)
        * [对撞指针](#对撞指针)
        * [滑动窗口](#滑动窗口)
* [参考资料](#参考资料)
<!-- GFM-TOC -->

# 数组部分总结笔记
## 二分查找
二分搜索（英语：binary search），也称折半搜索（英语：half-interval search）、对数搜索（英语：logarithmic search），是一种在有序数组中查找某一**特定元素**的搜索算法。搜索过程从数组的中间元素开始，如果中间元素正好是要查找的元素，则搜索过程结束；如果某一特定元素大于或者小于中间元素，则在数组大于或小于中间元素的那一半中查找，而且跟开始一样从中间元素开始比较。如果在某一步骤数组为空，则代表找不到。这种搜索算法每一次比较都使搜索范围缩小一半。
<div align=center>

![](../pict/array_01.png)

</div>

```java
public  int binarySearch(Comparable[] arr, int n, Comparable target){

        int l = 0, r = n - 1; // 在[l...r]的范围里寻找target
        while(l <= r){    // 当 l == r时,区间[l...r]依然是有效的
            int mid = l + (r - l) / 2;
            if(arr[mid].compareTo(target) == 0) return mid;
            if(target.compareTo(arr[mid]) > 0)
                l = mid + 1;  // target在[mid+1...r]中; [l...mid]一定没有target
            else    // target < arr[mid]
                r = mid - 1;  // target在[l...mid-1]中; [mid...r]一定没有target
        }

        return -1;
    }
```
## 简单的面试题

相关题目：
 * [283]()
 * [27](#)
 * [26](#)
 * [80](#)
## 三路快排思想的应用
当数组中有大量元素重复出现的时候，普通的快排算法会退化为O(n^2)，这样我们可以考虑使用三路快排算法。
三路快排要做的事情，其实就是将数组分成三部分：小于v，等于v和大于v，之后递归的对小于v和大于v部分进行排序就好了。

相关题目：
 * [75](#75)
 * [88](#)
 * [215](#)
## 双指针
双指针主要用于遍历数组，两个指针指向不同的元素，从而协同完成任务。
### 对撞指针
使用对撞指针的前提是数组的有序的，分别设置一个头指针和一个尾指针来遍历数组，当满足一定条件来分别移动两个指针的位置，最终完成任务。

相关题目：
 * [167-Two Sum II - Input Array is Sorted](#)
 * [125]()
 * [344]()
 * [345]()
 * [11]()

### 滑动窗口
当求解的时候，需要获得数组或者字符串的连续子部分的时候，就可以考虑使用滑动窗口的思想。
num[l,h]为滑动窗口，根据具体的要求，通过遍历的时候，来改变l和h的大小，从而完成任务。
相关题目：
 * [209](#209)
 * [3](#3)
 * [438](#438)
 * [76](#76)
 
#### 209
- 长度最小的子数组

给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。

示例: 

输入: s = 7, nums = [2,3,1,2,4,3]
输出: 2
解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
进阶:

如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。

- 分析：
本题需要求解满足一定条件的连续子数组，那么可以使用滑动窗口的思想；

首先，定义一个滑动窗口num[l,h],由于可能存在不符合条件的结果，那么初始化的时候该滑动窗口的大小为0.
因为该数组的元素都是正整数，所以当不满足大于或等于s的时候，则需要扩大滑动窗口的大小；当满足条件的时候，则尽量减小滑动窗口的大小。直到遍历完该数组，则可以获得最小长度的滑动窗口。

**注意在遍历过程中记录滑动窗口的最小值**

- 实现：
```java
public int minSubArrayLen(int s, int[] nums) {
        int i=0,j=-1;//滑动窗口num[i,j]，由于初始化的时候没有数值，所以j=-1
        int sum=0;
        int minCount=nums.length+1;
        while (i<nums.length){
            if (sum<s&&j<nums.length-1){
                sum+=nums[++j];
            }else {
                sum-=nums[i++];
            }
            if (sum>=s){
                if (j-i+1<minCount)//由于是nums[i,j]，前闭后闭区间
                    minCount=j-i+1;
            }
        }
        if (minCount==nums.length+1)
            return 0;
        return minCount;

    }
```
时间复杂度：O(n)

空间复杂度：O(1)

#### 3
- 无重复字符的最长子串

给定一个字符串，找出不含有重复字符的最长子串的长度。

示例 1:

输入: "abcabcbb"
输出: 3 
解释: 无重复字符的最长子串是 "abc"，其长度为 3。
- 分析：
因为要确保子串无重复的字符，那么需要一个额外的空间来记录每个字符出现的频率。这道题同样可以使用滑动窗口的方法来求解，当待搜索的字符未出现的时候，将其加入滑动窗口。
当该字符已经出现的时候，则将滑动窗口的第一个字符从中移除，移除以后将频率数组中该字符的频率-1。每次遍历的时候，记录滑动窗口的最大值。

判断一个字符是否出现在之前的滑动窗口中，利用一个频率数组记录每个字符出现的次数，当其值为0则表示没有出现，加入滑动窗口后需要将其频率+1。
>补充：ASCII码占用一个字节，可以有0～255共256个取值。
- 实现：
```java
public int lengthOfLongestSubstring(String s) {
        int l=0,h=-1;//s[l,h]为滑动窗口
        int[] freq=new int[256];//用于记录字符出现的频率
        int count=0;//子字符串的长度
        while (l<s.length()){
            if (h<s.length()-1&&freq[s.charAt(h+1)]==0){
                h++;
                freq[s.charAt(h)]++;
            }else {
                freq[s.charAt(l++)]--;
            }
            count=Math.max(count,h-l+1);
        }
        return count;

    }
```
时间复杂度：O(n)

空间复杂度：O(1)

#### 438
- 找到字符串中所有字母异位词

给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。

字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。

说明：

字母异位词指字母相同，但排列不同的字符串。
不考虑答案输出的顺序。
示例 1:

输入:
s: "cbaebabacd" p: "abc"

输出:
[0, 6]

解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。

- 分析：

字母异位词表示该字符串中出现的**字符的次数相同。** 由于字符串中只包含小写英文字母，那么同样需要额外的空间来记录字符出现的频率。

首先，遍历字符串p来统计其中的字符频率。再开始遍历字符串s,此时的滑动窗口大小是**固定**的为p的长度，由于字母异位词的长度必定相等。
当滑动窗口中的频率数组与p的频率数组相同的时候则寻找到字母异位词，记录其起始下标即可。
>注意：当比较两个数组中的值是否对应相等的时候，可以通过 Arrays.equals() 方法比较数组中元素值是否相等。
- 实现：
```java
public List<Integer> findAnagrams(String s, String p) {
        int[] pFreq=new int[26];
        int[] sFreq=new int[26];
        List<Integer> res=new ArrayList<Integer>();
        //首先统计p中出现的字符频率
        int pLen = p.length();
        for (int i=0;i<pLen;i++){
            pFreq[p.charAt(i)-'a']++;
        }
        for (int i=0;i<s.length();i++){
            sFreq[s.charAt(i)-'a']++;
            if (i>=pLen){
                sFreq[s.charAt(i-pLen)-'a']--;
            }
            if (Arrays.equals(sFreq,pFreq)){
                res.add(i-pLen+1);
            }

        }
        return res;

    }
```
时间复杂度：O(n)

空间复杂度：O(1)

#### 76
- 最小覆盖子串

给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。

示例：

输入: S = "ADOBECODEBANC", T = "ABC"
输出: "BANC"
说明：

如果 S 中不存这样的子串，则返回空字符串 ""。
如果 S 中存在这样的子串，我们保证它是唯一的答案。
- 分析：

本题求解的是最小子串，可以使用滑动窗口的思想来求解。此时的滑动窗口大小至少为T的长度，因为需要覆盖T。

设置一个total来记录搜索的自创中包含T中字符的数量，当total等于T的长度的时候，则确保了此时的子串一定包含了T的所有字符。

设置一个l为记录子串的起始位置，每次尽量减小滑动窗口的大小，从滑动窗口的头开始移除字符，当移除的字符是T中所包含的字符的时候，则将total大小减小，并更新l的值。

由于此时的频率数组中，T中未包含的字符对应的值为非正数，当数组的值大于0的时候，则表示T中包含的字符。
- 实现：
```java
public String minWindow(String s, String t) {
        int[] freq=new int[128];
        int sLen = s.length();
        int tLen = t.length();
        if (sLen==0||tLen==0)
            return "";
        int minLen=Integer.MAX_VALUE;
        //首先统计t中出现的字符的频率
        for (int i=0;i<t.length();i++){
            freq[t.charAt(i)]++;
        }
        int l=0;//起始位置
        int total=0;//记录当前包含的字符数量
        for (int i=0,j=0;i<sLen;i++){
            //当t中的字符出现的时候
            if (freq[s.charAt(i)]-->0)
                total++;
            //此时说明已经包含了t
            while (total==tLen){
                if (i-j+1<minLen){
                    minLen=i-j+1;
                    l=j;
                }
                //此时移除的是T包含中的字符
                if (++freq[s.charAt(j++)]>0)
                    total--;
            }

        }
        if (minLen==Integer.MAX_VALUE)
            return "";
        else
            return s.substring(l,l+minLen);

    }
```
时间复杂度：O(n)

空间复杂度：O(1)

#参考资料
[玩儿转算法面试 - 课程官方代码仓](https://github.com/liuyubobobo/Play-with-Algorithm-Interview)


