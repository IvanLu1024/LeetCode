package stackAndQueue;

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
        int step=0;
        while (!queue.isEmpty()){
            step++;
            int size = queue.size();
            while (size-->0){
                String word = queue.poll();
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char ch = chars[i];
                    for (char c = 'a'; c <= 'z'; ++c) {
                        if (c == ch) continue;
                        chars[i] = c;
                        String t = new String(chars);
                        if (t.equals(endWord)) return step + 1;
                        if (dict.contains(t))
                        {
                        dict.remove(t);
                        queue.offer(t);
                        }
                    }
                    chars[i]=ch;
                }

            }
        }

        return 0;

    }
    @Test
    public void test(){
        String[] arr={"hot","dot","dog","lot","log","cog"};
        List<String> wordList = Arrays.asList(arr);
        String bWord="hit";
        String eWord="cog";
        int step = ladderLength(bWord, eWord, wordList);
        System.out.println(step);
    }

}
