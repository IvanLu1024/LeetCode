package array;

/**
 * @Author Ivan 14:36
 * @Description TODO
 */
public class Solution73 {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        if(m==0){
            return ;
        }
        int n = matrix[0].length;
        boolean[] rows=new boolean[m];
        boolean[] cols=new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j]==0){
                    rows[i]=true;
                    cols[j]=true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (rows[i]){
                for (int j = 0; j < n; j++) {
                    matrix[i][j]=0;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (cols[i]){
                for (int j = 0; j < m; j++) {
                    matrix[j][i]=0;
                }
            }
        }

    }

    public void setZeroes1(int[][] matrix) {
        int m = matrix.length;
        if(m==0){
            return ;
        }
        int n = matrix[0].length;
        boolean rows=false;
        boolean cols=false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0]==0){
                cols=true;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (matrix[0][i]==0){
                rows=true;
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j]==0){
                    matrix[i][0]=0;
                    matrix[0][j]=0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            if (matrix[i][0]==0){
                for (int j = 1; j < n; j++) {
                    matrix[i][j]=0;
                }
            }
        }
        for (int i = 1; i < n; i++) {
            if (matrix[0][i]==0){
                for (int j = 1; j < m; j++) {
                    matrix[j][i]=0;
                }
            }
        }
        if (rows){
            for (int i = 0; i < n; i++) {
                matrix[0][i]=0;
            }
        }
        if (cols){
            for (int i = 0; i < m; i++) {
                matrix[i][0]=0;
            }
        }
    }

}
