<!-- GFM-TOC -->
* [动态规划部分笔记总结](#动态规划部分笔记总结)
    * [第一个动态规划问题](#第一个动态规划问题)
    * [发现重叠子问题](#发现重叠子问题)
    * [状态的定义和状态转移](#状态的定义和状态转移)
    * [面试中的0-1背包问题 ](#面试中的0-1背包问题)
    * [LIS问题  ](#LIS问题)
    * [LCS，最短路，求动态规划的具体解以及更多 ](#LCS，最短路，求动态规划的具体解以及更多)
    * [股票交易问题](#股票交易问题)
    * [更多动态规划的问题](#更多动态规划的问题)
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
## 股票交易问题
相关题目：
* [121.买卖股票的最佳时机](#121)
* [122.买卖股票的最佳时机(2)](#122)
* [123.买卖股票的最佳时机(3)](#123)
* [188.买卖股票的最佳时机(4)](#188)
* [309.买卖股票时机含冷冻期](#309)
* [714.买卖股票时机含手续费](#714)
### 121
给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。

注意你不能在买入股票前卖出股票。

示例 1:

输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。

示例 2:

输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。

- 实现：
```java
public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n==0){
            return 0;
        }
        int max=0;  //记录最大利润
        for (int i = 1; i <n ; i++) {
            for (int j = 0; j < i; j++) {
                //prices[i]-prices[j]:一次买卖的利润
                max=Math.max(max,prices[i]-prices[j]);
            }
        }
        return max;
    }
```
### 122
给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。**你可以尽可能地完成更多的交易（多次买卖一支股票）**。

注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:

输入: [7,1,5,3,6,4]
输出: 7

解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。

示例 2:

输入: [1,2,3,4,5]
输出: 4

解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。

示例 3:

输入: [7,6,4,3,1]
输出: 0

解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。

- 实现：
```java
//贪心策略：只要有上升就进行交易
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n==0||n==1){
            return 0;
        }
        int max=0;
        for (int i = 1; i < n; i++) {
            if (prices[i]>prices[i-1]){
                max+=prices[i]-prices[i-1];
            }
        }
        return max;
    }
```
### 123
给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。**你最多可以完成 两笔 交易。**

注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:

输入: [3,3,5,0,0,3,1,4]
输出: 6

解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。

示例 2:

输入: [1,2,3,4,5]
输出: 4

解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。

示例 3:

输入: [7,6,4,3,1] 
输出: 0 

解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。

- 实现：
```java
public int maxProfit(int[] prices) {
        int n=prices.length;
        if (n==0||n==1){
            return 0;
        }
        //第一次交易
        int hold1=Integer.MIN_VALUE;
        int unhold1=0;
        //第二次交易
        int hold2=Integer.MIN_VALUE;
        int unhold2=0;
        for (int i = 0; i < n; i++) {
            hold1=Math.max(hold1,-prices[i]);
            unhold1=Math.max(unhold1,hold1+prices[i]);
            hold2=Math.max(hold2,unhold1-prices[i]);
            unhold2=Math.max(unhold2,hold2+prices[i]);
        }
        return unhold2;
    }
```
### 188
给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。**你最多可以完成 k 笔交易。**

注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:

输入: [2,4,1], k = 2
输出: 2

解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。

示例 2:

输入: [3,2,6,5,0,3], k = 2
输出: 7

解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
     
- 实现：
```java
//122题+123题
    public int maxProfit(int k, int[] prices) {
        int n=prices.length;
        if (n==0||n==1||k==0){
            return 0;
        }
        //当2k>n,相当于可以进行不限次数的交易
        //退化为122题
        if (k>n/2){
            return maxProfit(prices);
        }
        int[] holds=new int[k];
        int[] unholds=new int[k];
        Arrays.fill(holds,Integer.MIN_VALUE);
        Arrays.fill(unholds,0);
        for (int i = 0; i < n; i++) {
            //做K次交易
            for (int j = 0; j < k; j++) {
                unholds[j] = Math.max(unholds[j], holds[j] + prices[i]);
                if (j==0){
                    holds[j]=Math.max(holds[j],-prices[i]);
                }else {
                    holds[j] = Math.max(holds[j], unholds[j-1] - prices[i]);
                }
            }
        }
        return Math.max(unholds[k-1],holds[k-1]);
    }

    private int maxProfit(int[] prices){
        int n=prices.length;
        if (n==0||n==1){
            return 0;
        }
        int res=0;
        for (int i = 1; i <n ; i++) {
            if (prices[i]>prices[i-1]){
                res+=prices[i]-prices[i-1];
            }
        }
        return res;
    }
```
### 309
给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​

设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

**卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。**

示例:

输入: [1,2,3,0,2]
输出: 3 

解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
- 实现：
```java
public int maxProfit(int[] prices) {
        int n=prices.length;
        if (n<=1){
            return 0;
        }
        int[] hold=new int[n];  //hold[i]:第i天持有股票的最大收益
        int[] unhold=new int[n];    //unhold[i]:第i天不持有股票的最大收益


        hold[0]=-prices[0];
        hold[1]=Math.max(hold[0],-prices[1]);
        unhold[1]=Math.max(unhold[0],hold[0]+prices[1]);
        for (int i = 2; i < n; i++) {
            //没有买入和买入
            hold[i] = Math.max(hold[i-1], unhold[i-2] - prices[i]);
            //没有卖出和卖出
            unhold[i] = Math.max(unhold[i-1], hold[i-1] + prices[i]);

        }
        return unhold[n-1];
    }
```
### 714
给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。

**你可以无限次地完成交易，但是你每次交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。**

返回获得利润的最大值。

示例 1:

输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
输出: 8

解释: 能够达到的最大利润:  

在此处买入 prices[0] = 1

在此处卖出 prices[3] = 8

在此处买入 prices[4] = 4

在此处卖出 prices[5] = 9

总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.

注意:

0 < prices.length <= 50000.

0 < prices[i] < 50000.

0 <= fee < 50000.

- 实现：
```java
//与309题类似，但不需要冷却期
    public int maxProfit(int[] prices, int fee) {
        int n=prices.length;
        if (n==0||n==1){
            return 0;
        }
        int[] hold=new int[n];
        int[] unhold=new int[n];
        hold[0]=-prices[0];
        hold[1]=Math.max(-prices[0],-prices[1]);
        for (int i = 1; i <n ; i++) {
            hold[i]=Math.max(unhold[i-1]-prices[i],hold[i-1]);
            unhold[i]=Math.max(hold[i-1]+prices[i]-fee,unhold[i-1]);
        }
        return Math.max(hold[n-1],unhold[n-1]);
    }
```
## 更多动态规划的问题
相关题目：
* [53.最大子序和](#53)
### 53
给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例:

输入: [-2,1,-3,4,-1,2,1,-5,4],
输出: 6

解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

进阶:

如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。

- 实现：
```java
public int maxSubArray(int[] nums){
        //全局最大值
        int max=nums[0];
        //局部最大值:当前的连续子序和
        int curMax=nums[0];
        for (int i = 1; i < nums.length; i++) {
            //继续累加
            if (curMax>=0){
                curMax+=nums[i];
            }else//curMax<0:重新在i位置选择连续子数组
                {
                curMax=nums[i];
            }
            //从局部最大值中选择出全局最大值
            max=Math.max(max,curMax);
        }
        return max;
    }
```
# 参考资料

[玩儿转算法面试 - 课程官方代码仓](https://github.com/liuyubobobo/Play-with-Algorithm-Interview)
