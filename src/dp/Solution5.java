package dp;

import org.junit.Test;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 */
public class Solution5 {
    //DP的解法
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n==0){
            return "";
        }
        //记录最长的回文串的起始下标
        int start=0;
        //记录最长回文串的长度
        int maxLen=1;
        //memo[i][j] i:起始下标，j：结束下标，表示是否为回文串
        boolean[][] memo=new  boolean[n][n];
        char[] chars = s.toCharArray();
        //初始化，
        for (int i = 0; i < n; i++) {
            memo[i][i]=true;
                if (i+1<n&&chars[i]==chars[i+1]){
                    memo[i][i+1]=true;
                    maxLen=2;
                    start=i;
                }
        }
        //若s[i]和s[j]相等，并且memo[i+1][j-1]为true，此时可以构建新的回文串s[i,j]
        //从后向前地搜索
        for (int i = n-1; i >= 0; i--) {
            for (int j = i+2; j <n ; j++) {
                if (chars[i]==chars[j]&&memo[i+1][j-1]){
                    memo[i][j]=true;
                    if (j-i+1>maxLen){
                        maxLen=j-i+1;
                        start=i;
                    }
                }
            }
        }
        return s.substring(start,start+maxLen);
    }

    public String longestPalindrome1(String s){
        if (s==null||s.length()==0){
            return "";
        }
        int start=0,end=0;
        for (int i = 0; i < s.length(); i++) {
            //i：中间位置，若回文串为偶数则是偏左的位置
            int len1 = expand(s, i, i);            //回文串长度为奇数
            int len2 = expand(s, i, i + 1);     //回文串长度为偶数
            int len = Math.max(len1,len2);
            if (len>end-start){
                //len-1：对于奇数没有影响，对于偶数位置会偏左
                start = i - (len-1)/2;
                end = i +(len/2);
            }
        }
        return s.substring(start,end+1);
    }

    //从中间开始拓展
    private int expand(String s,int l ,int r){
        while (l>=0&&r<s.length()&&s.charAt(l)==s.charAt(r)){
            l--;
            r++;
        }
        return r-l-1;
    }
    @Test
    public void test(){
        String s="cbbd";
        String s1 = longestPalindrome1(s);
        System.out.println(s1);
    }
}
