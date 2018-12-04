package dp;

import org.junit.Test;

/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 *
 */
public class Solution64 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        if (m==0){
            return 0;
        }
        int n = grid[0].length;
        int[][] memo=new int[m][n];
        memo[0][0]=grid[0][0];
        //先处理第一行(m=0),因为这一行只能右移得到
        for (int i = 1; i < n; i++) {
            memo[0][i]=memo[0][i-1]+grid[0][i];
        }
        //再处理第一列(n=0),因为这一列只能下移得到
        for (int i = 1; i < m; i++) {
            memo[i][0]=memo[i-1][0]+grid[i][0];
        }

        //处理其他的位置
        for (int i = 1; i <m ; i++) {
            for (int j = 1; j <n ; j++) {
                memo[i][j]=grid[i][j]+Math.min(memo[i-1][j],memo[i][j-1]);
            }
        }

        return memo[m-1][n-1];

    }

    //优化空间复杂度——>空间复杂度为O（1）
    public int minPathSum1(int[][] grid){
        int m = grid.length;
        if (m==0){
            return 0;
        }
        int n = grid[0].length;
        //先处理第一行
        for (int i = 1; i < m; i++) {
            grid[i][0]+=grid[i-1][0];
        }
        //再处理第一列
        for (int i = 1; i < n; i++) {
            grid[0][i]+=grid[0][i-1];
        }
        //再处理其他位置
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j]=grid[i][j]+Math.min(grid[i-1][j],grid[i][j-1]);
            }
        }
        return grid[m-1][n-1];
    }

    @Test
    public void test(){
        int[][] g={{1,3,1},
                {1,5,1},
                {4,2,1}

        };
        int min = minPathSum1(g);
        System.out.println(min);

    }
}
