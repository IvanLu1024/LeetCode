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
                    memo[i][j]=Math.max(memo[i][j],v[i]+memo[i-1][j-w[i]]);
                }
            }
        }
        return memo[n-1][C];
    }
    @Test
    public void test(){
        int[] v={60,100,120};
        int[] w={10,20,30};
        int C=50;
        int knapsack = knapsack(w, v, C);
        System.out.println(knapsack);
    }
}
