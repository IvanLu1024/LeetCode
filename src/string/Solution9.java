package string;

import org.junit.Test;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶:
 *
 * 你能不将整数转为字符串来解决这个问题吗？
 *
 */
public class Solution9 {
    //转化为字符串来进行求解
    public boolean isPalindrome(int x) {
        if (x<0){
            return false;
        }
        String s = String.valueOf(x);
        int l=0,r=s.length()-1;
        while (l<r){
            if (s.charAt(l++)!=s.charAt(r--)){
                return false;
            }
        }
        return true;
    }
    public boolean isPalindrome1(int x){
        if (x<0){
            return false;
        }
        int num=x;
        int reNum=0;
        //翻转正整数
        while (num!=0){
            reNum=10*reNum+(num%10);
            num/=10;
        }
        //判断翻转以后和原始值是否相等
        return reNum==x;
    }
    @Test
    public void test(){
        int x=10;
        boolean res = isPalindrome1(x);
        System.out.println(res);
    }
}
