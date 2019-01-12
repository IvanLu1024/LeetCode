package array;

import org.junit.Test;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 *
 */
public class Solution240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m==0){
            return false;
        }
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j]==target){
                    return true;
                }else if (matrix[i][j]>target){
                    break;
                }
            }
        }
        return false;
    }

    public boolean searchMatrix1(int[][] matrix, int target){
        int m = matrix.length;
        if (m==0){
            return false;
        }
        int n = matrix[0].length;
        //关键在于初始值的设置，
        int i=m-1,j=0;
        while (i>=0&&j<n){
            //搜索的当前值大于目标值的时候继续向上搜索
            if (matrix[i][j]>target){
                i--;
            }
            //搜素的当前值小于目标值的时候继续向右搜索
            else if (matrix[i][j]<target){
                j++;
            }
            //相等的时候则直接返回true
            else {
                return true;
            }
        }
        return false;
    }
    @Test
    public void test(){
        int[][] matrix={
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        int t=20;
        boolean b = searchMatrix1(matrix, t);
        System.out.println(b);
    }
}
