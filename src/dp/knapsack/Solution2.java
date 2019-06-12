package dp.knapsack;

import org.junit.Test;

public class Solution2 {
    private int[][] memo;
    public int knapsack(int[] w, int[] v, int C){
        int n = w.length;
        if (n==0||C==0){
            return 0;
        }
        memo=new int[n][C+1];

        //先初始化
        for (int i = 0; i <= C; i++) {
            if (i>=w[0]){
                memo[0][i]=v[0];
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= C; j++) {
                //0：不放入该物品
                memo[i][j]=memo[i-1][j];
                if (j>=w[i]){
                    //1：放入该物品
                    memo[i][j]=Math.max(memo[i-1][j],v[i]+memo[i-1][j-w[i]]);
                }
            }
        }
        return memo[n-1][C];
    }

    //优化空间复杂度
    public int knapsack1(int[] w, int[] v, int C){
        int n = w.length;
        if (n==0||C==0){
            return 0;
        }
        memo=new int[2][C+1];

        //先初始化
        for (int i = 0; i <= C; i++) {
            if (i>=w[0]){
                memo[0][i]=v[0];
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= C; j++) {
                //0：不放入该物品
                memo[i%2][j]=memo[(i-1)%2][j];
                if (j>=w[i]){
                    //1：放入该物品
                    memo[i%2][j]=Math.max(memo[i%2][j],v[i]+memo[(i-1)%2][j-w[i]]);
                }
            }
        }
        return memo[(n-1)%2][C];
    }

    private int[] dp;
    //进一步优化空间复杂度
    public int knapsack2(int[] w, int[] v, int C){
        int n = w.length;
        if (n==0||C==0){
            return 0;
        }
        dp=new int[C+1];

        //先初始化，将第一个元素放入背包中
        for (int i = 0; i <= C; i++) {
            if (i>=w[0]){
                dp[i]=v[0];
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = C; j >=w[i] ; j--) {
                dp[j]=Math.max(dp[j],v[i]+dp[j-w[i]]);
            }
        }
        return dp[C];
    }



    @Test
    public void test(){
        int[] coins={1,2,5};
        int amount=11;

    }
}
