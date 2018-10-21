package find;

import org.junit.Test;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。

     示例 1:

     输入: s = "anagram", t = "nagaram"
     输出: true
     示例 2:

     输入: s = "rat", t = "car"
     输出: false
 *
 */
public class Solution242 {

    //使用map的方式来解决
    public boolean isAnagram(String s, String t) {
        if (s.equals("")||t.equals("")){
            return false;
        }
        if (s.length()!=t.length()) {
            return false;
        }
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        for (char s1:s.toCharArray()){
            Integer count = map1.get(s1);
            map1.put(s1,count==null?0:count+1);
        }
        for (char t1:t.toCharArray()){
            Integer count = map2.get(t1);
            map2.put(t1,count==null?0:count+1);
        }
        Set<Character> keySet1 = map1.keySet();
        for (Character c:keySet1){
            if (!map1.get(c).equals(map2.get(c))){
                return false;
            }
        }
        return true;
    }

    //使用数组的方式来解决,并且只使用两次循环
    public boolean isAnagram1(String s, String t) {
        int sLen = s.length();
        int tLen=t.length();
        if (sLen!=tLen){
            return false;
        }
        int[] freq=new int[26];
        for (int i = 0; i <sLen ; i++) {
            freq[s.charAt(i)-'a']++;
            freq[t.charAt(i)-'a']--;

        }
        for (int i:freq){
            if (i!=0){
                return false;
            }
        }
        return true;

    }

    @Test
    public void test(){
        boolean flag = isAnagram1("", "");
        System.out.println(flag);
    }



}
