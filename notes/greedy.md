<!-- GFM-TOC -->
* [贪心算法部分笔记总结](#贪心算法部分笔记总结)
    * [贪心基础](#贪心基础)
    * [贪心算法与动态规划的关系](#贪心算法与动态规划的关系)
    * [参考资料](#参考资料)
<!-- GFM-TOC -->
# 贪心算法部分笔记总结
## 贪心基础
相关题目：
* [分发饼干](#455)
* [判断子序列](#392)
* [买卖股票的最佳时机（2）](#122)
### 455
假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。对每个孩子 i ，都有一个胃口值 gi ，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j ，都有一个尺寸 sj 。如果 sj >= gi ，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。

注意：

你可以假设胃口值为正。
一个小朋友最多只能拥有一块饼干。

示例 1:

输入: [1,2,3], [1,1]

输出: 1

解释: 
你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
所以你应该输出1。
示例 2:

输入: [1,2], [1,2,3]

输出: 2

解释: 
你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
你拥有的饼干数量和尺寸都足以让所有孩子满足。
所以你应该输出2.
- 分析：
贪心策略：尽可能大地满足孩子的贪心值。
- 实现：
````java
public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int gi=g.length-1;
        int si=s.length-1;
        int count=0;
        //从大到小遍历
        while (gi>=0&&si>=0){
            if (s[si]>=g[gi]){
                count++;
                gi--;
                si--;
            }else {
                gi--;
            }
        }
        return count;
    }
````
### 392
给定字符串 s 和 t ，判断 s 是否为 t 的子序列。

你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。

字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。

示例 1:
s = "abc", t = "ahbgdc"

返回 true.

示例 2:
s = "axc", t = "ahbgdc"

返回 false.

后续挑战 :

如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？

- 分析：

只需要在t中搜索即可，贪心策略体现在每次只比较第一个匹配的字符，同时设置一个计数器来记录t中匹配的字符数，
如果计数器等于s的长度则成功；如果循环结束则表示没有全部匹配到t中的字符，则失败。
- 实现：
```java
public boolean isSubsequence(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen==0){
            return true;
        }
        int sCount=0;
        for (int i = 0; i < tLen; i++) {
            if (s.charAt(j)==t.charAt(i)){
                sCount++;
            }
            if (sCount==sLen){
                return true;
            }
        }
        return false;
    }
```
### 122
给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。

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
- 分析：

由于不限制交易次数，那么可以考虑使用贪心算法；
贪心策略：体现在每当股票价格上升的时候就尽可能进行交易

- 实现：
```java
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

## 贪心算法与动态规划的关系
相关题目：
* [重叠区间](#455)
### 435
给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。

注意:

可以认为区间的终点总是大于它的起点。
区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
示例 1:

输入: [ [1,2], [2,3], [3,4], [1,3] ]

输出: 1

解释: 移除 [1,3] 后，剩下的区间没有重叠。
示例 2:

输入: [ [1,2], [1,2], [1,2] ]

输出: 2

解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
示例 3:

输入: [ [1,2], [2,3] ]

输出: 0

解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
- 分析：
使用贪心算法，首先将区间按照结尾做升序排列；由于题目中要求求出需要移除区间的最小数量，使剩余区间互不重叠，等价于求出总区间数减去剩余区间互不重叠的最大区间数
（min=n-max）

贪心策略：尽量选择结尾靠前的区间，每次选择结尾最早的，并且和前一个区间没有重叠的；在遍历过程中记录区间数，遍历结束最后得到的就是不重叠的最大区间数(res)，用总区间数减去res即可。
- 实现：
```java
public class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
  }
  private class  myComparator implements Comparator<Interval>{
      @Override
      public int compare(Interval o1, Interval o2) {
          if (o1.end!=o2.end){
              return o1.end-o2.end;
          }else {
              return o1.start-o2.start;
          }
      }
  }

    //使得区间按照结尾从小到大排列
    //贪心策略：尽量选择结尾靠前的区间，每次选择结尾最早的，并且和前一个区间没有重叠的
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length==0){
            return 0;
        }
        Arrays.sort(intervals,new  myComparator() );
        int res=1;
        int pre=0;  //遍历过程中之前使用的区间索引
        for (int i = 1; i < intervals.length; i++) {
            //如果区间没有重叠的时候
            if (intervals[i].start>=intervals[pre].end){
                res++;
                pre=i;  //记录
            }
        }
        return intervals.length-res;
    }
```
## 更多贪心算法的题目
相关题目：
* [860.柠檬水找零](#860)
* [861.翻转矩阵后的得分](#861)

### 860

在柠檬水摊上，每一杯柠檬水的售价为 5 美元。

顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。

每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。

注意，一开始你手头没有任何零钱。

如果你能给每位顾客正确找零，返回 true ，否则返回 false 。

示例 1：

输入：[5,5,5,10,20]
输出：true
解释：
前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。

第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。

第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。

由于所有客户都得到了正确的找零，所以我们输出 true。

示例 2：

输入：[5,5,10]
输出：true

示例 3：

输入：[10,10]
输出：false

示例 4：

输入：[5,5,10,10,20]
输出：false
解释：
前 2 位顾客那里，我们按顺序收取 2 张 5 美元的钞票。

对于接下来的 2 位顾客，我们收取一张 10 美元的钞票，然后返还 5 美元。

对于最后一位顾客，我们无法退回 15 美元，因为我们现在只有两张 10 美元的钞票。

由于不是每位顾客都得到了正确的找零，所以答案是 false。
 

提示：

0 <= bills.length <= 10000

bills[i] 不是 5 就是 10 或是 20 

- 分析：

贪心策略：尽量使用少的零钱去找零，所以每次找零的时候，尽量选择面额大的零钱来进行找零。

- 实现：
```java
public boolean lemonadeChange(int[] bills) {
        int five=0,ten=0;
        int n = bills.length;
        if (n==0){
            return true;
        }
        for (int i = 0; i < n; i++) {
            if (bills[i]==5){
                five++;
            }else if (bills[i]==10){
                if (five>0){
                    five--;
                    ten++;
                }else {
                    return false;
                }
            }else {
                //bills[i]==20
                if (ten>0){
                    if (five>0){
                        ten--;
                        five--;
                    }else {
                        return false;
                    }
                }else {
                    if (five>=3){
                        five-=3;
                    }else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
```
### 861
有一个二维矩阵 A 其中每个元素的值为 0 或 1 。

移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。

在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。

返回尽可能高的分数。

 

示例：

输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]

输出：39

解释：

转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 

提示：

1 <= A.length <= 20

1 <= A[0].length <= 20

A[i][j] 是 0 或 1

- 分析：

这一题如果不使用贪心算法的话，求解会比较麻烦，因为穷举是指数级别的。

贪心策略：**尽可能将高位填充1**，将每一行看做是一个二进制数，分数是这些数字之和，那么高位中有尽可能多
的1就能确保加和会尽量地大。

- 实现：
```java
public int matrixScore(int[][] A) {
        int row = A.length;
        if (row==0){
            return 0;
        }
        int col = A[0].length;
        for (int i = 0; i < row; i++) {
            if (A[i][0]==0){
                reverseCol(A,i);
            }
        }
        for (int i = 1; i < col; i++) {
            int zero=colZeros(A,i);
            if (zero>row/2){
                reverseRow(A,i);
            }
        }
        int res=scores(A);
        return res;
    }

    //按列来进行 行翻转
    private void reverseRow(int[][] a,int col){
        for (int i = 0; i < a.length; i++) {
            if (a[i][col]==1){
                a[i][col]=0;
            }else {
                a[i][col]=1;
            }
        }
    }

    //按行来进行列翻转
    private void reverseCol(int[][] a,int row){
        for (int i = 0; i < a[0].length; i++) {
            if (a[row][i]==1){
                a[row][i]=0;
            }else {
                a[row][i]=1;
            }
        }
    }

    //统计一列中
    private int colZeros(int[][] a,int col){
        int zero=0;
        for (int i = 0; i < a.length; i++) {
            if (a[i][col]==0){
                zero++;
            }
        }
        return zero;
    }

    //计算分数
    private int scores(int[][] a){
        int res=0;
        for (int i = 0; i < a.length; i++) {
            int rowNum=0;
            int col = a[0].length-1;
            for (int j = 0; j <= col; j++) {
                int bit = a[i][j];
                rowNum+=bit<<(col-j);
            }
            res+=rowNum;
        }
        return res;
    }
```


# 参考资料

[玩儿转算法面试 - 课程官方代码仓](https://github.com/liuyubobobo/Play-with-Algorithm-Interview)
