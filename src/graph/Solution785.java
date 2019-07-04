package graph;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Ivan 14:15
 * @Description TODO
 */
public class Solution785 {

    //将一条边上的两个端点着上不同的颜色，若能确保每一对端点都是不同的颜色，那么就是二分图
    int[] colors;//0:未上色；1：红色；-1：黑色

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        colors=new int[n];
        for (int i = 0; i < n; i++) {
            if (colors[i]==0&&!dfs(graph,i,1)){
                return false;
            }
        }
        return true;
    }

    //判断相邻节点颜色是否不相同
    private boolean dfs(int[][] graph,int cur,int curColor){
        //若与预期上色不一致就说明不是二分图
        if (colors[cur]!=0){
            return curColor==colors[cur];
        }
        colors[cur]=curColor;
        for (int next:graph[cur]){
            if (!dfs(graph,next,-curColor)){
                return false;
            }
        }
        return true;
    }

    //BFS解法
    public boolean isBipartite1(int[][] graph){
        int n = graph.length;
        colors=new int[n];
        //依次判断每一对邻边的着色情况
        for (int i = 0; i < n; i++) {
            if (colors[i]==0&&!bfs(graph,i)){
                return false;
            }
        }
        return true;

    }

    //判断相邻节点颜色是否不相同
    private boolean bfs(int[][] graph,int cur){
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(cur);
        colors[cur]=1;
        while (!queue.isEmpty()){
            int curNode = queue.poll();
            int curColor=colors[curNode];
            for(int next:graph[curNode]){
                if (colors[next]==0){
                    colors[next]=-curColor;
                    queue.offer(next);
                }else if (colors[next]==curColor){
                    return false;
                }
            }
        }
        return true;
    }
    @Test
    public void test(){
        /*[1,3], [0,2], [1,3], [0,2]*/
        /*[[1,2,3], [0,2], [0,1,3], [0,2]]*/
        /*[[],[2,4,6],[1,4,8,9],[7,8],[1,2,8,9],[6,9],[1,5,7,8,9],[3,6,9],[2,3,4,6,9],[2,4,5,6,7,8]]*/
        int[][] graph={{1,2,3},{0,2},{0,1,3},{0,2}};
        boolean v = isBipartite1(graph);
        System.out.println(v);
    }
}
