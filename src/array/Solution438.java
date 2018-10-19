package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。

 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。

 说明：

 字母异位词指字母相同，但排列不同的字符串。
 不考虑答案输出的顺序。
 示例 1:

 输入:
 s: "cbaebabacd" p: "abc"

 输出:
 [0, 6]

 解释:
 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *
 */
public class Solution438 {

    public List<Integer> findAnagrams(String s, String p) {
        int[] pFreq=new int[26];
        int[] sFreq=new int[26];
        List<Integer> res=new ArrayList<Integer>();
        //首先统计p中出现的字符频率
        int pLen = p.length();
        for (int i=0;i<pLen;i++){
            pFreq[p.charAt(i)-'a']++;
        }
        for (int i=0;i<s.length();i++){
            sFreq[s.charAt(i)-'a']++;
            if (i>=pLen){
                sFreq[s.charAt(i-pLen)-'a']--;
            }
            if (Arrays.equals(sFreq,pFreq)){
                res.add(i-pLen+1);
            }

        }
        return res;

    }
    @Test
    public void test(){
        List<Integer> list = findAnagrams("cbaebabacd", "abc");
        for (Integer i:list){
            System.out.println(i);
        }
    }
}
