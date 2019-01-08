package dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 * 示例:
 *
 * 输入:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * 输出: 6
 *
 */
public class Solution85 {
    //DP解法
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m==0){
            return 0;
        }
        int n = matrix[0].length;
        //存储左边界
        int[] left=new int[n];
        int[] right=new int[n];
        int[] height=new int[n];
        Arrays.fill(right,n);
        int max=0;
        for (int i = 0; i < m; i++) {
            int leftBound=0;
            int rightBound=n;
            //计算高度
            for (int j = 0; j < n; j++) {
                //从当前位置开始的高度
                if (matrix[i][j]=='0'){
                    height[j]=0;
                }else {
                    height[j]++;
                }
            }
            //计算左边界
            for (int j = 0; j < n; j++) {
                //当遇到0的时候，左边界从j+1开始寻找
                if (matrix[i][j]=='0'){
                    left[j]=0;
                    leftBound=j+1;
                }else {
                    left[j]=Math.max(left[j],leftBound);
                }
            }
            //计算右边界
            for (int j = n-1; j >=0 ; j--) {
                if (matrix[i][j]=='0'){
                    rightBound=j;
                    right[j]=n;
                }else {
                    right[j]=Math.min(right[j],rightBound);
                }
            }

            for (int j = 0; j < n; j++) {
                max=Math.max((right[j]-left[j])*height[j],max);
            }
        }
        return max;
    }


    @Test
    public void test(){
        char[][] ma={
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        int i = maximalRectangle(ma);
        System.out.println(i);
    }
}
