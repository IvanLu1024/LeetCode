package math;

import org.junit.Test;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。

 返回被除数 dividend 除以除数 divisor 得到的商。

 示例 1:

 输入: dividend = 10, divisor = 3
 输出: 3
 示例 2:

 输入: dividend = 7, divisor = -3
 输出: -2
 说明:

 被除数和除数均为 32 位有符号整数。
 除数不为 0。
 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，

 则返回 2^31 − 1。
 *
 * @author
 * @create 2019-01-27 20:39
 **/
public class Solution29 {
    //时间复杂度：O(log(N))
    public int divide(int dividend, int divisor) {
        if (dividend==0){
            return 0;
        }
        long ldiv = dividend;
        long ldsor = divisor;
        boolean flag=false;
        if ((ldiv<0&&ldsor>0)||(dividend>0&&ldsor<0)){
            flag=true;      //-1
        }
        ldiv=Math.abs(ldiv);
        ldsor=Math.abs(ldsor);
        long res = div(ldiv, ldsor);
        if (flag){
            res=-res;
        }
        if (res>Integer.MAX_VALUE||res<Integer.MIN_VALUE){
            return Integer.MAX_VALUE;
        }
        return (int) res;
    }

    //除法的辅助函数
    //利用加法去求解 除法
    private long div(long dividend,long divisor){
        //递归终止条件
        if (dividend<divisor){
            return 0;
        }
        long sum = divisor;
        //倍数
        long count = 1;
        //二分搜索
        while (sum+sum<=dividend){
            sum+=sum;
            count+=count;
        }
        return count+div(dividend-sum,divisor);
    }
    @Test
    public void test(){
        /**
         * -2147483648
         -1
         */
        int dividend=-2147483648;
        int divisor=-1;
        int res = divide(dividend, divisor);
        System.out.println(res);
    }
}
