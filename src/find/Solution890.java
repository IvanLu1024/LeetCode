package find;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 你有一个单词列表 words 和一个模式  pattern，你想知道 words 中的哪些单词与模式匹配。

 如果存在字母的排列 p ，使得将模式中的每个字母 x 替换为 p(x) 之后，我们就得到了所需的单词，那么单词与模式是匹配的。

 （回想一下，字母的排列是从字母到字母的双射：每个字母映射到另一个字母，没有两个字母映射到同一个字母。）

 返回 words 中与给定模式匹配的单词列表。

 你可以按任何顺序返回答案。



 示例：

 输入：words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 输出：["mee","aqq"]
 解释：
 "mee" 与模式匹配，因为存在排列 {a -> m, b -> e, ...}。
 "ccc" 与模式不匹配，因为 {a -> c, b -> c, ...} 不是排列。
 因为 a 和 b 映射到同一个字母。


 提示：

 1 <= words.length <= 50
 1 <= pattern.length = words[i].length <= 20

 *
 */
public class Solution890 {
    //双哈希表的解法，与290题相类似
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res=new ArrayList<>();
        if (words==null||words.length==0){
            return res;
        }
        for (String word:words){
            if (isPattern(word,pattern)){
                res.add(word);
            }
        }
        return res;
    }
    //判断单词和模式是否匹配
    private boolean isPattern(String word,String pattern){
            //为了得到一对一的关系需要使用两个map来实现
            //K:word,V:pattern
            Map<Character,Character> wMap = new HashMap<>();
            //K:pattern,V:word
            Map<Character,Character> pMap = new HashMap<>();
            if (word.length()!=pattern.length()){
                return false;
            }
            char[] pChars = pattern.toCharArray();
            char[] wChars = word.toCharArray();
            for (int i = 0; i < word.length(); i++) {
            if (!wMap.containsKey(wChars[i])){
                //当word不存在而pattern存在的时候，说明此时不能匹配
                if (pMap.containsKey(pChars[i])){
                    return false;
                }
                //分别放入两个map中
                wMap.put(wChars[i],pChars[i]);
                pMap.put(pChars[i],wChars[i]);
            }else {
                //wMap.containsKey(wChars[i])=true
                if (!pMap.containsKey(pChars[i])){
                    return false;
                }
                //当不能一一匹配的时候
                if (pMap.get(pChars[i])!=wChars[i]||wMap.get(wChars[i])!=pChars[i]){
                    return false;
                }
            }
        }
        return true;
    }
    @Test
    public void test(){
        String[] words={"abc","deq","mee","aqq","dkd","ccc"};
        String pattern="abb";
        List<String> list = findAndReplacePattern(words, pattern);
        System.out.println(list);
    }
}
