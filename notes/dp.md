<!-- GFM-TOC -->
* [动态规划部分笔记总结](#动态规划部分笔记总结)
    * [第一个动态规划问题](#第一个动态规划问题)
    * [发现重叠子问题](#发现重叠子问题)
    * [状态的定义和状态转移](#状态的定义和状态转移)
    * [面试中的0-1背包问题 ](#面试中的0-1背包问题)
    * [LIS问题  ](#LIS问题)
    * [LCS，最短路，求动态规划的具体解以及更多 ](#LCS，最短路，求动态规划的具体解以及更多)
    * [更多动态规划的问题]()
* [参考资料](#参考资料)
<!-- GFM-TOC -->
# 动态规划部分笔记总结
![](../pict/dp_01.png)
## 第一个动态规划问题
相关题目：
* [70.爬楼梯](#70)
### 70
## 发现重叠子问题
相关题目：
* [343.整数拆分](#343)
* [279.完全平方数](#279)
* [91.解码方法](#91)
* [62.不同路径](#62)
* [63.不同路径（2）](#63)

### 343
给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。

示例 1:

输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1。
示例 2:

输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
说明: 你可以假设 n 不小于 2 且不大于 58。
- 分析：
- 实现：
```java
    //记忆化搜索
    private int[] memo;
    public int integerBreak1(int n) {
        assert n>=2;
        memo=new int[n+1];
        return breakInteger1(n);
    }


    private int breakInteger1(int n){
        if (n==1){
            return 1;
        }
        //记录分割结果的最大值
        int res=-1;
        if (memo[n]!=0){
            res=memo[n];
        }else {
            //从1到n-1计算分割结果
            for (int i = 1; i <= n - 1; i++) {
                res = max3(res, i * (n - i), i * breakInteger1(n - i));
            }
            memo[n]=res;
        }
        return res;
    }
    private int max3(int a,int b,int c){
        return Math.max(a,Math.max(b,c));
    }

    public int integerBreak2(int n) {
        assert n>=2;
        memo=new int[n+1];
        return breakInteger2(n);

    }

    private int breakInteger2(int n){
        memo[1]=1;
        for (int i = 2; i <=n ; i++) {
            for (int j = 1; j <=i ; j++) {
                //将n分割成j+(i-j)
                memo[i]= max3(memo[i],j*(i-j),j*memo[i-j]);
            }
        }
        return memo[n];
    }
```
### 279
给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

示例 1:

输入: n = 12
输出: 3 
解释: 12 = 4 + 4 + 4.
示例 2:

输入: n = 13
输出: 2
解释: 13 = 4 + 9.
- 分析：

- 实现：
```java
public int numSquares(int n){
        if (n==0){
            return 0;
        }
        memo=new int[n+1];
        Arrays.fill(memo,Integer.MAX_VALUE);
        memo[0]=0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; i-j*j>=0 ; j++) {
                memo[i]=Math.min(memo[i],1+memo[i-j*j]);
            }
        }
        return memo[n];
    }
```
### 91
一条包含字母 A-Z 的消息通过以下方式进行了编码：

'A' -> 1
'B' -> 2
...
'Z' -> 26
给定一个只包含数字的非空字符串，请计算解码方法的总数。

示例 1:

输入: "12"
输出: 2
解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
示例 2:

输入: "226"
输出: 3
解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
### 62
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

问总共有多少条不同的路径？

例如，上图是一个7 x 3 的网格。有多少可能的路径？

说明：m 和 n 的值均不超过 100。

示例 1:

输入: m = 3, n = 2
输出: 3
解释:
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向右 -> 向下
2. 向右 -> 向下 -> 向右
3. 向下 -> 向右 -> 向右
示例 2:

输入: m = 7, n = 3
输出: 28

### 63
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

网格中的障碍物和空位置分别用 1 和 0 来表示。

说明：m 和 n 的值均不超过 100。

示例 1:

输入:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
输出: 2
解释:
3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右

## 状态的定义和状态转移
相关题目：
* [198.打家劫舍](#198)
### 198 

## 面试中的0-1背包问题 
## LIS问题 
## LCS，最短路，求动态规划的具体解以及更多
## 更多动态规划的题目
相关题目：
* [5.最长回文子串](#5)
### 5
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。

示例 2：

输入: "cbbd"
输出: "bb"


- 实现：
解法1：动态规划
```java
public String longestPalindrome(String s) {
        int n = s.length();
        if (n==0){
            return "";
        }
        //记录最长的回文串的起始下标
        int start=0;
        //记录最长回文串的长度
        int maxLen=1;
        //memo[i][j] i:起始下标，j：结束下标，表示是否为回文串
        boolean[][] memo=new  boolean[n][n];
        char[] chars = s.toCharArray();
        //初始化，
        for (int i = 0; i < n; i++) {
            memo[i][i]=true;
                if (i+1<n&&chars[i]==chars[i+1]){
                    memo[i][i+1]=true;
                    maxLen=2;
                    start=i;
                }
        }
        //若s[i]和s[j]相等，并且memo[i+1][j-1]为true，此时可以构建新的回文串s[i,j]
        //从后向前地搜索
        for (int i = n-1; i >= 0; i--) {
            for (int j = i+2; j <n ; j++) {
                if (chars[i]==chars[j]&&memo[i+1][j-1]){
                    memo[i][j]=true;
                    if (j-i+1>maxLen){
                        maxLen=j-i+1;
                        start=i;
                    }
                }
            }
        }
        return s.substring(start,start+maxLen);
    }
```
解法2：
```java
public String longestPalindrome1(String s){
        if (s==null||s.length()==0){
            return "";
        }
        int start=0,end=0;
        for (int i = 0; i < s.length(); i++) {
            //i：中间位置，若回文串为偶数则是偏左的位置
            int len1 = expand(s, i, i);            //回文串长度为奇数
            int len2 = expand(s, i, i + 1);     //回文串长度为偶数
            int len = Math.max(len1,len2);
            if (len>end-start){
                //len-1：对于奇数没有影响，对于偶数位置会偏左
                start = i - (len-1)/2;
                end = i +(len/2);
            }
        }
        return s.substring(start,end+1);
    }

    //从中间开始拓展
    private int expand(String s,int l ,int r){
        while (l>=0&&r<s.length()&&s.charAt(l)==s.charAt(r)){
            l--;
            r++;
        }
        return r-l-1;
    }
```
# 参考资料

[玩儿转算法面试 - 课程官方代码仓](https://github.com/liuyubobobo/Play-with-Algorithm-Interview)
