

<!-- GFM-TOC -->
* [栈和队列部分总结笔记](#栈和队列部分总结笔记)
    * [栈和递归的紧密关系](#栈和递归的紧密关系) 
    * [更多栈的题目](#更多栈的题目)
* [参考资料](#参考资料)
<!-- GFM-TOC -->

# 栈和队列部分总结笔记

## 栈和递归的紧密关系
在递归调用的时候，系统中相当于有一个系统栈，系统栈中要推入信息来保存**递归调用前**的系统的执行情况。
### 递归算法
- 二叉树的算法

相关题目：

* [144](#144)
* [145]()
* [94]()
#### 144
二叉树的前序遍历
- 分析：
1. 解法一：

这题用递归来写十分简单，直接按照前序遍历的逻辑（中->左->右）来实现即可，在此就不赘述。
2. 解法二：

使用迭代式来实现，在这里我们会用到栈这个数据结构。在上文中有提到，递归调用相当于使用了系统栈的结构，那么我们在这里来模拟系统栈的过程来
实现二叉树的前序遍历。每次当函数递归的时候，便将未执行的操作逆序压入栈中，当执行完该操作便做弹栈操作。

递归调用的时候，模拟系统栈的情况如下，下面忽略了结点为null的情况：
>红色：已经遍历过；蓝色：未遍历
* 遍历根结点




![遍历根结点](../pict/stack_01.png)



* 遍历左子树



![遍历左子树](../pict/stack_02.png)

</div>

* 遍历右子树



![遍历右子树](../pict/stack_03.png)


- 实现：
```java
/**
* 额外定义了一个操作类，用来模拟系统栈的操作过程
* 每次执行只有两种操作，一是将结点的值加入集合，二是继续遍历结点
*/
 class Command{
        int command;//0:表示继续遍历；1:表示加入集合
        TreeNode node;

        public Command(int command, TreeNode node) {
            this.command = command;
            this.node = node;
        }
    }
//迭代式
    public List<Integer> preorderTraversal(TreeNode root){
        if (root==null){
            return res;
        }
        Stack<Command> stack=new Stack<>();
        stack.push(new Command(0,root));
        while (!stack.empty()){
            Command command = stack.pop();
            if (command.command==1){
                res.add(command.node.val);
            }
            //以操作的逆序压入操作类
            else {
                if (command.node.right!=null){
                    stack.push(new Command(0,command.node.right));
                }
                if (command.node.left!=null){
                    stack.push(new Command(0,command.node.left));
                }
                stack.push(new Command(1,command.node));
            }
        }
        return res;

    }
```
>这样不管是二叉树的前序、中序还是后序遍历的实现，只需要和递归一样调换操作的次序就可以方便的转化，更便于理解。题144和94同理即可得，下面不赘述其过程。
### 145
二叉树的后序遍历

给定一个二叉树，返回它的 后序 遍历。

示例:
```java


输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

输出: [3,2,1]

```
进阶: 递归算法很简单，你可以通过迭代算法完成吗？

- 实现：
递归解法：
```java
private List<Integer> res=new ArrayList<>();
    //递归式
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root!=null){
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            res.add(root.val);
        }
        return res;
    }
```
非递归解法：
```java
    class Command{
        int command;//0:表示继续遍历；1:表示加入集合
        TreeNode node;

        public Command(int command, TreeNode node) {
            this.command = command;
            this.node = node;
        }
    }

//迭代式
    private List<Integer> res=new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root){
        if (root==null){
            return res;
        }
        Stack<Command> stack=new Stack<>();
        stack.push(new Command(0,root));
        while (!stack.empty()){
            Command command = stack.pop();
            if (command.command==1){
                res.add(command.node.val);
            }else {
                stack.push(new Command(1,command.node));
                if (command.node.right!=null){
                    stack.push(new Command(0,command.node.right));
                }
                if (command.node.left!=null){
                    stack.push(new Command(0,command.node.left));
                }
            }
        }
        return res;

    }
```

##更多栈的题目

相关题目：
   * [20.有效的括号](#20)            
   * [71.简化路径](#71)
   * [84.]()
   * [133.]()
   * [150.]()            
   * [227.]()
   * [856.]()
   * [901.]()
### 20
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

示例 1:

输入: "()"
输出: true

示例 2:

输入: "()[]{}"
输出: true

- 分析：
使用一个栈，从头遍历这个字符串，当遍历到左括号的时候，将其入栈；而当遍历到右括号的时候，将栈顶出栈，
如果当前的栈为空表示右括号没有可以匹配的左括号，当栈顶元素与当前的括号不匹配的时候（这里指的是括号的一对一的关系，大，中，小括号，左右的一对一关系），
则表明当前字符串无效。如果遍历结束，栈为空，此时说明每一个括号都是匹配的，则返回true。

- 实现：
```java
public boolean isValid(String s) {
        if (s==null||s.length()==0){
            return true;
        }
        char[] chars = s.toCharArray();
        Stack<Character> stack=new Stack<>();
        for (char c : chars) {
            if (c=='('||c=='['||c=='{'){
                stack.push(c);
            }else {
                if (stack.empty()){
                    return false;
                }
                Character match = stack.pop();
                switch (match){
                    case '(':match=')';break;
                    case '[':match=']';break;
                    case '{':match='}';break;
                }
                if (c!=match){
                    return false;
                }
            }
        }
        if (!stack.empty()){
            return false;
        }
        return true;

    }
```
### 71
简化路径

给定一个文档 (Unix-style) 的完全路径，请进行路径简化。

例如，
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"

边界情况:

你是否考虑了 路径 = "/../" 的情况？

在这种情况下，你需返回 "/" 。

此外，路径中也可能包含多个斜杠 '/' ，如 "/home//foo/" 。

在这种情况下，你可忽略多余的斜杠，返回 "/home/foo" 。

- 分析：

使用一个LinkedList来模拟栈，因为为了方便最后的顺序遍历。

首先将路径（字符串）按照/来切分， 接着遍历数组，如果遇到..，则将栈顶元素弹出。遍历的过程中，如果遇到
""或者"."则直接跳过。最后，按照顺序遍历这个栈，用/分割，重新构建一个新的字符串。

- 实现：
```java
public String simplifyPath(String path) {
        if (path==null||path.length()==0){
            return null;
        }
        StringBuilder res=new StringBuilder("/");
        String[] paths = path.split("/");
        //为了方便遍历结果使用链表来模拟栈的结构
        LinkedList<String> stack=new LinkedList<>();
        for (int i = 0; i < paths.length; i++) {
            //..：表示上一级目录，此时需要弹栈，返回上一级目录
            if (paths[i].equals("..")){
                if (!stack.isEmpty()){
                    stack.removeLast();
                }
            }
            //跳过路径名为//（重复的/），和路径名为.
            else if (!paths[i].equals("")&&!paths[i].equals(".")){
                stack.add(paths[i]);
            }
        }
        for (int i = 0; i < stack.size(); i++) {
            if (i!=stack.size()-1){
                res.append(stack.get(i)+"/");
            }else {
                res.append(stack.get(i));
            }
        }
        return res.toString();

    }
```
### 84
给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。

示例:

输入: [2,1,5,6,2,3]
输出: 10

分析：

用栈来维护一个递增序列，当遍历到比栈顶元素的高度大的时候，将该元素入栈；反之，将栈顶出栈，计算构成的矩形面积。

实现：
```java
    //基本思想就是，将数值压栈，直到这些数值代表的高度的柱子不能向右再扩展长度了，
    // 就计算其面积大小，pop出来，留下还可以拓展的，一直到最后
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (n==0){
            return 0;
        }
        if (n==1){
            return heights[0];
        }
        //栈来记录从栈顶到栈底递增序列的下标
        Stack<Integer> stack = new Stack<>();
        int res=0;
        //处理的柱子宽度为0-n
        for (int i = 0; i <= n; i++) {
            //为了避免数组越界，将高度进行了统一处理
            int num=i==n?0:heights[i];
            //如果当前遍历的栈为空或者高度大于或等于栈顶元素的值的时候，入栈
            if (stack.empty()||num>=heights[stack.peek()]){
                stack.push(i);
            }//当前遍历的高度小于栈顶的值的时候，出栈
            else {
                int top = stack.pop();
                //计算当前的宽度
                //当栈为空的时候表明，当前高度为最小高度
                int w = stack.empty()?i:i-1-stack.peek();
                //记录最大面积
                res=Math.max(res,w*heights[top]);

                //为了从当前位置继续开始
                i--;
            }
        }
        return res;
    }
```
### 133
克隆图

克隆一张无向图，图中的每个节点包含一个 label （标签）和一个 neighbors （邻接点）列表 。

OJ的无向图序列化：

节点被唯一标记。

我们用 # 作为每个节点的分隔符，用 , 作为节点标签和邻接点的分隔符。

例如，序列化无向图 {0,1,2#1,2#2,2}。

该图总共有三个节点, 被两个分隔符  # 分为三部分。 

第一个节点的标签为 0，存在从节点 0 到节点 1 和节点 2 的两条边。
第二个节点的标签为 1，存在从节点 1 到节点 2 的一条边。
第三个节点的标签为 2，存在从节点 2 到节点 2 (本身) 的一条边，从而形成自环。
我们将图形可视化如下：
```java
       1
      / \
     /   \
    0 --- 2
         / \
         \_/
```

- 实现：
```java
class UndirectedGraphNode {
          int label;
          List<UndirectedGraphNode> neighbors;
          UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
      }

    //实现深拷贝
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node==null){
            return null;
        }
        UndirectedGraphNode ret = new UndirectedGraphNode(node.label);
        //使用栈来遍历原始的结点
        Stack<UndirectedGraphNode> stack=new Stack<>();
        //K：原始结点；V：复制生成的新结点
        Map<UndirectedGraphNode,UndirectedGraphNode> nodeMap=new HashMap<>();
        stack.push(node);
        nodeMap.put(node,ret);
        while (!stack.empty()){
            UndirectedGraphNode cur = stack.pop();
            //遍历邻居结点，克隆邻居结点
            for (UndirectedGraphNode next:cur.neighbors){
                if (nodeMap.get(next)==null){
                    //克隆结点
                    UndirectedGraphNode graphNode = new UndirectedGraphNode(next.label);
                    nodeMap.put(next,graphNode);
                    stack.push(next);
                }
                //将克隆的邻居结点加入该结点的邻居集合中
                nodeMap.get(cur).neighbors.add(nodeMap.get(next));
            }

        }
        return ret;
    }
```

### 150

### 227

### 856

### 901
    
    

# 参考资料
[玩儿转算法面试 - 课程官方代码仓](https://github.com/liuyubobobo/Play-with-Algorithm-Interview)

