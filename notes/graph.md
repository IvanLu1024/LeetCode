# 拓扑排序

在图论中，拓扑排序（Topological Sorting）是一个**有向无环图**（DAG, Directed Acyclic Graph）的所有顶点的线性序列。且该序列必须满足下面两个条件：

- 每个顶点出现且**只出现一次**。
- 若存在一条从顶点 A 到顶点 B 的路径，那么在序列中顶点 A 出现在顶点 B 的**前面**。

有向无环图（DAG）才有拓扑排序，非DAG图没有拓扑排序一说。拓扑排序的过程如下：

1. 从 DAG 图中选择一个 没有前驱（即入度为0）的顶点并输出。
2. 从图中删除该顶点和所有以它为起点的**有向边**。
3. 重复 1 和 2 直到当前的 DAG 图为空或**当前图中不存在无前驱的顶点为止**。后一种情况说明有向图中必然存在环。

通常一个有向无环图的拓扑排序的结果是不唯一的。

## 实现

Kahn算法，该算法的关键**在于需要维护一个入度为0的顶点的集合**：

每次从该集合中取出(如果要求输出时编号小的在前，可以用优先队列保存集合)一个顶点，将该顶点放入保存结果的List中。

紧接着循环遍历由该顶点引出的所有边，从图中移除这条边，同时获取该边的另外一个顶点，如果该顶点的入度在减去本条边之后为0，那么也将这个顶点放到入度为0的集合中。然后继续从集合中取出一个顶点…………

当集合为空之后，检查图中是否还存在任何边，**如果存在的话，说明图中至少存在一条环路**。不存在的话则返回结果List，此List中的顺序就是对图进行拓扑排序的结果。

有向图：使用邻接表表示

```java
public class Digraph implements Graph {

    private int n;  //节点数
    private int m;  //边数
    private Vector<Integer>[]  g;     //图的具体数据

    public Digraph(int n, boolean directed) {
        assert n>=0;
        this.n = n;
        this.m = 0;
        g = new Vector[n];
        for (int i = 0; i < n; i++) {
            g[i] = new Vector<>();
        }
    }

    public int V(){return n;}   //返回节点个数
    public int E(){return m;}   //返回边数个数

    public void addEdge(int v,int w){
        assert v>=0&&v<n;
        assert w>=0&&w<n;

        g[v].add(w);
        m++;

    }
    public boolean hasEdge(int v,int w){
        assert v>=0&&v<n;
        assert w>=0&&w<n;

        for (int i = 0; i < g[v].size(); i++) {
            if (g[v].elementAt(i)==w){
                return true;
            }
        }
        return false;
    }

    // 返回图中一个顶点的所有邻边
    // 由于java使用引用机制，返回一个Vector不会带来额外开销
    public Iterable<Integer> adj(int v){
        assert v>=0&&v<n;
        return g[v];
    }

    public Vector<Integer>[] getG() {
        return g;
    }

    @Override
    public void show() {
        for( int i = 0 ; i < n ; i ++ ){
            System.out.print("vertex " + i + ":\t");
            for( int j = 0 ; j < g[i].size() ; j ++ )
                System.out.print(g[i].elementAt(j) + "\t");
            System.out.println();
        }
    }
}
```

拓扑排序使用Kahn算法的实现（相当于BFS）：

```java
public class KahnTopologicalSort {

    //拓扑序列
    private List<Integer> order;
    //入度为0的集合
    private Queue<Integer> setOfZeroIndegree;
    //存储每个节点的入度
    private int[] indegrees;
    private int edges;
    private Digraph graph;

    //有向图使用邻接表来表示
    public KahnTopologicalSort(Digraph graph) {
        this.graph = graph;
        this.edges = graph.E();
        this.indegrees = new int[graph.V()];
        order = new ArrayList<>();
        setOfZeroIndegree = new LinkedList<>();

        //对入度为0的集合进行初始化
        Vector<Integer>[] g = graph.getG();
        //将邻接表中的每一条[a->b]中的b添加入度
        for (int i = 0; i < g.length; i++) {
            for (int j:g[i]){
                indegrees[j]++;
            }
        }
        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i]==0){
                setOfZeroIndegree.offer(i);
            }
        }
        generateOrder();
    }

    //生成拓扑序列
    private void generateOrder(){
        while (!setOfZeroIndegree.isEmpty()){
            //从入度为0的集合中取出一个节点
            Integer curNode = setOfZeroIndegree.poll();
            order.add(curNode);
            //遍历该节点的邻居节点
            for (int i:graph.adj(curNode)){
                edges--;
                indegrees[i]--;
                if (indegrees[i]==0){
                    setOfZeroIndegree.offer(i);
                }
            }
        }
        //说明没有遍历完所有节点，证明有环
        if (edges!=0){
            order=new ArrayList<>();
        }
    }

    public List<Integer> getOrder(){
        return order;
    }
}
```

使用深度遍历：

