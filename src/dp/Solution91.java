package dp;

import org.junit.Test;

/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * 示例 1:
 *
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 *
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 *
 */
public class Solution91 {
    public int numDecodings(String s) {
        int n = s.length();
        if (n==0){
            return 0;
        }
        int[] memo=new int[n+1];
        memo[0]=1;
        if (s.charAt(0)>'0'){
            memo[1]=1;
        }
        for (int i = 2; i <= n; i++) {
            int one = Integer.parseInt(s.substring(i - 1, i));
            //如果一个字符满足(1-9)则记录
            if (one>0){
                memo[i]+=memo[i-1];
            }
            int two = Integer.parseInt(s.substring(i - 2, i));
            //如果两个字符满足(10-26)则记录
            if (two<=26&&two>=10){
                memo[i]+=memo[i-2];
            }
        }
        return memo[n];
    }
    @Test
    public void test(){
        String s="12";
        int i = numDecodings(s);
        System.out.println(i);
    }
}
