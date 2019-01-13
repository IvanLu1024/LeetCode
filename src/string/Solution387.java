package string;

import org.junit.Test;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

 案例:

 s = "leetcode"
 返回 0.

 s = "loveleetcode",
 返回 2.


 注意事项：您可以假定该字符串只包含小写字母。
 *
 */
public class Solution387 {
    public int firstUniqChar(String s) {
        if (s.length()==0){
            return -1;
        }
        int[] freq=new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            freq[chars[i]-'a']++;
        }
        for (int i = 0; i < chars.length; i++) {
            int j = chars[i]-'a';
            if (freq[j]==1){
                return i;
            }
        }
        return -1;
    }

    public int firstUniqChar1(String s){
        int n = s.length();
        if (n==0){
            return -1;
        }
        //用于记录第一个出现字符的下标
        int index=n;
        char[] chars = s.toCharArray();
        for (int i = 'a'; i <= 'z'; i++) {
            int first = s.indexOf(i);
            int last = s.lastIndexOf(i);
            //如果第一次出现和最后一次出现的下标相等的话
            // 说明是只出现一次的字符
            if (first==last&&first!=-1){
                //取最小值，则是寻找第一个出现的字符
                index=Math.min(index,first);
            }
        }
        if (index==n){
            return -1;
        }
        return index;
    }
    @Test
    public void test(){
        String s="leetcode";
        int i = firstUniqChar1(s);
        System.out.println(i);
    }
}
