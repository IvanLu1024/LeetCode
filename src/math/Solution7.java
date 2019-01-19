package math;

import org.junit.Test;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

 示例 1:

 输入: 123
 输出: 321
 示例 2:

 输入: -123
 输出: -321
 示例 3:

 输入: 120
 输出: 21
 注意:

 假设我们的环境只能存储得下 32 位的有符号整数，

 则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 */
public class Solution7 {
    //使用long型存储翻转数字的解法
    public int reverse(int x) {
        long reX = 0;
        long num = x;
        while (num!=0){
            reX = reX*10+num%10;
            num/=10;
        }
        if (reX>Integer.MAX_VALUE||reX<Integer.MIN_VALUE){
            return 0;
        }
        return (int) reX;
    }

    //转为为绝对值的解法
    public int reverse1(int x){
        if (x==Integer.MIN_VALUE){
            return 0;
        }
        int reverseNum = 0;
        //num：为x的绝对值
        int num = Math.abs(x);
        int sign = 1;
        if (x<0){
            sign=-1;
        }
        while (num!=0) {
            //当翻转数溢出整型范围的时候，直接返回0；Integer.MAX_VALUE：2147483647
            if (reverseNum > Integer.MAX_VALUE / 10 || (reverseNum == Integer.MAX_VALUE / 10 && x % 10 > 7))
                return 0;
            reverseNum=reverseNum*10+num%10;
            num/=10;
        }
        return sign*reverseNum;
    }
    //使用局部变量的解法
    public int reverse2(int x){
        int num = x;
        int reverseNum = 0;
        while (num!=0){
            //确保newReverseNum不会整型溢出
            if (reverseNum>Integer.MAX_VALUE/10||reverseNum<Integer.MIN_VALUE/10){
                return 0;
            }
            int newReverseNum = 10*reverseNum+num%10;
            reverseNum = newReverseNum;
            num/=10;
        }
        return reverseNum;
    }

    @Test
    public void test(){
        int x=Integer.MAX_VALUE-1;
        System.out.println(Integer.MAX_VALUE);
        int reverse = reverse2(x);
        System.out.println(reverse);
    }
}
