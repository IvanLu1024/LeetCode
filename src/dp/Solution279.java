package dp;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.util.Arrays;

/**
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 */
public class Solution279 {
    private int[] memo;
    public int numSquares(int n) {
        memo=new int[n+1];
        Arrays.fill(memo,-1);
        return findNumSquares(n);
    }
    private int findNumSquares(int n){
        if (n==0){
            return 0;
        }
        if (memo[n]!=-1){
            return memo[n];
        }

        int min=Integer.MAX_VALUE;
        for (int i = 1; n-i*i>=0; i++) {
            min=Math.min(min,1+findNumSquares(n-i*i));
        }
        memo[n]=min;
        return min;
    }

    public int numSquares1(int n){
        if (n==0){
            return 0;
        }
        memo=new int[n+1];
        Arrays.fill(memo,Integer.MAX_VALUE);
        memo[0]=0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; i-j*j>=0 ; j++) {
                memo[i]=Math.min(memo[i],1+memo[i-j*j]);
            }
        }
        return memo[n];
    }

    @Test
    public void test(){
        int n=13;
        int i = numSquares1(n);
        System.out.println(i);
    }
}
