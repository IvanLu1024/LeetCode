package dp;

import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

public class Fibonacci {

    //原始写法
    public int Fi(int n){
        if (n==0){
            return 0;
        }
        if (n==1){
            return 1;
        }
        return Fi(n-1)+Fi(n-2);
    }

    //带有记忆化搜索
    private int[] memo;
    public int Fi1(int n){
        if (n==0){
            return 0;
        }
        if (n==1){
            return 1;
        }
        if (memo[n]==-1){
            memo[n]=Fi1(n-1)+Fi1(n-2);
            return memo[n];
        }

        return memo[n];
    }

    //自底向上
    public int Fi2(int n){
        int[] m=new int[n+1];
        m[0]=0;
        m[1]=1;
        for (int i = 2; i <=n ; i++) {
            m[i]=m[i-1]+m[i-2];

        }
        return m[n];
    }

    @Test
    public void test(){
        int n=43;
        memo= new int[n+1];
        Arrays.fill(memo,-1);
        long start = System.currentTimeMillis();
        int r = Fi2(n);
        long end = System.currentTimeMillis();
        System.out.println("result:"+r+" costs "+(end-start));


    }
}
