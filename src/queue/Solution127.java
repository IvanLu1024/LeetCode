package queue;

import org.junit.Test;

import java.util.*;

/**
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：

     每次转换只能改变一个字母。
     转换过程中的中间单词必须是字典中的单词。
     说明:

     如果不存在这样的转换序列，返回 0。
     所有单词具有相同的长度。
     所有单词只由小写字母组成。
     字典中不存在重复的单词。
     你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
     示例 1:

     输入:
     beginWord = "hit",
     endWord = "cog",
     wordList = ["hot","dot","dog","lot","log","cog"]

     输出: 5

     解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     返回它的长度 5。
     示例 2:

     输入:
     beginWord = "hit"
     endWord = "cog"
     wordList = ["hot","dot","dog","lot","log"]

     输出: 0

     解释: endWord "cog" 不在字典中，所以无法进行转换。
 *
 */
public class Solution127 {

    //使用BFS（广度遍历）的方法
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict=new HashSet<>();
        for (String word : wordList) {
            dict.add(word);
        }
        if (!dict.contains(endWord)){
            return 0;
        }
        Queue<String> queue=new LinkedList<>();
        queue.offer(beginWord);
        int level=0;    //层数
        while (!queue.isEmpty()){
            level++;
            //记录这一层的大小
            int size = queue.size();
            //遍历这一层
            while (size-->0){
                String word = queue.poll();
                char[] chars = word.toCharArray();
                //分别变换单词的每一个字母搜索字典中的单词
                for (int i = 0; i < chars.length; i++) {
                    //记录初始值
                    char ch = chars[i];
                    //每一个字母都从a-z变换搜索
                    for (char c = 'a'; c <= 'z'; ++c) {
                        if (c == ch) continue;  //出现相同的单词就跳过
                        chars[i] = c;
                        String t = new String(chars);
                        if (t.equals(endWord)) return level + 1;    //搜索到目标，步数为层数+1
                        //查找到字典中的单词
                        if (dict.contains(t))
                        {
                        dict.remove(t);     //将字典中该单词删除，避免重复查找
                        queue.offer(t);     //将该单词入队
                        }
                    }
                    //还原初始值
                    chars[i]=ch;
                }
            }
        }
        return 0;

    }

    //双向BFS
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        //首先建立一个字典，将每个单词放入其中
        Set<String> dict=new HashSet<>();
        for (String word : wordList) {
            dict.add(word);
        }
        if (!dict.contains(endWord)){
            return 0;
        }
        //建立头尾队列
        Set<String> beginSet=new HashSet<>();
        Set<String> endSet=new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);

        int level=0;
        //遍历队列
        while (!beginSet.isEmpty()&&!endSet.isEmpty()){
            level++;
            //确保头集合较小，保持平衡
            if (beginSet.size()>endSet.size()){
                Set<String> t=endSet;
                endSet=beginSet;
                beginSet=t;
            }

            //建立一个临时集合
            Set<String> temp=new HashSet<>();


            for (String word : beginSet) {
                //寻找邻居节点
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char ch=chars[i];
                    for (char j = 'a'; j <='z' ; j++) {
                        chars[i]=j;
                        String s = new String(chars);
                        if (endSet.contains(s)){
                            return level+1;
                        }
                        if (dict.contains(s)){
                            dict.remove(s);
                            temp.add(s);
                        }
                    }
                    chars[i]=ch;
                }
            }
            beginSet=temp;

        }
        return 0;


    }
    @Test
    public void test(){
        String[] arr={"hot","dot","dog","lot","log"};
        List<String> wordList = Arrays.asList(arr);
        String bWord="hit";
        String eWord="cog";
        int step = ladderLength1(bWord, eWord, wordList);
        System.out.println(step);
    }

}
