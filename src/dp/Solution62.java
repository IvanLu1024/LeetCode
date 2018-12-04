package dp;

import org.junit.Test;

public class Solution62 {
    public int uniquePaths(int m, int n) {
        if (m==0){
            return 0;
        }
        int[][] memo=new int[m][n];
        for (int i = 0; i < n; i++) {
            memo[0][i]=1;
        }
        for (int i = 0; i < m; i++) {
            memo[i][0]=1;
        }
        for (int i = 1; i <m ; i++) {
            for (int j = 1; j < n; j++) {
                memo[i][j]=memo[i-1][j]+memo[i][j-1];
            }
        }
        return memo[m-1][n-1];
    }
    @Test
    public void test(){
        int m=7;
        int n=3;
        int i = uniquePaths(m, n);
        System.out.println(i);
    }
}
