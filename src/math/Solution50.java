package math;

import org.junit.Test;

public class Solution50 {

    //时间复杂度：O（logN）
    public double myPow(double x, int n) {
        if (n==0){
            return 1.0;
        }
        //先折半
        double half = myPow(x,n/2);
        //若n为偶数则将折半值直接相乘即可
        if (n%2==0) return half*half;
        //n%2==1:若n为奇数的情况
        //要分成n为正数和负数的情况来讨论
        if (n<0) return half*half/x;
        return half*half*x;
    }
    @Test
    public void test(){
        double x =2;
        int n = -3;
        double v = myPow(x, n);
        System.out.println(v);
    }
}
