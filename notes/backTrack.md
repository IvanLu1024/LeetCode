<!-- GFM-TOC -->
* [递归和回溯法的笔记总结](#递归和回溯法的笔记总结)
    * [树形问题](#树形问题)
    * [排列问题](#排列问题)
    * [组合问题](#组合问题)
    * [回溯法解决组合问题的优化](#回溯法解决组合问题的优化)  
    * [二维平面上的回溯法](#二维平面上的回溯法)
    * [floodfill算法](#floodfill算法)
    * [回溯法是人工智能的基础](#回溯法是人工智能的基础)
    
    
* [参考资料](#参考资料)
<!-- GFM-TOC -->

# 递归和回溯法的笔记总结
## 树形问题
相关题目：
<!-- GFM-TOC -->
* [17.电话号码的字母组合](#17)  
<!-- GFM-TOC -->
### 17
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

![](../pict/s17.png)

示例:

输入："23"
输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
说明:
尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
- 分析：
- 实现：
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
## 排列问题
<!-- GFM-TOC -->
相关题目：
* [46.全排列](#46)  
* [47.全排列（2）](#47) 
<!-- GFM-TOC -->
### 46
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
- 分析：
- 实现：
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
### 47
给定一个可包含重复数字的序列，返回所有不重复的全排列。

示例:

输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
- 分析：
- 实现：
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
## 组合问题
相关题目：
<!-- GFM-TOC -->
* [77.组合](#77)  
<!-- GFM-TOC -->
### 77
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
- 分析：
- 实现：
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
## 回溯法解决组合问题的优化

相关题目：
<!-- GFM-TOC -->
* [39.组合总和](#39)  
* [40.组合总和（2）](#40) 
* [216.组合总和（3）](#216) 
<!-- GFM-TOC -->
### 39
给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：

所有数字（包括 target）都是正整数。
解集不能包含重复的组合。 
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
- 分析：
- 实现：
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
### 40
给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

说明：

所有数字（包括目标数）都是正整数。
解集不能包含重复的组合。 
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
- 分析：
- 实现：
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
            //去重
            if (i>start&&c[i]==c[i-1]){
                System.out.println("repeat...");
                continue;
            }
            //剪枝，由于待搜索的数组都是正整数，则当前搜索位置元素大于目标则直接跳过
           if (c[i]<=target){
               list.add(c[i]);
               System.out.println("current :"+c[i]+"->"+list+" target:"+target);
               //由于candidates 中的数字可以无限制重复被选取，所以每次开始位置为当前位置
               generateCS(c,target-c[i],i+1,list);
               System.out.println("backTracking..."+list);
               list.remove(list.size()-1);
           }else {
               System.out.println("step over");
           }
        }
    }
```
### 216
找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。

说明：

所有数字都是正整数。
解集不能包含重复的组合。 
示例 1:

输入: k = 3, n = 7
输出: [[1,2,4]]
示例 2:

输入: k = 3, n = 9
输出: [[1,2,6], [1,3,5], [2,3,4]]
- 分析：
- 实现：
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
            if (i<=target){
                list.add(i);
                generate(k,target-i,index+1,i+1,list);
                list.remove(list.size()-1);
            }
        }
    }
```
## 二维平面上的回溯法
## floodfill算法
## 回溯法是人工智能的基础

# 参考资料
[玩儿转算法面试 - 课程官方代码仓](https://github.com/liuyubobobo/Play-with-Algorithm-Interview)


