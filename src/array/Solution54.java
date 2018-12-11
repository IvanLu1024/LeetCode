package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。

 示例 1:

 输入:
 [
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
 ]
 输出: [1,2,3,6,9,8,7,4,5]
 示例 2:

 输入:
 [
 [1, 2, 3, 4],
 [5, 6, 7, 8],
 [9,10,11,12]
 ]
 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class Solution54 {
    int[][] d={{0,1},{1,0},{0,-1},{-1,0}};
    private int N,M;
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res=new ArrayList<>();
        int m=matrix.length;
        if (m==0){
            return res;
        }
        int n=matrix[0].length;
        M=m;
        N=n;
        boolean[][] visited=new boolean[m][n];
        int curX=0,curY=0,curD=0;
        while (res.size()<m*n){
            if (!visited[curX][curY]){
                res.add(matrix[curX][curY]);
                visited[curX][curY]=true;
            }
            int newX = curX + d[curD][0];
            int newY = curY + d[curD][1];
            if (inArea(newX,newY)&&!visited[newX][newY]){
                curX=newX;
                curY=newY;
            }else {
                curD=(curD+1)%4;
            }

        }
        return res;

    }

    private boolean inArea(int x,int y){
        return 0<=x&&x<M&&0<=y&&y<N;
    }

    @Test
    public void test(){
        int[][] ma={{1, 2, 3},
                {4,5,6},
                {7,8,9}};
        List<Integer> list = spiralOrder(ma);
        System.out.println(list);
    }

}
