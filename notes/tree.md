<!-- GFM-TOC -->
* [二叉树和递归部分笔记总结](#二叉树和递归部分笔记总结)
    * [二叉树——天然的递归结构](#二叉树——天然的递归结构)
    * [翻转二叉树](#翻转二叉树)
    * [注意递归的终止条件](#注意递归的终止条件)
    * [定义递归问题](#定义递归问题)
    * [二分搜索树中的问题](#二分搜索树中的问题)
   
* [参考资料](#参考资料)
<!-- GFM-TOC -->
# 二叉树和递归部分笔记总结
## 二叉树——天然的递归结构
- 相关题目：
<!-- GFM-TOC -->
   * [104.二叉树的最大深度](#104)
   * [111.二叉树的最小深度](#111)
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
- 相关题目：
<!-- GFM-TOC -->
   * [226.翻转二叉树](#226)
   * [100.相同的树](#100)
   * [101.对称二叉树](#101)
   * [110.平衡二叉树](#110)
<!-- GFM-TOC --> 
### 226
### 100
### 101
### 110
## 注意递归的终止条件
- 相关题目：
<!-- GFM-TOC -->
   * [112.路径总和（1）](#112)
   * [404.左叶子之和](#404)
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
   * [257.二叉树的所有路径](#257)
   * [113.路径总和（2）](#113)
   * [129.求根到叶子节点数字之和](#129)
   * [222.完全二叉树的节点个数](#222)
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

### 222
给出一个完全二叉树，求出该树的节点个数。

说明：

完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。

示例:
```java
输入: 
    1
   / \
  2   3
 / \  /
4  5 6

输出: 6
```
## 稍复杂的递归逻辑
- 相关题目：
<!-- GFM-TOC -->
   * [路径总和（3）](#437)
<!-- GFM-TOC --> 
### 437
给定一个二叉树，它的每个结点都存放着一个整数值。

找出路径和等于给定数值的路径总数。

路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。

示例：
```java
root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

返回 3。和等于 8 的路径有:

1.  5 -> 3
2.  5 -> 2 -> 1
3.  -3 -> 11
```


## 二分搜索树中的问题
- 相关题目：
<!-- GFM-TOC -->
   * [235.二叉搜索树的最近公共祖先](#235)
   * [98.验证二叉搜索树](#98)
   * [450.删除二叉搜索树中的节点](#450)
   * [108.将有序数组转换为二叉搜索树](#108)
   * [109.有序链表转换二叉搜索树](#109)
   * [230.二叉搜索树中第K小的元素](#230)
   * [236.二叉树的最近公共祖先](#236)
   * [530.二叉搜索树的最小绝对差](#530)
<!-- GFM-TOC --> 
### 235
给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]

```java
        _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5
``` 
示例 1:

输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
输出: 6 
解释: 节点 2 和节点 8 的最近公共祖先是 6。
示例 2:

输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
输出: 2
解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
说明:

所有节点的值都是唯一的。
p、q 为不同节点且均存在于给定的二叉搜索树中。
- 分析：

由于给定的二叉树是二叉搜索树，那么可以运用其性质来求解这道题目；
还是同样使用递归的方法，首先确定递归的终止条件，当结点为空的时候，那么此时不存在
公共祖先；

接着来确定递归的过程，此时可以分为三种情况讨论：
1. 如果p,q同时小于根节点的时候，那么在左子树中继续寻找；
2. 如果p,q同时大于根节点的时候，那么在右子树中继续寻找；
3. 如果p,q分布在根节点的两侧的时候，此时公共祖先为当前的根节点。

这样，就可以很轻松的写出递归的解法了。
- 实现：
```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null){
            return null;
        }
        //如果p,q同时小于根节点的时候，那么在左子树中继续寻找
        if (p.val<root.val&&q.val<root.val){
            return lowestCommonAncestor(root.left,p,q);
        }
        //如果p,q同时大于根节点的时候，那么在右子树中继续寻找
        if (p.val>root.val&&q.val>root.val){
            return lowestCommonAncestor(root.right,p,q);
        }
        //如果p,q分布在根节点的两侧的时候，此时公共祖先为当前的根节点
        return root;
    }
```
### 98
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
```java
示例 1:

输入:
    2
   / \
  1   3
输出: true
示例 2:

输入:
    5
   / \
  1   4
     / \
    3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
     根节点的值为 5 ，但是其右子节点值为 4 。
```
### 450
给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。

一般来说，删除节点可分为两个步骤：

首先找到需要删除的节点；
如果找到了，删除它。
说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
```java
示例:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。

一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。

    5
   / \
  4   6
 /     \
2       7

另一个正确答案是 [5,2,6,null,4,null,7]。

    5
   / \
  2   6
   \   \
    4   7
```

### 108
将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

示例:
```java
给定有序数组: [-10,-3,0,5,9],

一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5
```
- 实现：
```java
public TreeNode sortedArrayToBST(int[] nums) {
        if (nums==null||nums.length==0){
            return null;
        }
        return createTreeNode(nums,0,nums.length-1);
    }
    private TreeNode createTreeNode(int[]nums,int l,int h){
        //特别注意，边界条件
        if (l>h){
            return null;
        }
        int mid=l+(h-l)/2;
        TreeNode root=new TreeNode(nums[mid]);
        root.left=createTreeNode(nums,l,mid-1);
        root.right=createTreeNode(nums,mid+1,h);
        return root;
    }
```
### 109
给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

示例:
```java
给定有序数组: [-10,-3,0,5,9],

一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5
```
- 实现：
```java
//首先将链表转化为数组或者集合，这样就转化为了108题
    public TreeNode sortedListToBST(ListNode head) {
        if (head==null){
            return null;
        }
        List<Integer> list=new ArrayList<>();
        ListNode cur = head;
        while (cur!=null){
            list.add(cur.val);
            cur=cur.next;
        }
        return createTree(list,0,list.size()-1);
    }
    private TreeNode createTree(List<Integer> list,int l,int r){
        //此时表明已经不存在这个结点
        if (l>r){
            return null;
        }
        int mid = l+(r-l)/2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left=createTree(list,l,mid-1);
        root.right=createTree(list,mid+1,r);
        return root;
    }
```
### 230
给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。

说明：
你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。

示例 1:
```java
输入: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
输出: 1
示例 2:

输入: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
输出: 3
```
进阶：
如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？

- 思路：

利用中序遍历的结果为从小到大排列的性质。

- 实现：
```java
private List<Integer> res=new ArrayList<>();
    public int kthSmallest(TreeNode root, int k) {
        inOrder(root);
        return res.get(k-1);
    }
    private void inOrder(TreeNode root){
        if (root==null){
            return ;
        }
        inOrder(root.left);
        res.add(root.val);
        inOrder(root.right);
    }
    public int kthSmallest1(TreeNode root, int k){
        InOrder(root, k);
        return result;

    }
    private int count=0;
    private int result;

    private void InOrder(TreeNode root,int k){

        if (root.left!=null){
            InOrder(root.left,k);
        }
        count++;
        if (count==k){
            result=root.val;
            return;
        }
        if (root.right!=null){
            InOrder(root.right,k);
        }

    }
```

### 236
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
```java

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
```
示例 1:

输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出: 3
解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
示例 2:

输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出: 5
解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
说明:

所有节点的值都是唯一的。
p、q 为不同节点且均存在于给定的二叉树中。

### 530
给定一个所有节点为非负值的二叉搜索树，求树中任意两节点的差的绝对值的最小值。
```java
示例 :

输入:

   1
    \
     3
    /
   2

输出:
1

解释:
最小绝对差为1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
```
注意: 树中至少有2个节点。

# 参考资料
[玩儿转算法面试 - 课程官方代码仓](https://github.com/liuyubobobo/Play-with-Algorithm-Interview)

