package array;

import org.junit.Test;

/**
 * 实现 strStr() 函数。

 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

 示例 1:

 输入: haystack = "hello", needle = "ll"
 输出: 2
 示例 2:

 输入: haystack = "aaaaa", needle = "bba"
 输出: -1
 说明:

 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 *
 */
public class Solution28 {
    public int strStr(String haystack, String needle) {
        if (needle.length()==0){
            return 0;
        }
        if (needle.length()>haystack.length()){
            return -1;
        }
        char[] hChars = haystack.toCharArray();
        char[] nChars = needle.toCharArray();
        //i：表示母串中的子串的起始位置下标
        for (int i = 0; i <=hChars.length-nChars.length ; i++) {
            //j:子串的起始位置下标
            int j=0;
            //j=0:确保每次循环都是从needle的头开始
            for (j = 0; j <nChars.length ; j++) {
                if (nChars[j]!=hChars[i+j]){
                    break;
                }
            }
            //只有nChars[j]和hChars[i+j]每一位都相等的时候，j才能等于nChars.length
            //这时说明已经找到了子串
            if (j==nChars.length){
                return i;
            }
        }
        return -1;
    }
    @Test
    public void test(){
        String h="ll";
        String n="ll";
        int i = strStr(h, n);
        System.out.println(i);
    }
}
