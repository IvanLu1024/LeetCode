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
* [290.单词模式](#290)
* [205.同构字符串](#205)
* [451.根据字符出现的频率排序](#451)
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
### 290
给定一种 pattern(模式) 和一个字符串 str ，判断 str 是否遵循相同的模式。

这里的遵循指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应模式。

示例1:

输入: pattern = "abba", str = "dog cat cat dog"
输出: true
示例 2:

输入:pattern = "abba", str = "dog cat cat fish"
输出: false
示例 3:

输入: pattern = "aaaa", str = "dog cat cat dog"
输出: false
示例 4:

输入: pattern = "abba", str = "dog dog dog dog"
输出: false
说明:
你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。    
- 分析：

模式和单词的关系是一对一的关系，那么有两种方法：
1. 利用两个map，相互指向即可。

如果发现pMap（pattern-word）不存在当前的字符，这种情况下，如果
sMap(word-pattern)存在当前的word则表明它们的关系不是一对一的，则直接返回false；如果不存在的话，则分别将当前的字符和单词放入两个map中。

如果发现pMap中存在当前的字符，取出其对应的word和当前的word进行比较如果不同，则表明不是一对一的关系，返回false；
循环正常结束的话，表明pattern和word能够一一对应，则返回true；

2. 利用一个map

与上面同理，只是在判断如果发现pMap（pattern-word）不存在当前的字符的情况下，在value中发现word存在的话，则说明不是一对一的关系，直接返回false；
其它的操作与法1是类似的，就不赘述。
- 实现：
```java
public boolean wordPattern(String pattern, String str) {
        String[] strs = str.split(" ");
        if (strs.length!=pattern.length()){
            return false;
        }
        Map<Character,String> pMap=new HashMap<>();
        Map<String,Character> sMap=new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (!pMap.containsKey(c)){
                if (sMap.containsKey(strs[i])){
                    return false;
                }
                pMap.put(c,strs[i]);
                sMap.put(strs[i],c);
            }else {
                String s = pMap.get(c);
                if (!s.equals(strs[i])){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean wordPattern1(String pattern, String str){
        String[] strs = str.split(" ");
        if (strs.length!=pattern.length()){
            return false;
        }
        Map<Character,String> pMap=new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (!pMap.containsKey(c)){
                if (pMap.containsValue(strs[i])){
                    return false;
                }
                pMap.put(c,strs[i]);
            }else {
                if (!pMap.get(c).equals(strs[i])){
                    return false;
                }
            }
        }
        return true;
    }
```
### 205
给定两个字符串 s 和 t，判断它们是否是同构的。

如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。

所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。

示例 1:

输入: s = "egg", t = "add"
输出: true
示例 2:

输入: s = "foo", t = "bar"
输出: false
示例 3:

输入: s = "paper", t = "title"
输出: true
说明:
你可以假设 s 和 t 具有相同的长度。
- 分析：

这道题目与上面（290题）的解法几乎如出一辙，需要找出一对一的关系，不再赘述。
- 实现：
```java
public boolean isIsomorphic(String s, String t) {
        if (s.length()!=t.length()){
            return false;
        }
        Map<Character,Character> sMap=new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if (!sMap.containsKey(sChar)){
                if (sMap.containsValue(tChar)){
                    return false;
                }
                sMap.put(sChar,tChar);
            }else {
                if (!sMap.get(sChar).equals(tChar)){
                    return false;
                }
            }
        }
        return true;
    }
```
### 451
给定一个字符串，请将字符串里的字符按照出现的频率降序排列。

示例 1:

输入:
"tree"

输出:
"eert"

解释:
'e'出现两次，'r'和't'都只出现一次。
因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
示例 2:

输入:
"cccaaa"

输出:
"cccaaa"

解释:
'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
注意"cacaca"是不正确的，因为相同的字母必须放在一起。
示例 3:

输入:
"Aabb"

输出:
"bbAa"

解释:
此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
注意'A'和'a'被认为是两种不同的字符。
- 分析：

统计字符出现的频数可以使用辅助数组来做记录，这题中的关键是根据字符的出现频数来重新构建一个新的字符串，在于**怎么选择map中的键值对（K-V）,
那么map的键可以用频数，则值就用字符的集合；**

在重新构建新的字符串的时候，由于字符串中的某个字符出现的频次的范围是在1-n（n：字符串的长度），所以可以从频次的高到低遍历查询map，若
存在则将其构建新的字符串，这里**需要注意每次字符出现的次数是频数。**
- 实现：
```java
public String frequencySort(String s) {
        int[] freq=new int[256];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)]++;
        }
        Map<Integer,List<Character>> map=new HashMap<>();
        for (int i = 0; i < 256; i++) {
            if (freq[i]==0)
                continue;
            if (!map.containsKey(freq[i]))
             {
                map.put(freq[i],new ArrayList<>());
            }
            map.get(freq[i]).add((char)i);
        }
        StringBuilder sb=new StringBuilder();
        for (int i = s.length(); i > 0; i--) {
            if (map.containsKey(i)){
                List<Character> characters = map.get(i);
                for (Character c:characters) {
                    for (int j = 0; j < i; j++) {
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }
```
## 使用查找表的经典问题

## 灵活选择键值
## 查找表和滑动窗口
## 二分搜索树底层实现的顺序性
# 参考资料

[玩儿转算法面试 - 课程官方代码仓](https://github.com/liuyubobobo/Play-with-Algorithm-Interview)

