package queue;

import org.junit.Test;

import java.util.*;

/**
 * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
 *
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 *
 * 如果不存在这样的转换序列，返回一个空列表。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 *
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * 输出:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 * 示例 2:
 *
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * 输出: []
 *
 * 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
 *
 */
public class Solution126 {


    //使用BFS（广度遍历）的方法
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        Set<String> dict=new HashSet<>(wordList);
        List<List<String>> res=new ArrayList<>();
        //用于记录当前路径的前驱结点
        Map<String,List<String>> nodeNeighbors=new HashMap<>();
        //用于记录到达当前位置所需要的步数的map
        Map<String,Integer> steps=new HashMap<>();
        ArrayList<String> solution=new ArrayList<>();

        dict.add(beginWord);
        //寻找目标
        bfs(beginWord,endWord,dict,nodeNeighbors,steps);
        //寻找可行路径
        dfs(beginWord,endWord,dict,nodeNeighbors,steps,solution,res);

        return res;
    }

    //通过DFS输出最短路径
    private void dfs(String cur, String endWord, Set<String> dict, Map<String,List<String>> nodeNeighbors, Map<String,Integer> steps, ArrayList<String> solution, List<List<String>> res) {
        solution.add(cur);
        if (endWord.equals(cur)){
            res.add(new ArrayList<>(solution));
        }else {
            for (String next:nodeNeighbors.get(cur)){
                if (steps.get(next)==steps.get(cur)+1){
                    dfs(next,endWord,dict,nodeNeighbors,steps,solution,res);
                }
            }
        }
        solution.remove(solution.size()-1);

    }

    //通过BFS寻找到目标
    private void bfs(String beginWord, String endWord, Set<String> dict, Map<String,List<String>> nodeNeighbors, Map<String,Integer> steps) {
        for (String str:dict){
            nodeNeighbors.put(str,new ArrayList<>());
        }
        steps.put(beginWord,0);
        Queue<String> queue=new LinkedList<>();
        queue.offer(beginWord);

        //当队列不空并且没有找到目标的时候，进入循环
        while (!queue.isEmpty()){
            boolean found=false;
            //记录这一层的大小
            int size = queue.size();
            //遍历这一层
            while (size-->0){
                String cur = queue.poll();
                Integer curDistance = steps.get(cur);
                ArrayList<String> neighbors = getNeighbors(cur, dict);

                for (String neighbor:neighbors){
                    nodeNeighbors.get(cur).add(neighbor);
                    if (!steps.containsKey(neighbor)){
                        steps.put(neighbor,curDistance+1);
                        if (endWord.equals(neighbor)){
                            found=true;
                        }else {
                            queue.offer(neighbor);
                        }
                    }
                }
                if (found)
                    break;
            }
        }
    }

    //寻找当前结点的相邻结点的集合
    private ArrayList<String> getNeighbors(String node, Set<String> dict) {
        ArrayList<String> res=new ArrayList<>();
        char[] chars = node.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char t = chars[i];
            for (char c='a';c<='z';c++){
                if (c==chars[i])continue;
                chars[i]=c;
                if (dict.contains(String.valueOf(chars))){
                    res.add(String.valueOf(chars));
                }
            }
            chars[i]=t;
        }
        return res;
    }


    @Test
    public void test(){
        String[] arr={"hot","dot","dog","lot","log"};
        List<String> wordList = Arrays.asList(arr);
        String bWord="hit";
        String eWord="cog";
        List<List<String>> res = findLadders(bWord, eWord, wordList);
        System.out.println(res);
    }

}
