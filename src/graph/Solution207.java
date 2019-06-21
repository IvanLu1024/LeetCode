package graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Ivan 15:03
 * @Description TODO
 */
public class Solution207 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //将prerequisites构建成邻接表
        List<Integer>[] graph=new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i]=new ArrayList<>();
        }
        for (int[] pre:prerequisites){
            graph[pre[0]].add(pre[1]);
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
        if (flag[curNode]==1){
            return true;
        }
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

    @Test
    public void test(){
        int num=2;
        int[][] pres={{0,1}};
        boolean b = canFinish(num, pres);
        System.out.println(b);
    }
    
}
