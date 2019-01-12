package string;

import org.junit.Test;

/**
 * 给定一个字符串和一个整数 k，你需要对从字符串开头算起的每个 2k 个字符的前k个字符进行反转。
 *
 * 如果剩余少于 k 个字符，则将剩余的所有全部反转。如果有小于 2k 但大于或等于 k 个字符，则反转前 k 个字符，并将剩余的字符保持原样。

 示例:

 输入: s = "abcdefg", k = 2
 输出: "bacdfeg"
 要求:

 该字符串只包含小写的英文字母。
 给定字符串的长度和 k 在[1, 10000]范围内。
 *
 */
public class Solution541 {
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int n = s.length();
        if (n==0){
            return "";
        }
        //每个 2k 个字符, 翻转前k个字符，
        for (int i = 0; i <n ; i+=(2*k)) {
            reverse(chars,i,i+k-1);
        }
        return new String(chars);
    }

    /**
     * 翻转字符数组
     *
     * @param chars
     * @param l 起始坐标
     * @param r 终止坐标
     */
    private void reverse(char[] chars,int l,int r){
        //为了避免传进来的r可能会大于数组长度,最后一个i+k-1可能>=n-1
        r=Math.min(r,chars.length-1);
        while (l<r){
            char c = chars[l];
            chars[l]=chars[r];
            chars[r]=c;
            l++;
            r--;
        }
    }
    @Test
    public void test(){
        String s="abcdefg";
        int k=8;
        String res = reverseStr(s, k);
        System.out.println(res);
    }
}

