<!-- GFM-TOC -->

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



![](../pict/dp_01.png)
# 第一个动态规划问题
相关题目：
* [70.爬楼梯](#70)
## 70.爬楼梯

### 描述

假设你正在爬楼梯。需要 *n* 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

**注意：**给定 *n* 是一个正整数。

**示例 1：**

```
输入： 2
输出： 2
解释： 有两种方法可以爬到楼顶。
1.  1 阶 + 1 阶
2.  2 阶
```

**示例 2：**

```
输入： 3
输出： 3
解释： 有三种方法可以爬到楼顶。
1.  1 阶 + 1 阶 + 1 阶
2.  1 阶 + 2 阶
3.  2 阶 + 1 阶
```

### 分析

当n=0，F(n)=0；当n=1，F(n)=1；当n=2，F(n)=2；

当n>=3时，对于到达这个高度有两种选择：1.从n-1处爬1阶；2.从n-2处爬2阶，

所以F(n)=F(n-1)+F(n-2)。

### 实现

```java
public int climbStairs(int n) {
        if(n<=2){
            return n;
        }
        int[] memo=new int[n+1];
        memo[1]=1;
        memo[2]=2;
        for(int i =3;i<=n;i++){
            memo[i]=memo[i-1]+memo[i-2];
        }
        return memo[n];
    }
```





## 发现重叠子问题
相关题目：
* [343.整数拆分](#343)
* [279.完全平方数](#279)
* [91.解码方法](#91)
* [62.不同路径](#62)
* [63.不同路径（2）](#63)

## 343.整数拆分

### 描述

给定一个正整数 *n*，将其拆分为**至少**两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。

**示例 1:**

```
输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1。
```

**示例 2:**

```
输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
```

**说明:** 你可以假设 *n* 不小于 2 且不大于 58。

### 实现

```java
    //记忆化搜索
    private int[] memo;
    public int integerBreak(int n) {
        assert n>=2;
        memo=new int[n+1];
        return breakInteger(n);
    }


    private int breakInteger(int n){
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
                res = max3(res, i * (n - i), i * breakInteger(n - i));
            }
            memo[n]=res;
        }
        return res;
    }
    private int max3(int a,int b,int c){
        return Math.max(a,Math.max(b,c));
    }

    public int integerBreak(int n) {
        assert n>=2;
        memo=new int[n+1];
        return breakInteger2(n);

    }

    private int breakInteger(int n){
        memo[1]=1;
        for (int i = 2; i <=n ; i++) {
            for (int j = 1; j <=i ; j++) {
                //将i分割成j+(i-j)
                memo[i]= max3(memo[i],j*(i-j),j*memo[i-j]);
            }
        }
        return memo[n];
    }
```
## 279.完全平方数

### 描述

给定正整数 *n*，找到若干个完全平方数（比如 `1, 4, 9, 16, ...`）使得它们的和等于 *n*。你需要让组成和的完全平方数的个数最少。

**示例 1:**

```
输入: n = 12
输出: 3 
解释: 12 = 4 + 4 + 4.
```

**示例 2:**

```
输入: n = 13
输出: 2
解释: 13 = 4 + 9.
```

### 分析

```
定义一个函数f(n)表示我们要求的解。f(n)的求解过程为：
f(n) = 1 + min{
  f(n-1^2), f(n-2^2), f(n-3^2), f(n-4^2), ... , f(n-k^2) //(k为满足k^2<=n的最大的k)
}
```

### 实现

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
## 91.解码方法
### 描述

一条包含字母 `A-Z` 的消息通过以下方式进行了编码：

```
'A' -> 1
'B' -> 2
...
'Z' -> 26
```

给定一个只包含数字的**非空**字符串，请计算解码方法的总数。

**示例 1:**

```
输入: "12"
输出: 2
解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
```

**示例 2:**

```
输入: "226"
输出: 3
解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
```

### 分析

思路()：

1. s[i]是编码、s[i-1]是编码、s[i-1:i+1]是编码。nums[i] = nums[i-1] + nums[i-2]
2. s[i]是编码、s[i-1]不是编码。nums[i] = nums[i-1]
3. s[i]不是编码、s[i-1]是编码、s[i-1:i+1]是编码。nums[i] = nums[i-2]
4. s[i]不是编码、s[i-1]是编码、s[i-1:i+1]不是编码。nums[i] = 0
5. 都不是编码。nums[i] = 0

### 实现

```java
public int numDecodings(String s) {
        int n = s.length();
        if (n==0){
            return 0;
        }
        //memo[i]表示字符串s中下标为(i-1)的解码方法次数
        int[] memo=new int[n+1];
        memo[0]=1;
        if (s.charAt(0)>'0'){
            memo[1]=1;
        }
        char[] chars = s.toCharArray();
        for (int i = 2; i <= n; i++) {
            int one = chars[i-1]-'0';
            //如果一个字符满足(1-9)则记录
            if (one>0){
                memo[i]+=memo[i-1];
            }
            int two = 10*(chars[i-2]-'0')+(chars[i-1]-'0');
            //如果两个字符满足(10-26)则记录
            if (two <= 26 && two >= 10) {
                    memo[i] += memo[i - 2];
            }

        }
        return memo[n];
    }
```





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
相关问题：
* [674.最长连续递增子序列](#674)
* [300.最长上升子序列](#300)
* [376.摆动序列](#376)
* [354.俄罗斯套娃信封问题](#354)
### 674
给定一个未经排序的整数数组，找到最长且**连续**的的递增序列。

示例 1:

输入: [1,3,5,4,7]
输出: 3

解释: 最长连续递增序列是 [1,3,5], 长度为3。
尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。 

示例 2:

输入: [2,2,2,2,2]
输出: 1

解释: 最长连续递增序列是 [2], 长度为1。
注意：数组长度不会超过10000。
- 实现：
```java
public int findLengthOfLCIS(int[] nums){
        if (nums==null||nums.length==0||nums.length==1){
            return nums.length;
        }
        int count=1;
        int max=0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i]>nums[i-1]){
                count++;
            }else {
                count=1;
            }
            max=Math.max(max,count);
        }
        return max;

    }
```
### 300
给定一个无序的整数数组，找到其中最长**上升子序列**的长度。

示例:

输入: [10,9,2,5,3,7,101,18]
输出: 4 

解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。

说明:

可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可，**子序列不要求连续。**

你算法的时间复杂度应该为 O(n2) 。

进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?

- 实现：

DP解法：
```java
public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n==0){
            return 0;
        }
        //memo[i]:表示数组nums中以下标为i的元素为结尾的上升序列的长度
        int[] memo=new int[n];
        Arrays.fill(memo,1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j]<nums[i]){
                    memo[i]=Math.max(memo[i],memo[j]+1);
                }
            }
        }
        int max=1;
        for (int i = 0; i < n; i++) {
            max=Math.max(max,memo[i]);
        }
        return max;
    }
```
二分查找解法：
```java
public int lengthOfLIS1(int[] nums){
        int maxLen=0;
        //存储着所有长度为i+1的递增子序列中, 最小的那个序列尾数
        //memo[]必然为递增数组
        int[] memo=new int[nums.length];
        for (int num:nums){
            int l=0,h=maxLen;
            while (l<h){
                int mid=l+(h-l)/2;
                if (memo[mid]<num) l=mid+1;
                else h=mid;
            }
            //将对应的数字放入相应的位置上
            memo[l]=num;
            //表示num比所有已知递增序列的尾数都大，则数组拓展的时候
            if (l==maxLen){
                maxLen++;
            }
        }
        return maxLen;
    }
```
### 354
给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，

 这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。

 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。

 说明:
 不允许旋转信封。

 示例:

 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]

 输出: 3

 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。

 - 实现：

 DP解法（效率蛮低的）：
 ```java
public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        if (n==0){
            return 0;
        }
        int[] memo=new int[n];
        Arrays.fill(memo,1);
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0]!=o2[0]){
                    return o1[0]-o2[0];
                }else {
                    return o1[1]-o2[1];
                }
            }
        });
        int max=1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0]>envelopes[j][0]&&envelopes[i][1]>envelopes[j][1]){
                    memo[i]=Math.max(memo[i],memo[j]+1);
                }
                max=Math.max(memo[i],max);
            }
        }
        return max;
    }
 ```
二分查找解法：
```java
public int maxEnvelopes1(int[][] envelopes){
        int n = envelopes.length;
        if (n==0){
            return 0;
        }
        int len=0;
        int[] memo=new int[n];
        //先按照width升序排列，如果width相等就按照height降序排列
        //这样可以保证依次遍历数组的时候,
        // 后面的width始终比前面的大并且如果高度也大于前面的就一定可以包含前面的
        //这样就转化成了一位数组的LIS问题
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0]==o2[0]){
                    return o2[1]-o1[1];
                }
                return o1[0]-o2[0];
            }
        });
        for (int[] envelope:envelopes){
            int l=0,h=len-1;
            while (l<=h){
                int mid=l+(h-l)/2;
                if (envelope[1]>memo[mid])  l=mid+1;
                else h=mid-1;
            }
            memo[l]=envelope[1];
            if (l==len){
                len++;
            }
        }
        return len;
    }
```
### 376
如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。

例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。

给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。

示例 1:

输入: [1,7,4,9,2,5]
输出: 6 
解释: 整个序列均为摆动序列。

示例 2:

输入: [1,17,5,10,13,15,10,5,16,8]
输出: 7
解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。

示例 3:

输入: [1,2,3,4,5,6,7,8,9]
输出: 2

进阶:
你能否用 O(n) 时间复杂度完成此题?

- 实现：
```java
public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n==0||n==1){
            return n;
        }
        int[] up=new int[n];
        int[] down=new int[n];
        up[0]=1;
        down[0]=1;
        for (int i = 1; i < n; i++) {
            if (nums[i]>nums[i-1]){
                //上升的时候
                up[i]=down[i-1]+1;
                down[i]=down[i-1];
            }else if (nums[i]<nums[i-1]){
                //下降的时候
                down[i]=up[i-1]+1;
                up[i]=up[i-1];
            }else {
                down[i]=down[i-1];
                up[i]=up[i-1];
            }

        }
        return Math.max(up[n-1],down[n-1]);
    }
```

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
               //持有股票
               int hold=Integer.MIN_VALUE;
               //抛售股票
               int unhold=0;
               for (int i = 0; i < n; i++) {
                   hold=Math.max(hold,-prices[i]);
                   unhold=Math.max(unhold,hold+prices[i]);
               }
               return unhold;
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
* [120.三角形最小路径和](#120)
* [221.最大的正方形](#221)
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
### 120
给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

例如，给定三角形：

```java
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
```
自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

说明：

如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分

- 实现：
```java
/**
     * 状态转移方程为：
     * f(0,0)=t[0][0]
     *
     * f(i,0)=t[i][0]+f(i-1,0)
     *
     * f(i,i)=t[i][i]+f(i-1,i-1)
     *
     * f(i,j)=t[i][j]+min{f(i-1,j-1),f(i-1,j)}
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        if (n==0){
            return 0;
        }
        for (int i = 1; i < n; i++) {
            triangle.get(i).set(0,triangle.get(i-1).get(0)+triangle.get(i).get(0));
            triangle.get(i).set(i,triangle.get(i).get(i)+triangle.get(i-1).get(i-1));
            for (int j = 1; j <i ; j++) {
                triangle.get(i).set(j,triangle.get(i).get(j)+Math.min(triangle.get(i-1).get(j-1),triangle.get(i-1).get(j)));
            }
        }
        Collections.sort(triangle.get(n-1));
        return triangle.get(n-1).get(0);
    }

    public int minimumTotal1(List<List<Integer>> triangle){
        int n = triangle.size();
        if (n==0){
            return 0;
        }
        int[] memo=new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (j==0){
                    memo[j]+=triangle.get(i).get(0);
                }else if (i==j){
                    memo[j]=triangle.get(i).get(i)+memo[i-1];
                }else {
                    memo[j]=triangle.get(i).get(j)+Math.min(memo[j],memo[j-1]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(memo[i]+" ");
        }
        int min=memo[0];
        for (int i = 0; i < n; i++) {
            min=Math.min(min,memo[i]);
        }
        return min;
        
    }
```
### 221
在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。

示例:

输入: 

```java
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
```
输出: 4

- 实现：
```java
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        if (m==0){
            return 0;
        }
        int n = matrix[0].length;
        //记录当前位置最大正方形的边长
        int[][] memo = new int[m][n];
        //最大正方形的边长
        int res=0;
        //初始化上边缘
        for (int i = 0; i < m; i++) {
            if (matrix[i][0]=='1'){
                memo[i][0]=1;
                res=1;
            }
        }
        //初始化左边缘
        for (int i = 0; i < n; i++) {
            if (matrix[0][i]=='1'){
                memo[0][i]=1;
                res=1;
            }

        }
        //状态转移方程：F（i,j）=Min{F(i,j-1),F(i-1,j),F(i-1,j-1)}+1
        //如果当前位置为1，那么当前位置的最大正方形的边长最多比它的上方，左方和左上方的位置多1
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j]=='1'){
                    memo[i][j]=min3(memo[i-1][j],memo[i][j-1],memo[i-1][j-1])+1;
                    //记录最大的边长
                    res=Math.max(res,memo[i][j]);
                }
                //else --> matrix[0][i]!='1' { memo[i][j]=0 }
            }
        }
        return res*res;
    }
    //从三个数中获得最小值
    private int min3(int a,int b,int c){
        return Math.min(a,Math.min(b,c));
    }
```
# 参考资料

[玩儿转算法面试 - 课程官方代码仓](https://github.com/liuyubobobo/Play-with-Algorithm-Interview)
