package find;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一种 pattern(模式) 和一个字符串 str ，判断 str 是否遵循相同的模式。
 *
 * 这里的遵循指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应模式。
 *
 * 示例1:
 *
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 *
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 *
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 示例 4:
 *
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 */
public class Solution290 {
    public boolean wordPattern(String pattern, String str) {
        String[] strs = str.split(" ");
        if (strs.length!=pattern.length()){
            return false;
        }
        Map<Character,String> pMap=new HashMap<>();
        Map<String,Character> sMap=new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (!pMap.containsKey(c)){
                if (sMap.containsKey(strs[i])){
                    return false;
                }
                pMap.put(c,strs[i]);
                sMap.put(strs[i],c);
            }else {
                String s = pMap.get(c);
                if (!s.equals(strs[i])){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean wordPattern1(String pattern, String str){
        String[] strs = str.split(" ");
        if (strs.length!=pattern.length()){
            return false;
        }
        Map<Character,String> pMap=new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (!pMap.containsKey(c)){
                if (pMap.containsValue(strs[i])){
                    return false;
                }
                pMap.put(c,strs[i]);
            }else {
                if (!pMap.get(c).equals(strs[i])){
                    return false;
                }
            }
        }
        return true;
    }
    @Test
    public void test(){
        String pattern="abba";
        String str="dog cat cat dog";
        boolean b = wordPattern(pattern, str);
        System.out.println(b);

    }
}
