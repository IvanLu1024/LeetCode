package dp;

import org.junit.Test;

/**
 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。

 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。

 示例 1:

 输入: "abc"
 输出: 3
 解释: 三个回文子串: "a", "b", "c".
 示例 2:

 输入: "aaa"
 输出: 6
 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 注意:

 输入的字符串长度不会超过1000。

 * @create 2019-02-15 15:38
 **/
public class Solution647 {
    //DP解法
    public int countSubstrings(String s) {
        if (s==null||s.length()==0){
            return 0;
        }
        int n = s.length();
        //memo[i,j]:起始位置为i，终止位置为j的字符串是否为回文串
        boolean[][] memo=new boolean[n][n];
        //初始化
        for (int i = 0; i < n; i++) {
            memo[i][i]=true;
        }
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i)==s.charAt(i+1)){
                memo[i][i+1]=true;
            }
        }
        //回文串的搜索
        for (int i = n-1; i >=0; i--) {
            for (int j = i+2; j <n ; j++) {
                if (memo[i+1][j-1]&&s.charAt(i)==s.charAt(j)){
                    memo[i][j]=true;
                }
            }
        }

        //统计回文子串的个数
        int res=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (memo[i][j]){
                    res++;
                }
            }
        }
        return res;
    }
    @Test
    public void test(){
        String s="aaa";
        int i = countSubstrings(s);
        System.out.println(i);
    }
}
