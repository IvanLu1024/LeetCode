package graph;

import org.junit.Test;

import java.util.*;

/**
 * @Author Ivan 15:03
 * @Description TODO
 */
public class Solution210 {

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

    public int[] findOrder1(int numCourses, int[][] prerequisites){
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


    @Test
    public void test(){
        int num=4;
        //[1,0],[2,0],[3,1],[3,2]
        int[][] pres={{1,0},{2,0},{3,1},{3,2}};
        int[] order = findOrder1(num, pres);
        for (int i = 0; i < order.length; i++) {
            System.out.print(order[i]+",");
        }
    }
    
}
