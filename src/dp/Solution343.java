package dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 *
 */
public class Solution343 {
    public int integerBreak(int n) {
        assert n>=2;
        return breakInteger(n);
    }

    private int breakInteger(int n){
        if (n==1){
            return 1;
        }
        //记录分割结果的最大值
        int res=-1;
        //从1到n-1计算分割结果
        for (int i = 1; i <=n-1 ; i++) {
            res=max3(res,i*(n-i),i*breakInteger(n-i));
        }
        return res;
    }

    //记忆化搜索
    private int[] memo;
    public int integerBreak1(int n) {
        assert n>=2;
        memo=new int[n+1];
        return breakInteger1(n);
    }


    private int breakInteger1(int n){
        if (n==1){
            return 1;
        }
        //记录分割结果的最大值
        int res=-1;
        if (memo[n]!=0){
            res=memo[n];
        }else {
            //从1到n-1计算分割结果
            for (int i = 1; i <= n - 1; i++) {
                res = max3(res, i * (n - i), i * breakInteger1(n - i));
            }
            memo[n]=res;
        }
        return res;
    }
    private int max3(int a,int b,int c){
        return Math.max(a,Math.max(b,c));
    }

    public int integerBreak2(int n) {
        assert n>=2;
        memo=new int[n+1];
        return breakInteger2(n);

    }

    private int breakInteger2(int n){
        memo[1]=1;
        for (int i = 2; i <=n ; i++) {
            for (int j = 1; j <=i ; j++) {
                //将n分割成j+(i-j)
                memo[i]= max3(memo[i],j*(i-j),j*memo[i-j]);
            }
        }
        return memo[n];
    }


    @Test
    public void test(){
        int n=10;
        int i = integerBreak2(n);
        System.out.println(i);
    }
}
