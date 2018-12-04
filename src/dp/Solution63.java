package dp;

import org.junit.Test;

public class Solution63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if (m==0){
            return 0;
        }
        int n=obstacleGrid[0].length;
        if (obstacleGrid[0][0]==1){
            return 0;
        }
        int[][] memo=new int[m][n];

        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0]!=1){
                memo[i][0]=1;
            }
            //表示此路不通，以后的元素没有必要继续
            else {
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i]!=1){
                memo[0][i]=1;
            }else {
                break;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {

                if (obstacleGrid[i][j]!=1){
                    memo[i][j]=memo[i-1][j]+memo[i][j-1];
                }else {
                    memo[i][j]=0;
                }
            }
        }
        return memo[m-1][n-1];
    }
    @Test
    public void test(){
        int[][] ob={{0,0,0},{0,1,0},{0,0,0}};
        int i = uniquePathsWithObstacles(ob);
        System.out.println(i);
    }
}
