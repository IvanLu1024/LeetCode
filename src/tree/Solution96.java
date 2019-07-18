package tree;

import org.junit.Test;

public class Solution96 {
    /**
     * 递推公式：
     * F(0)=1
     * F(1)=1
     * F(N)=SUM(F(i-1)*F(n-i))
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] memo=new int[n+1];
        memo[0]=1;
        memo[1]=1;
        for (int i = 2; i <=n ; i++) {
            for (int j = 1; j <= i; j++) {
                memo[i]+=memo[j-1]*memo[i-j];
            }
        }
        return memo[n];
    }
    @Test
    public void test(){
        int n=3;
        int res = numTrees(n);
        System.out.println(res);
    }

}
