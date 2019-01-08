package dp;

import org.junit.Test;

/**
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。

     示例:

     输入:

     1 0 1 0 0
     1 0 1 1 1
     1 1 1 1 1
     1 0 0 1 0

     输出: 4
 *
 */
public class Solution221 {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        if (m==0){
            return 0;
        }
        int n = matrix[0].length;
        //记录当前位置最大正方形的边长
        int[][] memo = new int[m][n];
        //最大正方形的边长
        int res=0;
        //初始化上边缘
        for (int i = 0; i < m; i++) {
            if (matrix[i][0]=='1'){
                memo[i][0]=1;
                res=1;
            }
        }
        //初始化左边缘
        for (int i = 0; i < n; i++) {
            if (matrix[0][i]=='1'){
                memo[0][i]=1;
                res=1;
            }

        }
        //状态转移方程：F（i,j）=Min{F(i,j-1),F(i-1,j),F(i-1,j-1)}+1
        //如果当前位置为1，那么当前位置的最大正方形的边长最多比它的上方，左方和左上方的位置多1
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j]=='1'){
                    memo[i][j]=min3(memo[i-1][j],memo[i][j-1],memo[i-1][j-1])+1;
                    //记录最大的边长
                    res=Math.max(res,memo[i][j]);
                }
                //else --> matrix[0][i]!='1' { memo[i][j]=0 }
            }
        }
        return res*res;
    }
    //从三个数中获得最小值
    private int min3(int a,int b,int c){
        return Math.min(a,Math.min(b,c));
    }
    @Test
    public void test(){
        char[][] matrix={{}};
        int i = maximalSquare(matrix);
        System.out.println(i);
    }
}
