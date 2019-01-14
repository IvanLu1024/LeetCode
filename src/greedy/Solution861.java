package greedy;

import org.junit.Test;

/**
 * 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
 *
 * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
 *
 * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
 *
 * 返回尽可能高的分数。
 *
 *
 *
 * 示例：
 *
 * 输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
 * 输出：39
 * 解释：
 * 转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
 * 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 *
 *
 * 提示：
 *
 * 1 <= A.length <= 20
 * 1 <= A[0].length <= 20
 * A[i][j] 是 0 或 1
 *
 */
public class Solution861 {
    //贪心策略：尽量确保高位的1的个数尽量多
    public int matrixScore(int[][] A) {
        int row = A.length;
        if (row==0){
            return 0;
        }
        int col = A[0].length;
        for (int i = 0; i < row; i++) {
            if (A[i][0]==0){
                reverseCol(A,i);
            }
        }
        for (int i = 1; i < col; i++) {
            int zero=colZeros(A,i);
            if (zero>row/2){
                reverseRow(A,i);
            }
        }
        int res=scores(A);
        return res;
    }

    //按列来进行 行翻转
    private void reverseRow(int[][] a,int col){
        for (int i = 0; i < a.length; i++) {
            if (a[i][col]==1){
                a[i][col]=0;
            }else {
                a[i][col]=1;
            }
        }
    }

    //按行来进行列翻转
    private void reverseCol(int[][] a,int row){
        for (int i = 0; i < a[0].length; i++) {
            if (a[row][i]==1){
                a[row][i]=0;
            }else {
                a[row][i]=1;
            }
        }
    }

    //统计一列中
    private int colZeros(int[][] a,int col){
        int zero=0;
        for (int i = 0; i < a.length; i++) {
            if (a[i][col]==0){
                zero++;
            }
        }
        return zero;
    }

    //计算分数
    private int scores(int[][] a){
        int res=0;
        for (int i = 0; i < a.length; i++) {
            int rowNum=0;
            int col = a[0].length-1;
            for (int j = 0; j <= col; j++) {
                int bit = a[i][j];
                rowNum+=bit<<(col-j);
            }
            res+=rowNum;
        }
        return res;
    }
    @Test
    public void test(){
        /*[0,0,1,1],[1,0,1,0],[1,1,0,0]*/
        int[][] a={{0,0,1,1},{1,0,1,0},{1,1,0,0}};
        int i = matrixScore(a);
        System.out.println(i);
    }
}
