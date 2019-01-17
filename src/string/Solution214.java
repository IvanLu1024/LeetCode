package string;

import org.junit.Test;

/**
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
 *
 * 示例 1:
 *
 * 输入: "aacecaaa"
 * 输出: "aaacecaaa"
 * 示例 2:
 *
 * 输入: "abcd"
 * 输出: "dcbabcd"
 *
 */
public class Solution214 {
    public String shortestPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s);
        StringBuilder reverseS = sb.reverse();
        int n = s.length();
        for (int i = 0; i <n ; i++) {
            String s1 = s.substring(0, n - i);
            String s2 = reverseS.substring(i,n-i);
            if (s1.compareTo(s2)==0){
                return reverseS.substring(0,i)+s;
            }
        }
        return "";
    }

    //思路：寻找最大的公共前缀回文串，将其余字符翻转后插入头部
    public String shortestPalindrome1(String s){
        int i=0,end=s.length()-1,j=end;
        char[] chars = s.toCharArray();
        while (i<j){
            if (chars[i]==chars[j]){
                i++;
                j--;
            }else {
                i=0;
                end--;
                j=end;
            }
        }
        StringBuilder reverse = new StringBuilder(s.substring(end + 1)).reverse();
        String res = reverse.append(s).toString();
        return res;
    }
    @Test
    public void test(){
        String s="aacecaaa";
        String res = shortestPalindrome1(s);
        System.out.println(res);

    }
}
