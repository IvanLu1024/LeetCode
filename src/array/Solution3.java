package array;

import org.junit.Test;

/**
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 无重复字符的最长子串是 "abc"，其长度为 3。
 *
 */
public class Solution3 {
    //使用滑动窗口的思路
    public int lengthOfLongestSubstring(String s) {
        int l=0,h=-1;//s[l,h]为滑动窗口
        int[] freq=new int[256];//用于记录字符出现的频率
        int count=0;//子字符串的长度
        while (l<s.length()){
            if (h<s.length()-1&&freq[s.charAt(h+1)]==0){
                h++;
                freq[s.charAt(h)]++;
            }else {
                freq[s.charAt(l++)]--;
            }
            count=Math.max(count,h-l+1);
        }
        return count;

    }
    @Test
    public void test(){
        int n = lengthOfLongestSubstring(" ");
        System.out.println(n);

    }
}
