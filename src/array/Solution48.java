package array;

import org.junit.Test;
import utils.ArrayUtils;

/**
 * 给定一个 n × n 的二维矩阵表示一个图像。
 *
 * 将图像顺时针旋转 90 度。
 *
 * 说明：
 *
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 *
 * 示例 1:
 *
 * 给定 matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 * 示例 2:
 *
 * 给定 matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 */
public class Solution48 {
    //顺时针旋转90度
    public void rotate(Integer[][] matrix) {
        int n=matrix.length;
        //先上下交换
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < n; j++) {
                int t=matrix[i][j];
                matrix[i][j]=matrix[n-1-i][j];
                matrix[n-1-i][j]=t;
            }
        }

        //按对角线对称交换
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j< n; j++) {
                int t=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=t;
            }
        }
    }

    //逆时针旋转90度
    public void antiRotate(Integer[][] matrix){
        int n=matrix.length;
        //先左右交换
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n/2; j++) {
                int t=matrix[i][j];
                matrix[i][j]=matrix[i][n-1-j];
                matrix[i][n-1-j]=t;
            }
        }

        //按对角线对称交换
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j< n; j++) {
                int t=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=t;
            }
        }

    }

    @Test
    public void test(){
        Integer[][] matrix={
                {1,2,3},
                {4,5,6},
                {7,8,9}};
        ArrayUtils.print2DimenArray(matrix);
        System.out.println("after rotate:");
        antiRotate(matrix);
        ArrayUtils.print2DimenArray(matrix);
    }

}
