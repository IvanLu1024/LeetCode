package string;

import org.junit.Test;

import java.util.Arrays;

/**
 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。

 换句话说，第一个字符串的排列之一是第二个字符串的子串。

 示例1:

 输入: s1 = "ab" s2 = "eidbaooo"
 输出: True
 解释: s2 包含 s1 的排列之一 ("ba").


 示例2:

 输入: s1= "ab" s2 = "eidboaoo"
 输出: False


 注意：

 输入的字符串只包含小写字母
 两个字符串的长度都在 [1, 10,000] 之间

 */
public class Solution567 {
    public boolean checkInclusion(String s1, String s2) {
        if (s1==null|| s1.length()==0){
            return true;
        }
        //记录s1中各字符出现的频率
        int[] freq1 = new int[26];
        //记录s2中子字符串各字符出现的频率
        int[] freq2 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            freq1[s1.charAt(i)-'a']++;
        }
        for (int i = 0; i < s2.length(); i++) {
            freq2[s2.charAt(i)-'a']++;
            //确保freq2记录的范围是s2[i-l1,i]即子字符串长度等于s1的长度
            if (i>=s1.length()){
                freq2[s2.charAt(i-s1.length())-'a']--;
            }
            //若各个字符出现频率相同则证明s2 是否包含 s1 的排列
            if (Arrays.equals(freq1,freq2)){
                return true;
            }
        }
        return false;
    }
    @Test
    public void test(){
        String s1="adc";
        String s2="dcda";
        boolean b = checkInclusion(s1, s2);
        System.out.println(b);
    }
}
