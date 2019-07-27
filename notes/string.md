## 相关题目：

* [14.最长公共前缀](#14)
* [151.翻转字符串里的单词](#151)
* [541.翻转字符串里的单词(2)](#541)
* [557.翻转字符串里的单词(3)](#557)
* [387.字符串中的第一个唯一字符](#387)
* [8.字符串转换整数 (atoi)](#8)
* [43.字符串相乘](#43)
* [567.字符串的排列](#567)





## 14

编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。

示例 1:

输入: ["flower","flow","flight"]
输出: "fl"
示例 2:

输入: ["dog","racecar","car"]
输出: ""

解释: 输入不存在公共前缀。
说明:

所有输入只包含小写字母 a-z 。

- 实现：
```java
public String longestCommonPrefix(String[] strs) {
        if (strs==null||strs.length==0){
            return "";
        }
        StringBuilder sb= new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            //公共前缀的元素一定来自于第一个字符串
            char c = strs[0].charAt(i);
            //从其他所有字符串中按位搜索
            for (int j = 1; j < strs.length; j++) {
                //如果当前的字符串已经遍历结束或者存在不同的元素就直接返回结果
                if (i>=strs[j].length()||strs[j].charAt(i)!=c){
                    return sb.toString();
                }
            }
            //若其他字符串的这一位都相等的话则添加到结果集合中
            sb.append(c);
        }
        return sb.toString();
    }
```
### 151
给定一个字符串，逐个翻转字符串中的每个单词。

示例:  

输入: "the sky is blue",
输出: "blue is sky the".
说明:

无空格字符构成一个单词。
输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。

如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。

进阶: 请选用C语言的用户尝试使用 O(1) 空间复杂度的原地解法。

- 分析：

遍历一遍字符串，将该字符串中的单词记录在一个集合中，最后再将集合从后向前重新构建一个新的字符串

- 实现：
```java
public String reverseWords(String s) {
        //为了去除字符串首尾两边的空格
        String trim = s.trim();
        int n = trim.length();
        if (n==0){
            return "";
        }
        char[] chars = trim.toCharArray();
        //定义一个起始位置，为了方便后面截取字符串
        int start=0;
        //存放单词的集合
        List<String> list = new ArrayList<>();
        //*这里需要注意subString的起始位置和终止位置的细节！
        for (int i = start; i < n; ) {
            //当遍历到尾部的时候，直接截取
            if (i==n-1){
                String substring = trim.substring(start, i+1);
                list.add(substring);
            }
            //当不为空格的时候，继续向前移动
            if (chars[i]!=' '){
                i++;
            }else   //当前位置为空格的时候，此时需要截取字符串
                {
                    //subString：trim[start,i),前闭后开
                String substring = trim.substring(start, i);
                list.add(substring);
                start=i+1;
                //为了处理连续空格，确保下次循环的起始位置为字符
                while (chars[start]==' '){
                    start++;
                }
                i=start;
            }

        }
        //将集合从后向前重新构建新的字符串
        StringBuilder sb= new StringBuilder();
        for (int i = list.size()-1; i >=0; i--) {
            if (i!=0){
                sb.append(list.get(i)+" ");
            }else {
                sb.append(list.get(i));
            }
        }
        return sb.toString();
    }
```
### 541
给定一个字符串和一个整数 k，你需要对从字符串开头算起的每个 2k 个字符的前k个字符进行反转。如果剩余少于 k 个字符，则将剩余的所有全部反转。如果有小于 2k 但大于或等于 k 个字符，则反转前 k 个字符，并将剩余的字符保持原样。

示例:

输入: s = "abcdefg", k = 2
输出: "bacdfeg"
要求:

该字符串只包含小写的英文字母。
给定字符串的长度和 k 在[1, 10000]范围内。

- 分析：

直接使用一个循环，而循环的增量为2k，每次翻转从i到开始的k个字符。

- 实现：
```java
public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int n = s.length();
        if (n==0){
            return "";
        }
        //每个 2k 个字符, 翻转前k个字符
        for (int i = 0; i <n ; i+=(2*k)) {
            reverse(chars,i,i+k-1);
        }
        return new String(chars);
    }

    /**
     * 翻转字符数组
     *
     * @param chars
     * @param l 起始坐标
     * @param r 终止坐标
     */
    private void reverse(char[] chars,int l,int r){
        //为了避免传进来的r可能会大于数组长度,最后一个i+k-1可能>=n-1
        r=Math.min(r,chars.length-1);
        while (l<r){
            char c = chars[l];
            chars[l]=chars[r];
            chars[r]=c;
            l++;
            r--;
        }
    }
```
### 557
给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。

示例 1:

输入: "Let's take LeetCode contest"

输出: "s'teL ekat edoCteeL tsetnoc" 

注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。

- 实现：
```java
public String reverseWords(String s) {
        if (s==null||s.length()==0){
            return "";
        }
        //在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格
        //所以按照空格来切分原字符串
        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();
        //将每个单词都翻转，并且按照空格来分割重新构建一个新的字符串
        for (String str:strs){
            char[] chars = str.toCharArray();
            reverse(chars);
            String reStr = new String(chars);
            sb.append(reStr+" ");
        }
        String res = sb.toString();
        //为了去除最后一个单词后面的空格
        return res.trim();
    }
    private void reverse(char[] chars){
        int l=0,h=chars.length-1;
        while (l<h){
            char c=chars[l];
            chars[l]=chars[h];
            chars[h]=c;
            l++;
            h--;
        }
    }
```
### 387
给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

案例:

s = "leetcode"
返回 0.

s = "loveleetcode",
返回 2.

- 分析：

解法1：

利用辅助空间，首先遍历一遍字符串来记录各字符的出现频率，再遍历一遍字符串，发现第一个字符频率为1的就直接返回，
没有则循环结束，返回-1；

解法2：

按照小写字母的顺序遍历，在字符串中查找出现的第一次和最后一次的下标，如果下标相同则表明只出现了一次，取其中的最小值
就是第一次出现的字符。

- 实现：
```java
public int firstUniqChar(String s) {
        if (s.length()==0){
            return -1;
        }
        int[] freq=new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            freq[chars[i]-'a']++;
        }
        for (int i = 0; i < chars.length; i++) {
            int j = chars[i]-'a';
            if (freq[j]==1){
                return i;
            }
        }
        return -1;
    }

    public int firstUniqChar1(String s){
        int n = s.length();
        if (n==0){
            return -1;
        }
        //用于记录第一个出现字符的下标
        int index=n;
        char[] chars = s.toCharArray();
        for (int i = 'a'; i <= 'z'; i++) {
            int first = s.indexOf(i);
            int last = s.lastIndexOf(i);
            //如果第一次出现和最后一次出现的下标相等的话
            // 说明是只出现一次的字符
            if (first==last&&first!=-1){
                //取最小值，则是寻找第一个出现的字符
                index=Math.min(index,first);
            }
        }
        if (index==n){
            return -1;
        }
        return index;
    }
```
### 8
请你来实现一个 atoi 函数，使其能将字符串转换成整数。

首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。

当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0。

说明：

假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，qing返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。

- 实现：
```java
public int myAtoi(String str) {
        str=str.trim();
        int n = str.length();
        if (str==null||n==0){
            return 0;
        }
        //起始位置
        int start=0;
        char[] chars = str.toCharArray();
        if (start>=n||Character.isLetter(chars[start])){
            return 0;
        }
        boolean flag=false;
        if (start<n&&chars[start]=='-'){
            start++;
            flag=true;
        }else if (start<n&&chars[start]=='+'){
            start++;
        }
        if (start>n-1){
            return 0;
        }
        int end=start+1;
        while (end< str.length()&&Character.isDigit(chars[end])){
            end++;
        }
        if (!Character.isDigit(chars[start])){
            return 0;
        }
        //确保截取部分是数值
        String numStr=str.substring(start,end);
        long v = Long.parseLong(numStr);
        if (flag){
            v=0-v;
        }
        if (v>Integer.MAX_VALUE){
            v=Integer.MAX_VALUE;
        }
        if (v<Integer.MIN_VALUE){
            v=Integer.MIN_VALUE;
        }
        int res= (int) v;
        return res;
    }
```
### 43
给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

 示例 1:

 输入: num1 = "2", num2 = "3"
 输出: "6"
 示例 2:

 输入: num1 = "123", num2 = "456"
 输出: "56088"
 说明：

 num1 和 num2 的长度小于110。

 num1 和 num2 只包含数字 0-9。

 num1 和 num2 均不以零开头，除非是数字 0 本身。

 **不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。**

- 分析：

由于num1和num2均为字符串并且长度是小于110的，那么这说明不能简单地将字符串转化为数字来直接
进行乘积。

这里可以将小学数学中乘法的过程回忆一下：**逐位相乘并加和**，那么字符串的相乘可以转化为这样的过程来求解。


- 实现：
```java
public String multiply(String num1, String num2) {
        if (num1.length()==0||num2.length()==0){
            return null;
        }
        int n1 = num1.length();
        int n2 = num2.length();
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        if (chars1[0]=='0'||chars2[0]=='0'){
            return "0";
        }
        int[ ] res = new int[n1+n2];
        StringBuilder sb = new StringBuilder();
        for (int i = n1-1; i >=0; i--) {
            for (int j = n2-1; j >=0 ; j--) {
                int i1 = chars1[i]-'0';
                int j2 = chars2[j]-'0';
                //当前位数的乘积
                int mul = i1*j2;
                int hIndex = i+j;
                int lIndex = i+j+1;
                //加上之前这一位上的数值
                int sum = mul + res[lIndex];
                //进位
                res[hIndex]+=sum/10;
                res[lIndex]=sum%10;
            }
        }
        for(int i:res){
            if (sb.length()==0&&i==0){
                continue;
            }
            sb.append(i);
        }
        return sb.toString();
    }

    public String multiply1(String num1, String num2){
        int n1 = num1.length();
        int n2 = num2.length();
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        if (chars1[0]=='0'||chars2[0]=='0'){
            return "0";
        }
        int[ ] res = new int[n1+n2];
        //首先计算各位上的乘积
        for (int i = n1-1; i >=0; i--) {
            for (int j = n2-1; j >=0 ; j--){
                int mul = (chars1[i] - '0') * (chars2[j] - '0');
                res[i+j+1] += mul;
            }
        }
        //进位的处理
        for (int i = res.length-1; i >0; i--) {
            int out = res[i]/10;
            res[i-1] +=out;
            res[i]%=10;
        }
        StringBuilder sb = new StringBuilder();
        for(int i:res){
            if (sb.length()==0&&i==0){
                continue;
            }
            sb.append(i);
        }
        return sb.toString();

    }
```
### 567
给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。

换句话说，第一个字符串的排列之一是第二个字符串的子串。

示例1:

输入: s1 = "ab" s2 = "eidbaooo"
输出: True
解释: s2 包含 s1 的排列之一 ("ba").


示例2:

输入: s1= "ab" s2 = "eidboaoo"
输出: False


**注意：
输入的字符串只包含小写字母
两个字符串的长度都在 [1, 10,000] 之间**

- 实现：
```java
public boolean checkInclusion(String s1, String s2) {
        if (s1==null|| s1.length()==0){
            return true;
        }
        //记录s1中各字符出现的频率
        int[] freq1 = new int[26];
        //记录s2中子字符串各字符出现的频率
        int[] freq2 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            freq1[s1.charAt(i)-'a']++;
        }
        for (int i = 0; i < s2.length(); i++) {
            freq2[s2.charAt(i)-'a']++;
            //确保freq2记录的范围是s2[i-l1,i]即子字符串长度等于s1的长度
            if (i>=s1.length()){
                freq2[s2.charAt(i-s1.length())-'a']--;
            }
            //若各个字符出现频率相同则证明s2 是否包含 s1 的排列
            if (Arrays.equals(freq1,freq2)){
                return true;
            }
        }
        return false;
    }
```