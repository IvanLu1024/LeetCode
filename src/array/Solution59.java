package array;

import org.junit.Test;
import utils.ArrayUtils;

/**
 * 给定一个正整数 n，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 *
 * 示例:
 *
 * 输入: 3
 * 输出:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 */
public class Solution59 {
    int[][] d={{0,1},{1,0},{0,-1},{-1,0}};
    private int M,N;
    public int[][] generateMatrix(int n) {
        int[][] matrix=new int[n][n];
        int curX=0,curY=0,curD=0;
        int i=1;
        M=n;
        N=n;
        while (i<=n*n){
            if (matrix[curX][curY]==0){
                matrix[curX][curY]=i++;
            }
            int newX = curX + d[curD][0];
            int newY = curY + d[curD][1];
            if (inArea(newX,newY)&&matrix[newX][newY]==0){
                curX=newX;
                curY=newY;
            }else {
                curD=(curD+1)%4;
            }
        }
        return matrix;

    }

    private boolean inArea(int x,int y){
        return 0<=x&&x<M&&0<=y&&y<N;
    }
    @Test
    public void test(){
        /*int n=3;
        int[][] res = generateMatrix(n);
        ArrayUtils.print2DimenArray(res);*/
        Integer[] nums={1,2,3};
        ArrayUtils.printArray(nums);

    }
}
