<!-- GFM-TOC -->
* [递归和回溯法的笔记总结](#递归和回溯法的笔记总结)
    * [树形问题](#树形问题)
    * [排列问题](#排列问题)
    * [组合问题](#组合问题)
    * [回溯法解决组合问题的优化](#回溯法解决组合问题的优化)  
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
* [46.全排列](#46)  
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
## 组合问题
## 回溯法解决组合问题的优化
# 参考资料
[玩儿转算法面试 - 课程官方代码仓](https://github.com/liuyubobobo/Play-with-Algorithm-Interview)