```java
public class DfsTopologicalSort {

    //拓扑序列
    private List<Integer> order;

    //入队序列需要逆序才是拓扑序列
    private Stack<Integer> stack;

    //标记各节点的状态
    // -1：visited;0:none-visited;1:visiting
    private int[] flag;

    private Digraph graph;

    public DfsTopologicalSort(Digraph graph) {
        this.graph = graph;
        order=new ArrayList<>();
        stack=new Stack<>();
        flag=new int[graph.V()];

        //生成拓扑序列
        for (int i = 0; i < graph.V(); i++) {
            if (dfs(graph,i)){
                order.clear();
            }
        }
        while (!stack.empty()){
            order.add(stack.pop());
        }
    }

    /**
     * 深度优先遍历
     * @param graph
     * @param curNode
     * @return 是否存在环路
     */
    private boolean dfs(Digraph graph,int curNode){
        //此时说明存在环路
        if (flag[curNode]==1){
            return true;
        }
        //当前的节点已被遍历过
        if (flag[curNode]==-1){
            return false;
        }
        flag[curNode]=1;
        //继续遍历当前节点的邻居节点
        for (int i:graph.adj(curNode)){
            if (dfs(graph,i)){
                return true;
            }
        }
        stack.push(curNode);
        flag[curNode]=-1;
        return false;
    }

    public List<Integer> getOrder(){
        return order;
    }
}
```

## 207.课程表

### 描述

现在你总共有 n 门课需要选，记为 0 到 n-1。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]

给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？

示例 1:

输入: 2, [[1,0]] 
输出: true
解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。

示例 2:

输入: 2, [[1,0],[0,1]]
输出: false
解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。

说明:

输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
你可以假定输入的先决条件中没有重复的边。
提示:

这个问题相当于**查找一个循环是否存在于有向图中**。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。

### 分析

将这些课程的依赖关系表示成有向图的实行，若有向图中存在环路，则说明该图不是有向无环图（DAG），那么就不存在拓扑排序，因此不可能选取所有课程。所以，只需要在图遍历的过程中判断是否存在环路即可。

### 实现

DFS:

```java
public boolean canFinish(int numCourses, int[][] prerequisites) {
        //将prerequisites构建成邻接表
        List<Integer>[] graph=new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i]=new ArrayList<>();
        }
        for (int[] pre:prerequisites){
            graph[pre[1]].add(pre[0]);
        }
        //设置标记数组
        //0:none-visited;1:visiting;-1:visited
        int[] flag=new int[numCourses];

        //深度遍历 dfs
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(graph,flag,i)){
                return false;
            }
        }
        return true;
    }

    private boolean hasCycle(List<Integer>[] graph,int[] flag,int curNode){
        //在本次遍历过程中，表示存在环路
        if (flag[curNode]==1){
            return true;
        }
        //表示已经遍历过，已经遍历过的节点是必然不存在环路的
        if (flag[curNode]==-1){
            return false;
        }
        flag[curNode]=1;
        for (int i:graph[curNode]){
            if (hasCycle(graph,flag,i)){
                return true;
            }
        }
        flag[curNode]=-1;
        return false;
    }
```

## 210.课程表（2）

### 描述

现在你总共有 n 门课需要选，记为 0 到 n-1。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]

给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。

可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。

示例 1:

输入: 2, [[1,0]] 
输出: [0,1]
解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。

示例 2:

输入: 4, [[1,0],[2,0],[3,1],[3,2]]
输出: [0,1,2,3] or [0,2,1,3]
解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。

说明:

输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
你可以假定输入的先决条件中没有重复的边。
提示:

这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。

通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
拓扑排序也可以通过 BFS 完成。

### 分析

典型的拓扑排序的应用

### 实现

DFS:

```java
public int[] findOrder(int numCourses, int[][] prerequisites) {
        //构建邻接表
        List<Integer>[] graph=new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i]=new ArrayList<>();
        }
        //由于[0,1]表示想要学习课程 0 ，你需要先完成课程 1，即1->0，那么pre[1]是pre[0]的先序关系
        for (int[] pre:prerequisites){
            graph[pre[1]].add(pre[0]);
        }
        int[] flag=new int[numCourses];
        Stack<Integer> order=new Stack<>();
        int[] res=new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (dfs(graph,flag,i,order)){
                return new int[0];
            }
        }
        int i=0;
        while (!order.empty()){
            res[i++]=order.pop();
        }
        return res;
    }

    private boolean dfs(List<Integer>[] graph, int[] flag, int curNode,Stack<Integer> order){
        if (flag[curNode]==1){
            return true;
        }
        if (flag[curNode]==-1){
            return false;
        }
        flag[curNode]=1;
        for (int i:graph[curNode]){
            if (dfs(graph,flag,i,order)){
                return true;
            }
        }
        order.push(curNode);
        flag[curNode]=-1;
        return false;
    }
```

BFS:

```java
public int[] findOrder(int numCourses, int[][] prerequisites){
        int[] res=new int[numCourses];
        List<Integer> order=new ArrayList<>();
        int[] indegrees=new int[numCourses];
        //存储入度为0的节点
        Queue<Integer> queue=new LinkedList<>();
        List<Integer>[] graph=new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i]=new ArrayList<>();
        }
        for (int[] pre:prerequisites){
            graph[pre[1]].add(pre[0]);
            //将终点的入度增加
            indegrees[pre[0]]++;
        }
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i]==0){
                queue.offer(i);
            }
        }
        //BFS
        while (!queue.isEmpty()){
            Integer cur = queue.poll();
            order.add(cur);
            for (int i:graph[cur]){
                indegrees[i]--;
                if (indegrees[i]==0){
                    queue.offer(i);
                }
            }
        }
        if (order.size()!=numCourses){
            return new int[0];
        }
        for (int i = 0; i < numCourses; i++) {
            res[i]=order.get(i);
        }
        return res;

    }
```







# 参考资料

- <https://blog.csdn.net/dm_vincent/article/details/7714519>
- 