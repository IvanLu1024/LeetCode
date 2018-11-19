<!-- GFM-TOC -->
* [二叉树和递归部分笔记总结](#二叉树和递归部分笔记总结)
    * [二叉树天然的递归结构](#二叉树天然的递归结构)
    * [翻转二叉树](#翻转二叉树)
   
* [参考资料](#参考资料)
<!-- GFM-TOC -->
# 二叉树和递归部分笔记总结
## 二叉树天然的递归结构
- 相关题目：
<!-- GFM-TOC -->
   * [二叉树的最大深度](#104)
   * [二叉树的最小深度](#111)
<!-- GFM-TOC -->    
### 104

- 二叉树的最大深度

给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明: 叶子节点是指没有子节点的节点。

示例：
给定二叉树 [3,9,20,null,null,15,7]，


   
返回它的最大深度 3 。

- 分析：

求二叉树的最大深度，就是求二叉树的深度；这里可以很好的应用递归的方法：

首先确定递归的**终止条件**，即二叉树为空的时候，此时深度为0；接着，确定**递归的过程**，
即从根结点的**左右子树**中找出最大的深度加上1（根结点）就是二叉树的最大深度。


- 实现：
```java
public int maxDepth(TreeNode root) {
        if (root==null){
            return 0;
        }

        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;

    }
``` 
### 111
- 二叉树的最小深度

给定一个二叉树，找出其最小深度。

最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

说明: 叶子节点是指没有子节点的节点。

示例:

给定二叉树 [3,9,20,null,null,15,7],

  
返回它的最小深度  2.
- 分析：

这一题与上一道题类似，同样使用递归的方法来求解，但有一些陷阱需要注意。

首先，确定递归的终止条件，即结点为空的时候，此时深度为0；接着关键的是在确定递归的过程，
当根结点的左右子树均不空的时候，这时取左右子树深度的较小值加1（根节点），**而当左右子树中有一边
为空的时候，这时取左右子树深度的较大值加1。**

>因为当只有一边有结点的时候需要记录此时的深度，如果还是取较小值的时候(任何一个自然数和0相比，较小值永远为0)，则会丢失此时的子结构的深度。


- 实现：
```java
public int minDepth(TreeNode root) {
        if (root==null){
            return 0;
        }
        //当左右子树均不为空的时候，取左右子树中的最小值
        if (root.left!=null&&root.right!=null){
            return Math.min(minDepth(root.right),minDepth(root.left))+1;
        }
        //当左右子树不是都有结点的时候，取左右子树的最大深度
        return Math.max(minDepth(root.right),minDepth(root.left))+1;

    }
```

## 翻转二叉树 
## 注意递归的终止条件
- 相关题目：
<!-- GFM-TOC -->
   * [路径总和（1）](#112)
   * [左叶子之和](#404)
<!-- GFM-TOC --> 
### 112
给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。

说明: 叶子节点是指没有子节点的节点。

- 分析：

首先，需要注意递归的终止条件，当树为空的时候，返回结果为false；

然后，需要确定递归的过程，当遇到叶子结点的时候需要验证结果是否为待搜索的目标（sum），如果搜索到目标即可返回true，
如果没有搜索到目标就继续搜索，递归最终得到结果。
- 实现：
```java
示例:
     给定如下二叉树，以及目标和 sum = 22，

      5
     / \
     4   8
     /   / \
     11  13  4
     /  \      \
     7    2      1
     返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 * 
 **/
public class Solution112 {
    public boolean hasPathSum(TreeNode root, int sum) {
        //注意递归的终止条件
        if (root==null){
            return false;
        }
        if (root.left==null&&root.right==null){
            if (root.val==sum){
            return true;
            }
            else {
                return false;
            }
        }
        return hasPathSum(root.right,sum-root.val)||hasPathSum(root.left,sum-root.val);
    }
```
### 404
计算给定二叉树的所有左叶子之和。

- 分析：

这一题的难度在于如何确定一个结点为左叶子结点，
首先，递归的终止条件为当树为空时候，返回的结果为0；然后，递归的过程为寻找到左叶子结点后累加即可。
当一个结点的左子树不为空，并且这个子树为叶子结点的时候则为左叶子结点。

- 实现：
```java
/**
 *计算给定二叉树的所有左叶子之和。

     示例：

      3
     / \
    9  20
     /  \
   15   7

     在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 *
 *
 */
public class Solution404 {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root==null){
            return 0;
        }
        //如何判断一个结点是否为左叶子结点，当其父亲结点的左孩子不空
        if (root.left!=null){
            //并且为叶子结点，则此时为左叶子结点
            if (root.left.left==null&&root.left.right==null){
                return
                        //继续遍历右边累积结果
                        sumOfLeftLeaves(root.right)+root.left.val;
            }
        }
        return sumOfLeftLeaves(root.left)+sumOfLeftLeaves(root.right);

    }
```
## 定义递归问题
- 相关题目：
<!-- GFM-TOC -->
   * [二叉树的所有路径](#257)
   * [路径总和（2）](#113)
   * [求根到叶子节点数字之和](#129)
<!-- GFM-TOC --> 
### 257
给定一个二叉树，返回所有从根节点到叶子节点的路径。

说明: 叶子节点是指没有子节点的节点。

- 分析：

我们需要关注递归调用的**语义**，在本题中：
首先确定递归的终止条件，当二叉树为空的时候，那么直接返回一个空的集合，当二叉树为叶子结点的时候，那么将该节点的值
加入集合中；

接着是递归的过程，分别递归调用获得左右子树以其为根结点到叶子结点的路径，将当前路径加上以其孩子结点为根结点到叶子结点的路径，即可。
- 实现：
```java
/**
* 示例:
  
  输入:
  
     1
   /   \
  2     3
   \
    5
  
  输出: ["1->2->5", "1->3"]
  
  解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
* 
*/
public List<String> binaryTreePaths(TreeNode root) {
        List<String> res=new LinkedList<>();
        if (root==null){
            return res;
        }
        //当寻找到叶子结点的时候，就将叶子结点加入集合
        if (root.left==null&&root.right==null){
            res.add(String.valueOf(root.val));
        }
        //分别添加左右子树，注意递归调用的语义！

        //获得左子树结点从根到叶子所有的路径
        List<String> leftStr = binaryTreePaths(root.left);
        for (String s : leftStr) {
            res.add(String.valueOf(root.val)+"->"+s);
        }
        //获得右子树结点从根到叶子所有的路径
        List<String> rightStr = binaryTreePaths(root.right);
        for (String s : rightStr) {
            res.add(String.valueOf(root.val)+"->"+s);
        }
        return res;
    }
```
### 113

### 129 

# 参考资料
[玩儿转算法面试 - 课程官方代码仓](https://github.com/liuyubobobo/Play-with-Algorithm-Interview)

