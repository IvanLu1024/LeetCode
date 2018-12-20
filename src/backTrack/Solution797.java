package backTrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Solution797 {
    //典型的回溯法来求解
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> list=new ArrayList<>();
        list.add(0);
        generate(graph,0,list,res);
        return res;
    }

    //生成结果
    private void generate(int[][] graph, int index, List<Integer> list,List<List<Integer>> res){

        //若搜索到终点的时候，此时将可行的解加入集合中
        if (index==graph.length-1){
            res.add(new ArrayList<>(list));
        }
        //从当前位置可达的位置中进行搜索
        for (int i = 0; i <graph[index].length ; i++) {
            int cur = graph[index][i];
            list.add(cur);
            //从当前位置出发进行搜索
            generate(graph,cur,list,res);
            //回溯
            list.remove(list.size()-1);
        }
    }
    @Test
    public void test(){
        int[][] graph={{1,2},{3},{3},{}};
        List<List<Integer>> res = allPathsSourceTarget(graph);
        System.out.println(res);
    }
}
