package backTrack;

import org.junit.Test;

/**
 * @Author Ivan 16:06
 * @Description TODO
 */
public class Solution329 {
    private int[][] d={{-1,0},{0,1},{1,0},{0,-1}};
    private int m,n;
    private int maxLen=0;
    private int[][] visited;

    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        if (m==0){
            return 0;
        }
        n = matrix[0].length;
        visited=new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]!=0) continue;
                dfs(matrix,i,j);
            }
        }
        return maxLen;
    }

    private int dfs(int[][] matrix,int x,int y){
        if (visited[x][y]!=0){
            return visited[x][y];
        }

        visited[x][y]=1;
        for (int i = 0; i < 4; i++) {
            int x1=x+d[i][0];
            int y1=y+d[i][1];
            if (inArea(x1,y1)&&matrix[x][y]<matrix[x1][y1]){
                int len = dfs(matrix, x1, y1);
                visited[x][y]=Math.max(len+1,visited[x][y]);
            }
        }
        maxLen=Math.max(maxLen,visited[x][y]);
        return visited[x][y];

    }

    private boolean inArea(int x,int y){
        return x>=0&&x<m&&y>=0&&y<n;
    }
    @Test
    public void test(){
        /*[9,9,4],
  [6,6,8],
  [2,1,1]*/
        int[][] mar={{9,9,4},{6,6,8},{2,1,1}};
        int i = longestIncreasingPath(mar);
        System.out.println(i);

    }
}
