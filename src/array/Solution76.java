package array;

import org.junit.Test;

/**
 * 给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。
 *
 * 示例：
 *
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 *
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 */
public class Solution76 {

    public String minWindow(String s, String t) {
        int[] freq=new int[128];
        int sLen = s.length();
        int tLen = t.length();
        if (sLen==0||tLen==0)
            return "";
        int minLen=Integer.MAX_VALUE;
        //首先统计t中出现的字符的频率
        for (int i=0;i<t.length();i++){
            freq[t.charAt(i)]++;
        }
        int l=0;//起始位置
        int total=0;//记录当前包含的字符数量
        for (int i=0,j=0;i<sLen;i++){
            //当t中的字符出现的时候
            if (freq[s.charAt(i)]-->0)
                total++;
            //此时说明已经包含了t
            while (total==tLen){
                if (i-j+1<minLen){
                    minLen=i-j+1;
                    l=j;
                }
                if (++freq[s.charAt(j++)]>0)
                    total--;
            }

        }
        if (minLen==Integer.MAX_VALUE)
            return "";
        else
            return s.substring(l,l+minLen);

    }


    @Test
    public void test(){
        String count = minWindow("ADOBECODEBANC", "ABC");
        System.out.println(count);


    }
}
