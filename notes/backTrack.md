<!-- GFM-TOC -->
* [递归和回溯法的笔记总结](#递归和回溯法的笔记总结)
    * [树形问题](#树形问题)
    * [排列问题](#排列问题)
    * [组合问题](#组合问题)
    * [回溯法解决组合问题的优化](#回溯法解决组合问题的优化)  
    * [二维平面上的回溯法](#二维平面上的回溯法)
    * [floodfill算法](#floodfill算法)
    * [回溯法是人工智能的基础](#回溯法是人工智能的基础)
    * [更多回溯算法](#更多回溯算法)  
* [参考资料](#参考资料)
<!-- GFM-TOC -->

# 树形问题
相关题目：
<!-- GFM-TOC -->
* [17.电话号码的字母组合](#17)  
<!-- GFM-TOC -->
## 17.电话号码的字母组合

### 描述

给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

![](../pict/s17.png)

示例:

输入："23"
输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
说明:
尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
### 分析

例如“23”的映射过程如下图所示，第二层表示“2”可以映射的字母，第三层表示“3”可以映射的字母。本题的字符串的映射过程就是图中这样的树形结构，可以使用DFS（深度遍历）来求解该问题。

<div align="center">
    <img src="https://gitee.com/IvanLu1024/picts/raw/4cc0ec7eb7e1c4cfff1380a167679029280727b9/blog/leetcode/backtrack/17.png"/>
</div>



### 实现

```java
private String letterMap[] = {
            " ",    //0
            "",     //1
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
    };

    private List<String> res;

	//即DFS
    private void findCombination(String digits, int index, String s){
        System.out.println(index+":"+s);
        if (digits.length()==index){
            res.add(s);
            return;
        }

        char c = digits.charAt(index);
        String letters = letterMap[c - '0'];
        for (int i = 0; i < letters.length(); i++) {
            System.out.println("digits["+index+"] ="+c+" used "+letters.charAt(i));
            findCombination(digits,index+1,s+letters.charAt(i));
        }
    }
    public List<String> letterCombinations(String digits) {
        res=new ArrayList<>();
        if (digits.equals("")){
            return res;
        }
        findCombination(digits,0,"");
        return res;
    }
```
# 排列问题
<!-- GFM-TOC -->
相关题目：

* [46.全排列](#46)  
* [47.全排列（2）](#47) 
<!-- GFM-TOC -->
## 46.全排列

### 描述

给定一个没有重复数字的序列，返回其所有可能的全排列。

示例:

输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
### 分析

该题可以使用回溯法来求解，举例说明，[1,2,3]，其生成过程如下图所示：

<div align="center">
    <img src="https://gitee.com/IvanLu1024/picts/raw/12a35b4a5dbccaa1708b7bf78d026c9166959e3f/blog/leetcode/backtrack/46.png"/>
</div>

由于排列的性质导致**已经选取过的元素在同一次排列中不能被再次选取**，所以在代码实现的时候需要注意使用一个数组来记录各元素的状态。回溯法体现在**元素是否加入待排列的集合中**。

### 实现

```java
//其中添加了输入可以更加明显的观察处理的过程
private List<List<Integer>> res=new ArrayList<>();
    //用于记录该数字是否被使用过
    private boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        if (nums==null||nums.length==0){
            return res;
        }
        //boolean数组默认值为false
        used=new boolean[nums.length];
        List<Integer> p=new ArrayList<>();
        generatePermute(nums,0,p);
        return res;
    }

    //p中保存了index个元素的排列
    //向这个排序的尾部放入一个第index+1个元素，获得一个index+1的元素排列
    private void generatePermute(int[] nums,int index,List<Integer> p){
        //递归终止条件
        if (index==nums.length){
            System.out.println("permute completed : "+p);
            res.add(new ArrayList<>(p));
            return;
        }
        //每次遍历都从头开始选择
        for (int i = 0; i <nums.length ; i++) {
            if (!used[i]){
                //将nums[i]添加到p中
                p.add(nums[i]);
                used[i]=true;
                System.out.println(nums[i]+" -> p: "+p);
                generatePermute(nums,index+1,p);
                //回溯
                p.remove(p.size()-1);
                used[i]=false;
                System.out.println("backTracking ... current P:"+p);
            }
        }
    }
```
输出结果：
````
1 -> p: [1]
2 -> p: [1, 2]
3 -> p: [1, 2, 3]
permute completed : [1, 2, 3]
backTracking ... current P:[1, 2]
backTracking ... current P:[1]
3 -> p: [1, 3]
2 -> p: [1, 3, 2]
permute completed : [1, 3, 2]
backTracking ... current P:[1, 3]
backTracking ... current P:[1]
backTracking ... current P:[]
2 -> p: [2]
1 -> p: [2, 1]
3 -> p: [2, 1, 3]
permute completed : [2, 1, 3]
backTracking ... current P:[2, 1]
backTracking ... current P:[2]
3 -> p: [2, 3]
1 -> p: [2, 3, 1]
permute completed : [2, 3, 1]
backTracking ... current P:[2, 3]
backTracking ... current P:[2]
backTracking ... current P:[]
3 -> p: [3]
1 -> p: [3, 1]
2 -> p: [3, 1, 2]
permute completed : [3, 1, 2]
backTracking ... current P:[3, 1]
backTracking ... current P:[3]
2 -> p: [3, 2]
1 -> p: [3, 2, 1]
permute completed : [3, 2, 1]
backTracking ... current P:[3, 2]
backTracking ... current P:[3]
backTracking ... current P:[]
[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
````
## 47.全排列（2）

### 描述

给定一个可包含重复数字的序列，返回所有不重复的全排列。

示例:

输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
### 分析

和46题思路的类似的，关键在于如何处理重复的数字，在每次遍历的时候多了一步去重操作。

### 实现

```java
private List<List<Integer>> res=new ArrayList<>();
    private boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums==null||nums.length==0){
            return res;
        }
        //首先将数组排序一下
        Arrays.sort(nums);
        List<Integer> p=new ArrayList<>();
        used=new boolean[nums.length];
        generatePermuteUnique(nums,0,p);
        return res;
    }

    private void generatePermuteUnique(int[] nums, int index, List<Integer> p) {
        if (index==nums.length){
            System.out.println("permute completed : "+p);
            res.add(new ArrayList<>(p));
        }
        for (int i = 0; i < nums.length; i++) {
            //去重操作
            if(i>0 && ( nums[i]==nums[i-1]) && !used[i-1]){
                //避免重复值,由于数组已经排序过了，所以只需要比较相邻的元素
                System.out.println("skip ... ");
                continue;
            }
            if(used[i]){//避免重复值
                System.out.println("skip ... ");
                continue;
            }
            p.add(nums[i]);
            used[i]=true;
            System.out.println(nums[i]+" -> p: "+p);
            generatePermuteUnique(nums,index+1,p);
            p.remove(p.size()-1);
            used[i]=false;
            System.out.println("backTracking ... current P:"+p);
        }

    }
```
# 组合问题
相关题目：
<!-- GFM-TOC -->
* [77.组合](#77)  
<!-- GFM-TOC -->
## 77.组合

### 描述

给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

示例:

输入: n = 4, k = 2
输出:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
### 分析

由于组合的性质是不考虑元素的次序关系，那么对于[1,2]和[2,1]是同一个组合。所以在每次搜索的时候，不能从头开始，只能从上一次搜索的下一个位置继续搜索。此时搜索范围是可以继续优化的，因为组合中元素的总数为k。那么，每次搜索的时候，**在[i...n]中保证至少要有(k-c.size())个元素即可**，那么n-i+1>=k-size。

### 实现

```java
private List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        if (n<=0||n<k||k<=0){
            return res;
        }
        List<Integer> c=new ArrayList<>();
        generateCombine(n,k,1,c);
        return res;

    }

    private void generateCombine(int n, int k, int start, List<Integer> c) {
        if (c.size()==k){
            System.out.println("Completed "+c);
            res.add(new ArrayList<>(c));
            return;
        }
        //此时有(k-c.size())个空位需要填充，那么[i...n]中至少要有(k-c.size())个元素
        //则n-i>=(k-c.size())-1确保[i...n]中至少要有(k-c.size())个元素
        // --> i<=n-(k-c.size())+1
        for (int i = start; i <=n-(k-c.size())+1; i++) {
            c.add(i);
            System.out.println(i+"-> c:"+c);
            generateCombine(n,k,i+1,c);
            c.remove(c.size()-1);
            System.out.println("backTracking..."+c);
        }
    }
```
相关题目：
<!-- GFM-TOC -->

* [39.组合总和](#39)  
* [40.组合总和（2）](#40) 
* [216.组合总和（3）](#216) 
* [78.子集](#78)  
* [90.子集（2）](#90) 
* [401.二进制手表](#401) 
<!-- GFM-TOC -->
## 39.组合总和

### 描述

给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

**candidates 中的数字可以无限制重复被选取**。

说明：

所有数字（包括 target）都是正整数。
**解集不能包含重复的组合**。 

示例 1:

输入: candidates = [2,3,6,7], target = 7,
所求解集为:
[
  [7],
  [2,2,3]
]
示例 2:

输入: candidates = [2,3,5], target = 8,
所求解集为:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
### 分析

标准的回溯法求解过程。

### 实现

```java
private List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates==null||candidates.length==0){
            return res;
        }
        generateCS(candidates,target,0,new ArrayList<>());
        return res;

    }
    private void generateCS(int[] c,int target,int start, List<Integer> list){
        if (target==0){
            System.out.println("completed :"+list);
            res.add(new ArrayList<>(list));
        }
        for (int i = start; i < c.length; i++) {
            //剪枝，由于待搜索的数组都是正整数，则当前搜索位置元素大于目标则直接跳过
           if (c[i]<=target){
               list.add(c[i]);
               System.out.println("current :"+c[i]+" target:"+target);
               //由于candidates 中的数字可以无限制重复被选取，所以每次开始位置为当前位置
               generateCS(c,target-c[i],i,list);
               System.out.println("backTracking..."+list);
               list.remove(list.size()-1);
           }else {
               System.out.println("step over");
           }
        }
    }
```
## 40.组合总和（2）

### 描述

给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

**candidates 中的每个数字在每个组合中只能使用一次**。

说明：

所有数字（包括目标数）都是正整数。
**解集不能包含重复的组合**。 

示例 1:

输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
示例 2:

输入: candidates = [2,5,2,1,2], target = 5,
所求解集为:
[
  [1,2,2],
  [5]
]
### 分析



### 实现

```java
private List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates==null||candidates.length==0){
            return res;
        }
        Arrays.sort(candidates);
        generateCS(candidates,target,0,new ArrayList<>());
        return res;

    }
    private void generateCS(int[] c,int target,int start, List<Integer> list){
        if (target==0){
            System.out.println("completed :"+list);
            res.add(new ArrayList<>(list));
        }
        for (int i = start; i < c.length; i++) {
            //保证解集中的组合是不重复的
            if (i>start&&c[i]==c[i-1]){
                System.out.println("repeat...");
                continue;
            }
            //剪枝，由于待搜索的数组都是正整数，则当前搜索位置元素大于目标则直接跳过
           if (c[i]<=target){
               list.add(c[i]);
               System.out.println("current :"+c[i]+"->"+list+" target:"+target);
               //由于candidates 中的数字只能被使用一次，所以下次搜索从下一个位置开始
               generateCS(c,target-c[i],i+1,list);
               System.out.println("backTracking..."+list);
               list.remove(list.size()-1);
           }else {
               System.out.println("step over");
           }
        }
    }
```
## 216.组合总和（3）

### 描述

找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且**每种组合中不存在重复的数字**。

说明：

所有数字都是正整数。

**解集不能包含重复的组合**。 

示例 1:

输入: k = 3, n = 7
输出: [[1,2,4]]
示例 2:

输入: k = 3, n = 9
输出: [[1,2,6], [1,3,5], [2,3,4]]
### 分析

典型的回溯法，注意题中的一些细节的把握。

### 实现

```java
private List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        if (k==0||n==0){
            return res;
        }
        generate(k,n,0,1,new ArrayList<>());
        return res;

    }
    private void generate(int k,int target,int index,int start,List<Integer> list){
        if (index==k&&target==0){
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= 9; i++) {
            //剪枝操作
            if (i<=target){
                list.add(i);
                //由于每个组合中不存在重复的数字，那么下一次搜索从i+1开始
                generate(k,target-i,index+1,i+1,list);
                list.remove(list.size()-1);
            }
        }
    }
```
# 回溯法

## 78.子集

给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。

示例:

输入: nums = [1,2,3]
输出:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

- 分析：
- 实现：

```java
private List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        if (nums==null||nums.length==0){
            return res;
        }
        generate(nums,0,new ArrayList<>());
        return res;

    }
    private void generate(int[] nums,int index,List<Integer> list){
        //当满足条件就加入结果的集合中，确保子集的长度不超过原始集合
        if (index<=nums.length){
            System.out.println(list+"-> res");
            res.add(new ArrayList<>(list));
        }
        for (int i = index; i <nums.length ; i++) {
            list.add(nums[i]);
            System.out.println("current index:"+i+" "+nums[i]+"->"+list);
            generate(nums,i+1,list);
            list.remove(list.size()-1);
            System.out.println("backTracking..."+list);
        }
        return;
    }
```

## 90.子集（2）

给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。

示例:

输入: [1,2,2]
输出:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

- 分析：
- 实现：

```java
private List<List<Integer>> res=new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums==null||nums.length==0){
            return res;
        }
        Arrays.sort(nums);
        generate(nums,0,new ArrayList<>());
        return res;
    }

    private void generate(int[] nums,int index,List<Integer> list){
        //当满足条件就加入结果的集合中，确保子集的长度不超过原始集合
        if (index<=nums.length){
            res.add(new ArrayList<>(list));
        }
        for (int i = index; i <nums.length ; i++) {
            //去重
            if (i>index&&nums[i]==nums[i-1]){
                continue;
            }
            list.add(nums[i]);
            generate(nums,i+1,list);
            list.remove(list.size()-1);
        }
        return;
    }
```

### 401

二进制手表顶部有 4 个 LED 代表小时（0-11），底部的 6 个 LED 代表分钟（0-59）。

每个 LED 代表一个 0 或 1，最低位在右侧。

例如，上面的二进制手表读取 “3:25”。

给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。

案例:

输入: n = 1
返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]

注意事项:

输出的顺序没有要求。
小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。

- 分析：
- 实现：

解法一（迭代解法）：

```java
private List<String> res=new ArrayList<>();
    public List<String> readBinaryWatch(int num) {
        String time;
        for (int i = 0; i <= num; i++) {
            List<Integer> hours = binCount(11, i);
            List<Integer> mins = binCount(59, num - i);
            for (int j = 0; j < hours.size(); j++) {
                String hour = String.valueOf(hours.get(j));
                for (int k = 0; k < mins.size(); k++) {
                    String min=mins.get(k)<=9?"0"+mins.get(k):String.valueOf(mins.get(k));
                    time=hour+":"+min;
                    res.add(time);
                }
            }
        }
        return res;

    }
    /**
     * 获得0-n范围内二进制中1的个数为count的所有数字
     * @param n
     * @param count
     * @return
     */
    private List<Integer> binCount(int n,int count){
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            if (Integer.bitCount(i)==count){
                list.add(i);
            }
        }
        return list;
    }
```

解法二（回溯法）：

```java
private List<String> res=new ArrayList<>();
public List<String> readBinaryWatch(int num){
        int[] time={1, 2, 4, 8, 1, 2, 4, 8, 16, 32};
        generateTime(num,time,0,0,0,0);
        return res;
    }

    private void generateTime(int num,int[] time,int index,int start,int hour,int min){
        if (index==num){
            String hs = String.valueOf(hour);
            String ms = min <= 9 ? "0" + min : String.valueOf(min);
            String t=hs+":"+ms;
            res.add(t);
            return;
        }
        for (int i = start; i <time.length ; i++) {
            int newHour = i<4?hour+time[i]:hour;//前四位表示小时
            int newMin = i<4?min:min+time[i];//后六位表示分钟
            if (newHour<=11&&newMin<=59){
                generateTime(num,time,index+1,i+1,newHour,newMin);
            }
            
        }
    }
```



相关题目：
<!-- GFM-TOC -->

- [51.N皇后](#51)  
- [52.N皇后（2）](#52) 
- [37.解数独](#37) 
  <!-- GFM-TOC -->

### 51

n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。

每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

示例:

输入: 4

输出: 

```java
    [
       [".Q..",  // 解法 1
        "...Q",
        "Q...",
        "..Q."],
      
       ["..Q.",  // 解法 2
        "Q...",
        "...Q",
        ".Q.."]
      ]
解释: 4 皇后问题存在两个不同的解法。
```

- 分析：
- 实现：

```java
private List<List<String>> res=new ArrayList<>();
    private boolean[] col,diag1,diag2;

    public List<List<String>> solveNQueens(int n) {
        if (n==0){
            return res;
        }
        col=new boolean[n];
        diag1 = new boolean[2 * n - 1];
        diag2 = new boolean[2 * n - 1];
        List<Integer> row=new LinkedList<>();
        putQueue(n,0,row);
        return res;


    }

    // 尝试在一个n皇后问题中, 摆放第index行的皇后位置
    private void putQueue(int n,int index,List<Integer> row){
        if (index==n){
            res.add(new ArrayList<>(generateBoard(n,row)));
        }
        for (int i = 0; i < n; i++) {
            // 尝试将第index行的皇后摆放在第i列
            if (!col[i]&&!diag1[index+i]&&!diag2[index-i+n-1]){
                col[i]=true;
                diag1[index+i]=true;
                diag2[index-i+n-1]=true;
                row.add(i);
                putQueue(n,index+1,row);
                col[i]=false;
                diag1[index+i]=false;
                diag2[index-i+n-1]=false;
                row.remove(row.size()-1);
            }
        }
    }

    private List<String> generateBoard(int n, List<Integer> row) {

        assert row.size()==n;

        List<String> board=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] chars = new char[n];
            Arrays.fill(chars,'.');
            chars[row.get(i)]='Q';
            board.add(new String(chars));
        }
        return board;
    }
```

### 52

n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

给定一个整数 n，返回 n 皇后不同的解决方案的数量。

示例:

输入: 4

输出: 2

解释: 4 皇后问题存在如下两个不同的解法。

```java
[
   [".Q..",  // 解法 1
    "...Q",
    "Q...",
    "..Q."],
  
   ["..Q.",  // 解法 2
    "Q...",
    "...Q",
    ".Q.."]
  ]
```

- 分析：
- 实现：

```java
    private int res=0;
    private boolean[] col,diag1,diag2;

    public int totalNQueens(int n) {
        if (n==0){
            return res;
        }
        col=new boolean[n];
        diag1=new boolean[2*n-1];
        diag2=new boolean[2*n-1];
        putQueens(n,0);
        return res;
    }
    private void putQueens(int n,int index){
        if (index==n){
            res++;
        }
        for (int i = 0; i < n; i++) {
            if (!col[i]&&!diag1[i+index]&&!diag2[i-index+n-1]){
                col[i]=true;
                diag1[i+index]=true;
                diag2[i-index+n-1]=true;
                putQueens(n,index+1);
                col[i]=false;
                diag1[i+index]=false;
                diag2[i-index+n-1]=false;
            }
        }
    }
```

### 37

编写一个程序，通过已填充的空格来解决数独问题。

一个数独的解法需遵循如下规则：

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
空白格用 '.' 表示。

**Note:**

1. 给定的数独序列只包含数字 1-9 和字符 '.' 。
2. 你可以假设给定的数独只有唯一解。
3. 给定数独永远是 9x9 形式的。

- 分析：
- 实现：

```java
    private boolean[][] col;    //用于记录每一行中出现的数字（0-9）
    private boolean[][] row;    //用于记录每一列中出现的数字
    private boolean[][] block;  //用于记录每3*3的宫中出现的数字
    public void solveSudoku(char[][] board) {
        col=new boolean[9][10];
        row=new boolean[9][10];
        block=new boolean[9][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j]=='.'){
                    continue;
                }
                col[i][board[i][j]-'0']=true;
                row[j][board[i][j]-'0']=true;
                block[i/3*3+j/3][board[i][j]-'0']=true;
            }
        }

        for (int i = 0; i < 81; i++) {
            if (board[i/9][i%9]=='.'){
                if (dfs(board,i,col,row,block)){
                    continue;
                }
            }
        }

    }

    private boolean dfs(char[][] board,int index,boolean[][] col,boolean[][] row,boolean[][] block){
        if (index==81){
            return true;
        }
        int start=index+1;

        for (; start <81 ; start++) {
            if (board[start/9][start%9]=='.'){
                break;
            }
        }
        int x=index/9,y=index%9;
        for (int i = 1; i <= 9; i++) {
            if (!col[x][i]&&!row[y][i]&&!block[x/3*3+y/3][i]){
                col[x][i]=true;
                row[y][i]=true;
                block[x/3*3+y/3][i]=true;
                board[x][y]=(char)('0'+i);
                if (dfs(board,start,col,row,block)){
                    return true;
                }
                col[x][i]=false;
                row[y][i]=false;
                block[x/3*3+y/3][i]=false;
                board[x][y]='.';
            }
        }
        return false;
    }
```

- [22.括号生成](#22)
- [89.格雷编码](#89)
- [93.复原IP地址](#93)

### 22

给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。

例如，给出 n = 3，生成结果为：

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

实现：

```java
private List<String> res;
    //回溯法
    public List<String> generateParenthesis(int n) {
        res=new ArrayList<>();
        generate(n,n,"");
        return res;
    }
    //left：剩余的左括号的数量
    //right：剩余的右括号的数量
    private void generate(int left,int right,String s){
        if (left==0&&right==0){
            System.out.println("finished : "+s);
            res.add(s);
            return;
        }
        //填充左括号
        if (left>0){
            System.out.println("add left -> "+s);
            generate(left-1,right,s+"(");
        }
        //当前的左括号数量大于当前的右括号的数量的时候，填充右括号
        if (left<right){
            System.out.println("add right -> "+s);
            generate(left,right-1,s+")");
        }
    }
```

### 89

格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。

给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。

示例 1:

输入: 2

输出: [0,1,3,2]

解释:
00 - 0

01 - 1

11 - 3

10 - 2

对于给定的 n，其格雷编码序列并不唯一。

例如，[0,2,3,1] 也是一个有效的格雷编码序列。

00 - 0

10 - 2

11 - 3

01 - 1

示例 2:

输入: 0

输出: [0]

解释: 我们定义格雷编码序列必须以 0 开头。
     给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
     因此，当 n = 0 时，其格雷编码序列为 [0]。

- 实现：

```java
//公式法：
    //整数 n 的格雷码是 n ⊕ ( n/2)
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        if (n==0){
            res.add(0);
            return res;
        }
        for (int i = 0; i < 1<<n; i++) {
            int grayCode = i^(i/2);
            res.add(grayCode);
        }
        return res;
    }

    //回溯法
    public List<Integer> grayCode1(int n){
        List<Integer> res = new ArrayList<>();
        if (n==0){
            res.add(0);
            return res;
        }
        if (n==1){
            res.add(0);
            res.add(1);
            return res;
        }
        List<Integer> grayM = grayCode1(n - 1);
        for (int i = 0; i < 1 << n; i++) {
            if (i<1<<(n-1)){//前面一半的数字不变
                res.add(grayM.get(i));
            }else {//后面一半的数字反向排列，再在前面添加一个1
                int code = grayM.get((1<<n)-i-1)+(1<<(n-1));
                res.add(code);
            }
        }
        return res;
    }
```

### 93

给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。

示例:

输入: "25525511135"

输出: ["255.255.11.135", "255.255.111.35"]

- 实现：

```java
private List<String> res=new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        if (s.length() < 4 || s.length() > 12){
            return res;
        }
        generateIpAddress(s,0,"");
        return res;

    }
    //生成ip地址的函数，s：待解析的字符串，count：当前已经解析的数字次数（ip为4个0-255是数字组成）
    //ip:前一次搜索得到的待完成的ip
    private void generateIpAddress(String s,int count,String ip){
        //当已经解析了3位数字，判断当前的s是否满足作为最后一位数字的条件，若满足则加入结果集合
        if (count==3&&isValid(s)){
            System.out.println("complete ip ");
            res.add(ip+s);
        }
        //每次最多只取三位数
        for (int i = 1; i < Math.min(4,s.length()); i++) {
            String curStr = s.substring(0, i);
            if (isValid(curStr)){
                System.out.println("CurStr:"+curStr+"  ip:"+ip);
                //从s[i...n]中搜索
                generateIpAddress(s.substring(i),count+1,ip+curStr+".");
            }

        }
    }

    //判断这个数字是否符合0-255之间
    private boolean isValid(String s){
        if (s.charAt(0)=='0'){
            return s.equals("0");
        }
        int i = Integer.parseInt(s);
        if (i>=0&&i<=255){
            return true;
        }else {
            return false;
        }
    }

    public List<String> restoreIpAddresses1(String s){
        if (s.length() < 4 || s.length() > 12){
            return res;
        }
        generateIp(s,"",0);
        return res;

    }
    private void generateIp(String s,String temp,int n){
        // 剪枝，因ip不会超过三位
        if (s.length()>3*(4-n)){
            return;
        }
        if (n==4){
            if (s.length()==0) {
                //去除最后一个"."
                System.out.println("ip completed ->"+temp.substring(0, temp.length() - 1));
                res.add(temp.substring(0, temp.length() - 1));
            }
        }
        for (int i = 1; i < 4; i++) {
            // 如果剩余的长度还不够i那么说明不能排列成ip
            if (s.length()<i){
                break;
            }
            if (isValid(s.substring(0,i))){
                System.out.println("current s:"+s.substring(i)+" last temp:"+temp+" current temp:"+s.substring(0,i));
                generateIp(s.substring(i),temp+s.substring(0,i)+".",n+1);
            }else {
                System.out.println("backTracking ...");
            }
        }
    }
```



# 二维平面上的回溯法

相关题目：
<!-- GFM-TOC -->
* [79.单词搜索](#79)  
<!-- GFM-TOC -->
## 79.单词搜索

### 描述

给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

示例:
```java
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

给定 word = "ABCCED", 返回 true.
给定 word = "SEE", 返回 true.
给定 word = "ABCB", 返回 false.
```
### 分析

DFS+回溯法

每次不断地从当前位置的上右下左，这样的顺时针方向搜索。

### 实现

```java
private boolean[][] visited;
    //用来表示上右下左这四个方向
    private int[][] d={{-1,0},{0,1},{1,0},{0,-1}};
    private int m,n;


    public boolean exist(char[][] board, String word) {
        visited=new boolean[board.length][board[0].length];
        m=board.length;
        n=board[0].length;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (search(board,word,0,i,j)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean search(char[][] board,String word,int index,int startX,int startY ){
        if (index==word.length()-1){
            if (board[startX][startY]==word.charAt(index)) {
                System.out.println("found completed x:"+startX+" y:"+startY);
                return true;
            }
        }
        if (board[startX][startY]==word.charAt(index)){
            visited[startX][startY]=true;
            //按照上右下左（顺时针）的顺序搜索
            for (int i = 0; i < 4; i++) {
                int newStartX = startX + d[i][0];
                int newStartY = startY + d[i][1];
                System.out.println("seraching x:"+newStartX+" y:"+newStartY+" target:"+word.charAt(index));
                if (inArea(newStartX,newStartY)&&!visited[newStartX][newStartY]){
                    System.out.println("seraching x:"+newStartX+" y:"+newStartY+" current :"+board[newStartX][newStartY]);
                    if (search(board,word,index+1,newStartX,newStartY)){
                        return true;
                    }
                }
            }
            //回溯
            System.out.println("backTracking ... x:"+startX+" y:"+startY);
            visited[startX][startY]=false;
        }
        return false;

    }

    //判断当前坐标是否在给定区域内
    private boolean inArea(int x,int y){
        return x>=0&&x<m&&y>=0&&y<n;
    }
```
### floodfill算法

相关题目：
<!-- GFM-TOC -->

- [200.岛屿的个数](#200)  
- [130.被围绕的区域](#130) 
- [417.太平洋大西洋水流问题](#417) 
- [695.岛屿的最大面积](#695)
  <!-- GFM-TOC -->

### 200

给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。

```java
示例 1:

输入:
11110
11010
11000
00000

输出: 1
示例 2:

输入:
11000
11000
00100
00011

输出: 3
```

- 分析：
- 实现：

```java
private int m,n;
    private boolean[][] visited;
    private int[][] d={{-1,0},{0,1},{1,0},{0,-1}};

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        m=grid.length;
        n=grid[0].length;
        int res=0;
        visited=new boolean[m][n];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]=='1'&&!visited[i][j]){
                    dfs(grid,i,j);
                    res++;

                }
            }
        }
        return res;
    }

    // 从grid[x][y]的位置开始,进行floodfill
    // 保证(x,y)合法,且grid[x][y]是没有被访问过的陆地
    private void dfs(char[][] grid,int x,int y){
        visited[x][y]=true;
        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if (inArea(newX,newY)&&!visited[newX][newY]&&grid[newX][newY]=='1'){
                dfs(grid,newX,newY);
            }
        }
        return;

    }
    //判断当前坐标是否在给定区域内
    private boolean inArea(int x,int y){
        return x>=0&&x<m&&y>=0&&y<n;
    }
```

### 130

给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。

找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

示例:

```java
X X X X
X O O X
X X O X
X O X X

运行你的函数后，矩阵变为：

X X X X
X X X X
X X X X
X O X X
```

解释:

被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。

- 分析：
- 实现：

```java
private int m,n;
    private boolean[][] isO;
    private int[][] d={{-1,0},{0,1},{1,0},{0,-1}};

    //判断当前坐标是否在给定区域内
    private boolean inArea(int x,int y){
        return x>=0&&x<m&&y>=0&&y<n;
    }


    //将所有和边界O相连的O都标记出来。
    public void solve(char[][] board) {
        m=board.length;
        if (m==0){
            return;
        }
        n=board[0].length;
        isO =new boolean[m][n];
        for (int i = 0; i < m; i++) {
            if (board[i][0]=='O'&&!isO[i][0]){
                dfs(board,i,0);
            }
            if (board[i][n-1]=='O'&&!isO[i][n-1]){
                dfs(board,i,n-1);
            }
        }
        for (int i = 0; i < n; i++) {
            if (board[0][i]=='O'&&!isO[0][i]){
                dfs(board,0,i);
            }
            if (board[m-1][i]=='O'&&!isO[m-1][i]){
                dfs(board,m-1,i);
            }
        }
        for (int i = 1; i < m-1; i++) {
            for (int j = 1; j < n-1; j++) {
                if (!isO[i][j]&&board[i][j]=='O'){
                    board[i][j]='X';
                }
            }
        }
    }
    private void dfs(char[][] board,int x,int y){
        System.out.println("x:"+x+","+"y:"+y);
        isO[x][y]=true;
        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if (inArea(newX,newY)&&board[newX][newY]=='O'&&!isO[newX][newY]){
                dfs(board,newX,newY);
            }
        }
    }
```

### 417

给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。

规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。

请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。

 

提示：

输出坐标的顺序不重要
m 和 n 都小于150

示例：

```java
给定下面的 5x5 矩阵:

  太平洋 ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * 大西洋

返回:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
```

- 分析：
- 实现：

```java
private List<int[]> res=new ArrayList<>();
    private int m,n;
    private int[][] d={{-1,0},{0,1},{1,0},{0,-1}};
    private boolean[][] pFlag;
    private boolean[][] aFlag;


    //判断当前坐标是否在给定区域内
    private boolean inArea(int x,int y){
        return x>=0&&x<m&&y>=0&&y<n;
    }

    public List<int[]> pacificAtlantic(int[][] matrix) {
        m=matrix.length;
        if (m==0){
            return res;
        }
        n=matrix[0].length;
        pFlag=new boolean[m][n];
        aFlag=new boolean[m][n];
        //太平洋
        for (int i = 0; i < m; i++) {
            if (!pFlag[i][0]){
                dfs(matrix,pFlag,i,0);
            }
        }

        for (int i = 0; i < n; i++) {
            if (!pFlag[0][i]){
                dfs(matrix,pFlag,0,i);
            }
        }
        //大西洋
        for (int i = 0; i < m; i++) {
            if (!aFlag[i][n-1]){
                dfs(matrix,aFlag,i,n-1);
            }
        }

        for (int i = 0; i < n; i++) {
            if (!aFlag[m-1][i]){
                dfs(matrix,aFlag,m-1,i);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pFlag[i][j]&&aFlag[i][j]){
                    res.add(new int[]{i,j});
                }
            }
        }
        return res;

    }

    private void dfs(int[][] matrix,boolean[][] used,int x,int y){
        used[x][y]=true;
        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if (inArea(newX,newY)&&!used[newX][newY]&&matrix[newX][newY]>=matrix[x][y]){
                dfs(matrix,used,newX,newY);
            }
        }
    }
```

### 695

给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。

找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)

示例 1:

```java
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
```

对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。

示例 2:

[[0,0,0,0,0,0,0,0]]
对于上面这个给定的矩阵, 返回 0。

注意: 给定的矩阵grid 的长度和宽度都不超过 50。

- 实现：

```java
private int m,n;
    private boolean[][] visited;
    private int[][] d={{-1,0},{0,1},{1,0},{0,-1}};

    public int maxAreaOfIsland(int[][] grid) {
        m=grid.length;
        if (m==0){
            return 0;
        }
        n=grid[0].length;
        visited=new boolean[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]&&grid[i][j]==1){
                    res=Math.max(res,dfs(grid,i,j));
                }
            }
        }
        return res;

    }
    //返回的值就是[x,y]所在位置的岛屿的面积
    private int dfs(int[][] grid,int x,int y){
        visited[x][y]=true;
        int res=1;
        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            //满足此条件，说明寻找到一个位置，加入该岛屿
            //相邻位置为1的区域为岛屿
            if (inArea(newX,newY)&&!visited[newX][newY]&&grid[newX][newY]==1){
                res+=dfs(grid,newX,newY);
            }
        }
        return res;
    }
    //判断当前坐标是否在给定区域内
    private boolean inArea(int x,int y){
        return x>=0&&x<m&&y>=0&&y<n;
    }
```

# 参考资料
[玩儿转算法面试 - 课程官方代码仓](https://github.com/liuyubobobo/Play-with-Algorithm-Interview)


