<!-- GFM-TOC -->
* [二叉树和递归部分笔记总结](#二叉树和递归部分笔记总结)
    * [二叉树——天然的递归结构](#二叉树——天然的递归结构)
    * [翻转二叉树](#翻转二叉树)
    * [注意递归的终止条件](#注意递归的终止条件)
    * [定义递归问题](#定义递归问题)
    * [二分搜索树中的问题](#二分搜索树中的问题)
    * [更多关于二叉树的问题](#更多关于二叉树的问题)
   
* [参考资料](#参考资料)
<!-- GFM-TOC -->
# 二叉树——天然的递归结构
- 相关题目：
<!-- GFM-TOC -->
   * [104.二叉树的最大深度](#104)
   * [111.二叉树的最小深度](#111)
<!-- GFM-TOC -->    
## 104

二叉树的最大深度

### 描述

给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明: 叶子节点是指没有子节点的节点。

示例：
给定二叉树 [3,9,20,null,null,15,7]，

返回它的最大深度 3 。

### 分析

求二叉树的最大深度，就是求二叉树的深度；这里可以很好的应用**递归**的方法：

首先确定递归的**终止条件**，即二叉树为空的时候，此时深度为0；接着，确定**递归的过程**，即从根结点的**左右子树**中找出最大的深度加上1（根结点）就是二叉树的最大深度。

### 实现

```java
public int maxDepth(TreeNode root) {
        if (root==null){
            return 0;
        }

        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;

    }
```
## 111*

二叉树的最小深度

### 描述

给定一个二叉树，找出其最小深度。

最小深度是**从根节点到最近叶子节点的最短路径上的节点数量**。

说明: 叶子节点是指没有子节点的节点。

示例:

给定二叉树 [3,9,20,null,null,15,7],


返回它的最小深度  2.
### 分析

这一题与上一道题类似，同样使用递归的方法来求解，但有一些陷阱需要注意：

- 首先，确定递归的终止条件，即结点为空的时候，此时深度为0；
- 接着关键的是在确定递归的过程，**当根结点的左右子树均不空的时候**，这时取左右子树深度的较小值加1（根节点）；
- 当左右子树均为空，此时该节点为叶子节点，此时该节点到叶子节点的深度为1，无论取最大值或最小值均可；
- **而当左右子树中只有一边为空的时候，这时取左右子树深度的较大值加1。**左右子树只有一边为空，那么此时该节点必然不是叶子结点，需要继续递归，则在有子树的那一边继续递归，所以需要取较大值。

### 实现

```java
public int minDepth(TreeNode root) {
        if (root==null){
            return 0;
        }
        //当左右子树均不为空的时候，取左右子树中的最小值
        if (root.left!=null&&root.right!=null){
            return Math.min(minDepth(root.right),minDepth(root.left))+1;
        }
    	//当左右子树均为空的时候，该节点为叶子节点直接返回1
    	 if(root.left==null&&root.right==null){
            return 1;
        }
        //当左右子树不是都有结点的时候，取左右子树的最大深度
        return Math.max(minDepth(root.right),minDepth(root.left))+1;

    }
```

- 相关题目：
<!-- GFM-TOC -->
   * [226.翻转二叉树](#226)
   * [100.相同的树](#100)
   * [101.对称二叉树](#101)
   * [110.平衡二叉树](#110)
<!-- GFM-TOC --> 
## 226

翻转二叉树

### 描述

翻转一棵二叉树。

**示例：**

输入：

```
     4
   /   \
  2     7
 / \   / \
1   3 6   9
```

输出：

```
     4
   /   \
  7     2
 / \   / \
9   6 3   1
```

**备注:**
这个问题是受到 [Max Howell ](https://twitter.com/mxcl)的 [原问题](https://twitter.com/mxcl/status/608682016205344768) 启发的 ：

> 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。

### 分析

这里需要注意，树的递归问题。那么翻转的时候，首先翻转当前节点的左右子树的子结构，再将当前节点的左右子树交换，需要注意这里的递归出口有两个。

### 实现

```java
public TreeNode invertTree(TreeNode root) {
        if(root==null||(root.left==null&&root.right==null)){
            return root;
        }
        invertTree(root.left);
        invertTree(root.right);
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
        return root;
    }
```

## 100

相同的树

### 描述

给定两个二叉树，编写一个函数来检验它们是否相同。

如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

**示例 1:**

```
输入:       1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

输出: true
```

**示例 2:**

```
输入:      1          1
          /           \
         2             2

        [1,2],     [1,null,2]

输出: false
```

**示例 3:**

```
输入:       1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

输出: false
```

### 分析

递归，当两棵树均为空的时候返回ture，若只有一棵树为空则返回false；当前两个节点的值相等的时候，继续递归判断其左右子树；不相等，则返回false。

### 实现

```java
public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null){
            return true;
        }else if (p==null||q==null){
            return false;
        }
        
        if(p.val==q.val){
            return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
        }else{
            return false;
        }   
    }
```

## 101

对称二叉树

### 描述

给定一个二叉树，检查它是否是镜像对称的。

例如，二叉树 `[1,2,2,3,4,4,3]` 是对称的。

```
    1
   / \
  2   2
 / \ / \
3  4 4  3
```

但是下面这个 `[1,2,2,null,3,null,3]` 则不是镜像对称的:

```
    1
   / \
  2   2
   \   \
   3    3
```

**说明:**

如果你可以运用递归和迭代两种方法解决这个问题，会很加分。

### 分析

因为需要**同时判断左右子树**，所以需要构造一个辅助函数。这个辅助函数来递归判断节点的左右子树是否对称。

### 实现

```java
public boolean isSymmetric(TreeNode root) {
        if(root==null)
            return true;
        return isSymmetric(root.left,root.right);
    }
    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left==null&&right==null){
            return true;
        }else if (left==null||right==null){
            return false;
        }
        if (left.val!=right.val){
            return false;
        }
        return isSymmetric(left.left,right.right)&&isSymmetric(right.left,left.right);
        
    }
```

## 110

平衡二叉树

### 描述

给定一个二叉树，判断它是否是高度平衡的二叉树。

本题中，一棵高度平衡二叉树定义为：

> 一个二叉树*每个节点* 的左右两个子树的高度差的绝对值不超过1。

**示例 1:**

给定二叉树 `[3,9,20,null,null,15,7]`

```
    3
   / \
  9  20
    /  \
   15   7
```

返回 `true` 。

**示例 2:**

给定二叉树 `[1,2,2,3,3,null,null,4,4]`

```
       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
```

返回 `false` 。

### 分析

根据平衡二叉树的定义，其所有子树的左右子树的高度差均不超过1，若超过则就不是平衡二叉树。这里需要注意，在计算深度的时候就可以设置isBalanced**全局变量**判断当前子树是否平衡。

### 实现

```java
	boolean isBalanced = true;
    public boolean isBalanced(TreeNode root) {
        if(root==null){
            return true;
        }
        depth(root);
        return isBalanced;
    }
    private int depth(TreeNode root){
        if(root==null){
            return 0;
        }
        int l = depth(root.left);
        int r = depth(root.right);
        if(Math.abs(l-r)>1) isBalanced=false;
        return Math.max(l,r)+1;
    }
```



# 注意递归的终止条件
- 相关题目：
<!-- GFM-TOC -->
   * [112.路径总和（1）](#112)
   * [404.左叶子之和](#404)
<!-- GFM-TOC --> 
## 112

路径总和（1）

### 描述

给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。

**说明:** 叶子节点是指没有子节点的节点。

**示例:** 
给定如下二叉树，以及目标和 `sum = 22`，

```
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
```

返回 `true`, 因为存在目标和为 22 的根节点到叶子节点的路径 `5->4->11->2`。

### 分析

首先，需要注意递归的终止条件，**当树为空的时候**，返回结果为false；

然后，需要确定递归的过程，**当遇到叶子结点**的时候需要验证结果是否为待搜索的目标（sum），如果搜索到目标即可返回true，没有则返回false，表明这条路径不可行。
如果没有搜索到目标就**继续搜索**，递归最终得到结果。

### 实现

```java
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
## 404

左叶子之和

### 描述

计算给定二叉树的所有左叶子之和。

**示例：**

```
    3
   / \
  9  20
    /  \
   15   7

在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
```

### 分析

这一题的难度在于如何确定一个结点为左叶子结点。

> **当一个结点的左子树不为空，并且这个子树为叶子结点的时候则为左叶子结点。**

首先，递归的终止条件为当树为空时候，返回的结果为0；

然后，递归的过程为寻找到左叶子结点后累加后继续去右子树搜索；

最后，再去递归左子树和右子树。



### 实现

```java
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
# 树中的深度遍历问题
- 相关题目：
  <!-- GFM-TOC -->
   * [257.二叉树的所有路径](#257)
   * [113.路径总和（2）](#113)
   * [437.路径总和（3）](#437)
   * [129.求根到叶子节点数字之和](#129)
   * [222.完全二叉树的节点个数](#222)
      <!-- GFM-TOC --> 
## 257

二叉树的所有路径

### 描述

给定一个二叉树，返回所有从根节点到叶子节点的路径。

**说明:** 叶子节点是指没有子节点的节点。

**示例:**

```
输入:

   1
 /   \
2     3
 \
  5

输出: ["1->2->5", "1->3"]

解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
```

### 分析

我们需要关注递归调用的**语义**，在本题中：
首先确定递归的终止条件，当二叉树为空的时候，那么直接返回一个空的集合，当二叉树为叶子结点的时候，那么将该节点的值，加入集合中；

接着是递归的过程，分别递归调用获得左右子树以其为根结点到叶子结点的路径，将当前路径加上以其孩子结点为根结点到叶子结点的路径，即可。
### 实现

```java
public List<String> binaryTreePaths(TreeNode root) {
        List<String> res=new ArrayList();
        if(root==null){
            return res;
        }
        generate(root,"",res);
        return res;
    }
    private void generate(TreeNode root,String s,List<String> res){
        if(root.left==null&&root.right==null){
            res.add(s+root.val);
            return;
        }
            s+=root.val+"->";
            if(root.left!=null){
                generate(root.left,s,res);
            }
            if(root.right!=null){
                generate(root.right,s,res);
            }
    }
```
## 113

路径总和（2）

### 描述

给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

**说明:** 叶子节点是指没有子节点的节点。

**示例:**
给定如下二叉树，以及目标和 `sum = 22`，

```
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
```

返回:

```
[
   [5,4,11,2],
   [5,8,4,5]
]
```

### 分析

使用回溯法（即深度遍历），当搜索到叶子结点并且和为sum的时候将当前遍历的节点加入结果集合，否则继续在其左右子树中搜索。

### 实现

```java
public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> list = new ArrayList();
        generatePath(root,sum,list,res);
        return res;
    }

    private void generatePath(TreeNode node,int sum,List<Integer> list,List<List<Integer>> res){
        if (node==null){
            return;
        }
        list.add(node.val);
        if (node.left==null&&node.right==null){
            if (sum-node.val==0){
                res.add(new ArrayList<>(list));
            }
        }else {
            if (node.left!=null){
                generatePath(node.left,sum-node.val,list,res);
            }
            if (node.right!=null){
                generatePath(node.right,sum-node.val,list,res);
            }
        }
        list.remove(list.size()-1);
    }
```

## 437**

路径总和（3）

### 描述

给定一个二叉树，它的每个结点都存放着一个整数值。

找出路径和等于给定数值的路径总数。

**路径不需要从根节点开始，也不需要在叶子节点结束**，但是路径方向必须是向下的（只能从父节点到子节点）。

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

### 分析

题干中搜索的目标路径**不需要从根节点开始**，说明每个节点都可以作为该路径的起点。目标路径也不需要在叶子节点结束，说明在搜索每个节点的时候都可以判断当前累加和是否满足sum，若满足则计数器加一。否则，继续向下进行搜索。

### 实现

```java
public int pathSum(TreeNode root, int sum) {
        if(root==null){
            return 0;
        }
    	//先搜索根节点，此时起点为根节点
        int count=dfs(root,sum);
    	//再搜索左右子树，此时起点就是左右子树节点
        count+=pathSum(root.left,sum);
        count+=pathSum(root.right,sum);
        return count;
    }
	//深度遍历，从当前节点开始搜索
    private int dfs(TreeNode node,int sum){
        if (node==null){
            return 0;
        }
        int count=0;
        sum-=node.val;
        //表明搜索到一个符合条件的解
        if (sum==0){
            count++;
        }
        //继续搜索左右子树
        return count+dfs(node.left,sum)+dfs(node.right,sum);
    }
```



## 129*

求根到叶子节点数字之和 

### 描述

给定一个二叉树，它的每个结点都存放一个 `0-9` 的数字，每条从根到叶子节点的路径都代表一个数字。

例如，从根到叶子节点路径 `1->2->3` 代表数字 `123`。

计算从根到叶子节点生成的所有数字之和。

**说明:** 叶子节点是指没有子节点的节点。

**示例 1:**

```
输入: [1,2,3]
    1
   / \
  2   3
输出: 25
解释:
从根到叶子节点路径 1->2 代表数字 12.
从根到叶子节点路径 1->3 代表数字 13.
因此，数字总和 = 12 + 13 = 25.
```

**示例 2:**

```
输入: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
输出: 1026
解释:
从根到叶子节点路径 4->9->5 代表数字 495.
从根到叶子节点路径 4->9->1 代表数字 491.
从根到叶子节点路径 4->0 代表数字 40.
因此，数字总和 = 495 + 491 + 40 = 1026.
```

### 分析

和上面的题目思路是类似的，也是使用深度遍历。不过这里需要主要对于sum的处理，每搜索到下一层当前的sum需要进位，即**10*sum**。

### 实现

```java
    //深度遍历
    public int sumNumbers(TreeNode root) {
        if (root==null){
            return 0;
        }
        int results=dfs(root,0);
        return results;

    }
    private int dfs(TreeNode root,int sum){
        if (root==null){
            return 0;
        }
        //若为叶子结点，则直接返回计算结果
        if (root.right==null&&root.left==null){
            return sum*10+root.val;
        }
        return dfs(root.right,10*sum+root.val)+dfs(root.left,10*sum+root.val);
    }
```

## 222*

完全二叉树的节点个数

### 描述

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
### 分析

思路1 ： 直接使用深度遍历，若当前节点为空，则返回0，否则将当前节点累加上继续遍历左右子树；

思路2：利用完全二叉树的性质，完全二叉树去除最后一行，为一棵满二叉树。满二叉树的节点数可以利用公式：

n=2^h-1。所以在遍历过程中，可以先利用公式计算，若不能使用公式则使用累加。

### 实现

思路1：

```java
 public int countNodes(TreeNode root) {
        return root==null ? 0 : countNodes(root.left)+countNodes(root.right)+1;
    }
```

思路2：

```java
public int countNodes(TreeNode root) {
        if (root==null){
            return 0;
        }

        //先统计左右子树的高度
        int leftHeight=getLeftHeight(root);
        int rightHeight=getRightHeight(root);
        //如果左右子树高度相等，则说明是满二叉树，则按公式来计算
        if (leftHeight==rightHeight)
            return (1<<leftHeight)-1;
        //如果不相等，则遍历计数
        else return countNodes(root.right)+countNodes(root.left)+1;

    }

	//由于该树为完全二叉树，其右子树的高度就是根节点到最右端节点的距离
    private int getRightHeight(TreeNode right) {
        int height=1;
        while (right.right!=null){
            right=right.right;
            height++;
        }
        return height;
    }
	
    private int getLeftHeight(TreeNode left) {
        int height=1;
        while (left.left!=null){
            left=left.left;
            height++;
        }
        return height;
    }
```

## 完全二叉树

判断是否为完全二叉树

```java
public class CompeteTree {
    //
    /*任意的一个二叉树，都可以补成一个满二叉树。这样中间就会有很多空洞。在广度优先遍历的时候，如果是满二叉树，或者完全二叉树，这些空洞是在广度优先的遍历的末尾，所以，但我们遍历到空洞的时候，整个二叉树就已经遍历完成了。而如果，是非完全二叉树，我们遍历到空洞的时候，就会发现，空洞后面还有没有遍历到的值。这样，只要根据是否遍历到空洞，整个树的遍历是否结束来判断是否是完全的二叉树。
*/
    public boolean isCompete(TreeNode root){
        if (root==null)
            return true;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        TreeNode t;
        while ((t=queue.poll())!=null){
            queue.offer(t.left);
            queue.offer(t.right);
        }
        while (!queue.isEmpty()){
            t=queue.poll();
            if (t!=null){
                return false;
            }
        }
        return true;
    }
    @Test
    public void test(){
        int[] pre={1,2,4,3,5};
        int[] in={4,2,1,5,3};
        TreeNode root = TreeNodeUtils.ConstructBinaryTree(pre, in);
        boolean b = isCompete(root);
        System.out.println(b);
    }
}
```

# 二分搜索树中的问题

相关题目：

 * [235.二叉搜索树的最近公共祖先](#235)
 * [98.验证二叉搜索树](#98)
 * [450.删除二叉搜索树中的节点](#450)
 * [108.将有序数组转换为二叉搜索树](#108)
 * [109.有序链表转换二叉搜索树](#109)
 * [230.二叉搜索树中第K小的元素](#230)
 * [236.二叉树的最近公共祖先](#236)
 * [530.二叉搜索树的最小绝对差](#530)
 * [173.二叉搜索树的迭代器](#173)
 * [-897.递增顺序查找树](#897)

## 235

二叉搜索树的最近公共祖先

### 描述

给定一个**二叉搜索树**, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]

```java
        _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4_      7       9
         /   \
        3     5
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
### 分析

由于给定的二叉树是二叉搜索树，那么可以运用其性质来求解这道题目；
还是同样使用递归的方法，首先确定递归的终止条件，当结点为空的时候，那么此时不存在
公共祖先；

接着来确定递归的过程，此时可以分为三种情况讨论：
1. 如果p,q同时小于根节点的时候，那么在左子树中继续寻找；
2. 如果p,q同时大于根节点的时候，那么在右子树中继续寻找；
3. 如果p,q分布在根节点的**两侧**的时候，此时公共祖先为当前的**根节点**。

这样，就可以很轻松的写出递归的解法了。
### 实现

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
## 236*

二叉树的最近公共祖先

### 描述

给定一个**二叉树**, 找到该树中两个指定节点的最近公共祖先。

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

### 实现

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //首先最近公共祖先判断是否是该节点本身
        if (root==null||root==q||root==p){
            return root;
        }
        //在左侧继续寻找
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        //在右侧继续寻找
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        //说明p,q分布在两侧
        if (left!=null&&right!=null){
            return root;
        }
        //在左侧上寻找到结果
        if (left!=null){
            return left;
        }
        //在右侧上寻找到结果
        if (right!=null){
            return right;
        }
        //否则，没有结果返回null
        return null;
    }
```

## 98

验证二叉搜索树

### 描述

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
### 分析

根据二分搜索树的性质可得其中序遍历的为**从小到大**的排列，那么从这点出发来寻找解题的突破口。

设置一个全局变量来记录中序遍历的前一个结点即pre，在中序遍历的时候，不断地判断当前节点值和前一个节点值的关系，若当前节点值大于前一个节点值则继续递归，否则返回false。

### 实现

```java
	private boolean flag=true;	//判断标志
    private TreeNode pre=null;	//用于记录本次遍历的上一个结点
    public boolean isValidBST(TreeNode root) {
        dfs(root);
        return flag;
    }
    private void dfs(TreeNode node){
        if(node==null)
            return;
        dfs(node.left);
        //不满足升序排序
        if(pre!=null&&pre.val>=node.val)
            flag=false;
        pre=node;
        dfs(node.right);
    }
```

## 450*

删除二叉搜索树中的节点

### 描述

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

### 分析

首先搜索待删除节点，若key小于当前节点值则继续在左子树中搜索，若大于，则继续在右子树中搜索，若等于则说明寻找到了待删除节点。

对于待删除节点，需要分三种情况来讨论：

1. 左子树为空的时候，若删除当前节点，就只需要将其左子树覆盖即可；
2. 右子树为空的时候，若删除当前节点，就只需要将其右子树覆盖即可；
3. **左右子树均不为空**的时候，若删除当前节点，其中一种方法就是**选择中序序列中与该节点值相邻的节点来替换即可**。那么可以选择**右子树中的最小元素**或**左子树中的最大元素**，笔者在这里是选择了右子树的最小元素的方式来实现的。

### 实现

```java
public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null){
            return null;
        }
        if(key==root.val){
            //当左子树为空的时候
            if (root.left==null){
                TreeNode right = root.right;
                return right;
            }
            //当右子树为空的时候
            if (root.right==null){
                TreeNode left = root.left;
                return left;
            }
            //当左右子树均不为空的时候
            if (root.left!=null&&root.right!=null){
                //寻找右子树中最小的元素
                TreeNode successNode = minNode(root.right);
                successNode.right = delMinNode(root.right);
                successNode.left = root.left;
                return successNode;
            }


        }else if(key<root.val){
            root.left=deleteNode(root.left,key);
        }else{
            root.right=deleteNode(root.right,key);
        }
        return root;

    }

    //寻找最小值
    private TreeNode minNode(TreeNode root){
        if (root==null){
            return null;
        }
        if (root.left==null){
            return root;
        }
        return minNode(root.left);
    }
    //删除最小值，并返回删除后的根节点
    private TreeNode delMinNode(TreeNode root){
        //当左侧为空的时候，删除当前结点
        if (root.left==null){
            TreeNode rightNode = root.right;
            root.right=null;
            return rightNode;
        }
        root.left = delMinNode(root.left);
        return root;
    }
```

## 108

将有序数组转换为二叉搜索树

### 描述

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
### 分析

根据二叉搜索树的性质可得，其中序遍历得到的结果为有序序列，不断地根据中点来构建节点，就是中序遍历的逆过程。

### 实现

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
## 109**

有序链表转换二叉搜索

### 描述

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
### 分析

思路1：将链表转化为数组，那么该题目就转化成了[108](#108)题了

思路2：使用快慢指针，不断搜索中间节点，注意记得还要使用一个pre指针，指向慢指针的前一个节点，用于将**前半部分链表的结尾置空**。

### 实现

思路1：

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
思路2：

```java
public TreeNode sortedListToBST(ListNode head) {
        if(head==null){
            return null;
        }
        if(head.next == null)
            return new TreeNode(head.val);
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = new ListNode(0);
        while (fast!=null&&fast.next!=null){
            pre=slow;
            slow=slow.next;
            fast=fast.next.next;
            
        }
        TreeNode node = new TreeNode(slow.val);
        node.right = sortedListToBST(slow.next);
        pre.next=null;
        node.left = sortedListToBST(head);
        return node;
    }
```



## 230

二叉搜索树中第K小的元素

### 描述

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

### 思路

利用**中序遍历**的结果为从小到大排列的性质。

### 实现

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

## 530

二叉搜索树的最小绝对差

### 描述

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

### 分析

使用二叉搜索树的中序遍历的结果为从小到大的排序的性质，任意两个节点之间差的绝对值的最小值只会出现在相邻的两次节点中，所以在中序遍历的过程中使用一个变量min来记录相邻节点之间差的绝对值的最小值即可。

### 实现

```java
TreeNode pre=null;
    int min=Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return min;
    }
    private void dfs(TreeNode root){
        if(root==null)
            return;
        dfs(root.left);
        if(pre!=null){
            min=Math.min(min,Math.abs(pre.val-root.val));
        }
        pre=root;
        dfs(root.right);
    }
```

## 99

恢复二叉搜索树

### 描述

二叉搜索树中的**两个节点被错误地交换**。

请在不改变其结构的情况下，恢复这棵树。

示例 1:

```
输入: [1,3,null,null,2]

   1
  /
 3
  \
   2

输出: [3,1,null,null,2]

   3
  /
 1
  \
   2
示例 2:

输入: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

输出: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
```

进阶:

使用 O(n) 空间复杂度的解法很容易实现。
你能想出一个只使用常数空间的解决方案吗？

### 分析

利用二叉搜索树中序遍历的性质：

例如：

   ```
    3
   / \
  4   1
   \
    2
   ```

其中序遍历的结果为[4,2,3,1]，我们只需要将3和2交换即可。

那么：

- 第一个节点，是第一个按照中序遍历时候**前一个节点大于后一个节点**,我们选取**前一个节点**,这里指节点4;

- 第二个节点,是在第一个节点找到之后，后面出现**前一个节点大于后一个节点**，我们选择**后一个节点**,这里指节点1;

### 实现

```java
	TreeNode pre,first,second;
    public void recoverTree(TreeNode root) {
        dfs(root);
        int t=first.val;
        first.val=second.val;
        second.val=t;
    }
    private void dfs(TreeNode root){
        if(root==null)
            return;
        dfs(root.left);
        if(pre!=null&&pre.val>root.val){
            if(first==null){
                first=pre;
            }
            second=root;
        }
        pre=root;
        dfs(root.right);
    }
```

## 173

### 描述

实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。

调用 next() 将返回二叉搜索树中的下一个最小的数。

 

示例：

![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/25/bst-tree.png)



```
BSTIterator iterator = new BSTIterator(root);
iterator.next();    // 返回 3
iterator.next();    // 返回 7
iterator.hasNext(); // 返回 true
iterator.next();    // 返回 9
iterator.hasNext(); // 返回 true
iterator.next();    // 返回 15
iterator.hasNext(); // 返回 true
iterator.next();    // 返回 20
iterator.hasNext(); // 返回 false
```


提示：

next() 和 hasNext() 操作的时间复杂度是 O(1)，并**使用 O(h) 内存，其中 h 是树的高度**。
你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。

### 分析

利用中序遍历的性质。

### 实现

空间复杂度为O(n)：

```java
class BSTIterator{
        private List<Integer> data;
        private int nextIndex;

        public BSTIterator(TreeNode root) {
            data=new ArrayList<>();
            inOrder(root);

            nextIndex=0;

        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return nextIndex<data.size();

        }

        /** @return the next smallest number */
        public int next() {
            return data.get(nextIndex++);

        }

        private void inOrder(TreeNode root){
            if (root==null){
                return;
            }
            inOrder(root.left);
            data.add(root.val);
            inOrder(root.right);
        }

    }
```

空间复杂度为O（h），h为树的高度：

```java
class BSTIterator {

    private Stack<TreeNode> stack;
        public BSTIterator(TreeNode root) {
            stack=new Stack<>();
            //按照中序遍历，首先将左边的元素依次压入栈
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /** @return the next smallest number */
    	//每次压入栈的元素都是中序序列中最小的元素
        public int next() {
            TreeNode cur = stack.pop();
            int res=cur.val;
            cur=cur.right;
            while (cur!=null){
                stack.push(cur);
                cur=cur.left;
            }
            return res;
        }
}
```

# 构建二叉树

相关题目：

- [105.从前序与中序遍历序列构造二叉树](#105)
- [106.从中序与后序遍历序列构造二叉树](#106)
- [889.根据先序与后序遍历序列构造二叉树](#889)

## 105

从前序与中序遍历序列构造二叉树

### 描述

根据一棵树的前序遍历与中序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出

前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
返回如下的二叉树：

       3
      / \
      9  20
        /  \
       15   7
###  分析

每次根据前序序列获得当前结点的根节点，然后去中序序列中寻找这个元素，并将中序序列按照该元素划分成左右两个区域。并且，每次利用左右子树的节点数量来确定中序遍历的起始位置。

### 实现

```java
public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
    }
    private TreeNode buildTree(int[] preorder,int[] inorder,int preStart,int preEnd,int inStart,int inEnd){
        if (preStart>preEnd||inStart>inEnd){
            return null;
        }
        int val = preorder[preStart];
        TreeNode root = new TreeNode(val);
        //根节点的下标
        int index=0;
        for (int i = inStart; i <= inEnd; i++) {
            if (val==inorder[i]){
                index=i;
                break;
            }
        }
        //左子树，preorder[preStart+1,preStart+(index-inStart)],inorder[inStart,index-1]
        // (index-inStart):左子树的结点数量
        root.left=buildTree(preorder,inorder,preStart+1,preStart+(index-inStart),inStart,index-1);
        //右子树，
        root.right=buildTree(preorder,inorder,preStart+(index-inStart)+1,preEnd,index+1,inEnd);
        return root;
    }
```

## 106

从中序与后序遍历序列构造二叉树

### 描述

根据一棵树的中序遍历与后序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出

中序遍历 inorder = [9,3,15,20,7]
后序遍历 postorder = [9,15,7,20,3]
返回如下的二叉树：

      3
     / \
      9  20
        /  \
       15   7
### 分析

和[105](#105)是类似的，不过在于处理的指针的变化

### 实现

```java
public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder,postorder,0,inorder.length-1,0,postorder.length-1);
    }
    private TreeNode buildTree(int[] inorder, int[] postorder,int inStart,int inEnd,int postStart,int postEnd){
        if (inStart>inEnd||postStart>postEnd){
            return null;
        }
        int val = postorder[postEnd];
        TreeNode root = new TreeNode(val);
        int index=0;
        for (int i = inStart; i <=inEnd ; i++) {
            if (val==inorder[i]){
                index=i;
                break;
            }
        }
        //(index-inStart):左子树的结点数量
        root.left=buildTree(inorder,postorder,inStart,index-1,postStart,postStart+(index-inStart)-1);
        root.right=buildTree(inorder,postorder,index+1,inEnd,postStart+(index-inStart),postEnd-1);
        return root;
    }
```

## 889*

根据先序与后序遍历序列构造二叉树

### 描述

返回与给定的前序和后序遍历匹配的任何二叉树。

 pre 和 post 遍历中的值是不同的正整数。

 

示例：

输入：pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
输出：[1,2,3,4,5,6,7]


提示：

1 <= pre.length == post.length <= 30
pre[] 和 post[] 都是 1, 2, ..., pre.length 的排列
每个输入保证至少有一个答案。如果有多个答案，可以返回其中一个。

### 分析

根据先、后序序列的性质可知，若存在序列反转的话，则说明此时只存在一边的子树，只有左子树或只有右子树，其它情况均存在左右子树。因为只需要找出一种答案即可，那么这里我设定只有一边子树的情况为只有左子树。

### 实现

```java
public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return build(pre,post,0,pre.length-1,0,post.length-1);
    }
    private TreeNode build(int[] pre,int[] post,int preStart,int preEnd,int postStart,int postEnd){
        if (preStart>preEnd){
            return null;
        }
        
        if (preStart==preEnd){
            return new TreeNode(pre[preStart]);
        }

        int val = pre[preStart];
        TreeNode root = new TreeNode(val);
        //在前序中寻找右子树的范围
        int prePost=preStart;
        for (int i = preStart; i <=preEnd ; i++) {
            if (post[postEnd-1]==pre[i]){
                prePost=i;
                break;
            }
        }

        //在后序中寻找左子树的范围
        int postPost=postStart;
        for (int i = postStart; i <= postEnd; i++) {
            if (pre[preStart+1]==post[i]){
                postPost=i;
                break;
            }
        }
        //这时只含有左子树或只含有右子树
        // 这里设定为只有左子树
        if (pre[prePost]==post[postPost]){
            root.left=build(pre,post,preStart+1,preEnd,postStart,postEnd-1);
        }
        //存在左右子树的时候
        else {
            root.left=build(pre,post,preStart+1,prePost-1,postStart,postPost);
            root.right=build(pre,post,prePost,preEnd,postPost+1,postEnd-1);
        }

        return root;
    }
```

# 更多关于二叉树的问题

相关题目：
   * [114.二叉树展开为链表](#114)
   * [124.二叉树中的最大路径和](#124)
   * [543.二叉树的直径](#543)
   * [617.*合并二叉树](#617)
   * [654.最大二叉树](#654)
   * [863.二叉树中的所有距离为K的结点](#863)
   * [865.具有所有最深结点的最小子树](#865)
   * [872.叶子相似的树](#872)
   * [865.具有所有最深结点的最小子树](#865)
   * [894.所有可能的满二叉树](#894)
### 114
给定一个二叉树，原地将它展开为链表。

例如，给定二叉树
```java
    1
   / \
  2   5
 / \   \
3   4   6
将其展开为：

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
```
- 实现

递归式：
```java
public void flatten(TreeNode root) {
        if(root==null){
            return ;
        }
        TreeNode node=root;
        //不断地将右子树与放入左子树中与根节点相邻的结点的右边
        while (node!=null){
            if (node.left!=null) {
                TreeNode left = node.left;
                //寻找左子树中最右边的结点就是和根节点相邻的结点
                while (left.right != null) {
                    left = left.right;
                }

                //将右子树放入该节点的右侧
                left.right = node.right;
                //将左子树整体放入根节点的右边
                node.right = node.left;
                node.left = null;
            }
            node = node.right;
        }
    }
```
栈+集合的广度遍历：
```java
public void flatten(TreeNode root) {
        List<TreeNode> res = bfs(root);
        for (int i = 0; i < res.size()-1; i++) {
            res.get(i).right=res.get(i+1);
        }

    }

private List<TreeNode> bfs(TreeNode root){
        List<TreeNode> res=new LinkedList<>();
        if (root==null){
            return res;
        }
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        while (!stack.empty()){
            TreeNode node = stack.pop();
            res.add(node);
            if (node.right!=null){
                stack.push(node.right);
            }
            if (node.left!=null){
                stack.push(node.left);
            }
            node.left=null;

        }
        return res;
    }

    
```

## 617

合并二叉树

### 描述

给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。

你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。

示例 1:

```
输入: 
	Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
输出: 
合并后的树:
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7
```

注意: 合并必须从两个树的根节点开始。

### 分析

当两棵树均为空的时候，则合并为空；

当有一棵为空，另一棵不为空的时候，则直接返回不为空的那一棵；

当两棵树都不为空的时候，则生成一个树的结点的值为这两棵树的和。

递归生成其左右子树。

### 实现

```java
public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1==null&&t2==null)
            return null;
        if(t1==null)
            return t2;
        if(t2==null)
            return t1;
        TreeNode root=new TreeNode(t1.val+t2.val);
        root.left=mergeTrees(t1.left,t2.left);
        root.right=mergeTrees(t1.right,t2.right);
        return root;
    }
```







# 参考资料

[玩儿转算法面试 - 课程官方代码仓](https://github.com/liuyubobobo/Play-with-Algorithm-Interview)

