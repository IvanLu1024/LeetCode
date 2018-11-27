package backTrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
 *
 * 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
 *
 * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
 *
 *
 *
 * 提示：
 *
 * 输出坐标的顺序不重要
 * m 和 n 都小于150
 *
 *
 * 示例：
 *
 *
 *
 * 给定下面的 5x5 矩阵:
 *
 *   太平洋 ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * 大西洋
 *
 * 返回:
 *
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
 *
 *
 */
public class Solution417 {

    private List<int[]> res=new ArrayList<>();
    private int m,n;
    private int[][] d={{-1,0},{0,1},{1,0},{0,-1}};
    private boolean[][] pFlag;
    private boolean[][] aFlag;


    //判断当前坐标是否在给定区域内
    private boolean inArea(int x,int y){
        return x>=0&&x<m&&y>=0&&y<n;
    }

    public List<int[]> pacificAtlantic(int[][] matrix) {
        m=matrix.length;
        if (m==0){
            return res;
        }
        n=matrix[0].length;
        pFlag=new boolean[m][n];
        aFlag=new boolean[m][n];
        //太平洋
        for (int i = 0; i < m; i++) {
            if (!pFlag[i][0]){
                dfs(matrix,pFlag,i,0);
            }
        }

        for (int i = 0; i < n; i++) {
            if (!pFlag[0][i]){
                dfs(matrix,pFlag,0,i);
            }
        }
        //大西洋
        for (int i = 0; i < m; i++) {
            if (!aFlag[i][n-1]){
                dfs(matrix,aFlag,i,n-1);
            }
        }

        for (int i = 0; i < n; i++) {
            if (!aFlag[m-1][i]){
                dfs(matrix,aFlag,m-1,i);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pFlag[i][j]&&aFlag[i][j]){
                    res.add(new int[]{i,j});
                }
            }
        }
        return res;

    }

    private void dfs(int[][] matrix,boolean[][] used,int x,int y){
        used[x][y]=true;
        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if (inArea(newX,newY)&&!used[newX][newY]&&matrix[newX][newY]>=matrix[x][y]){
                dfs(matrix,used,newX,newY);
            }
        }
    }
    @Test
    public void test(){
        int[][] m={{1 ,2 ,2,3,5},
                {3,2,3,4,4},
                {2,4,5,3,1},
                {6,7,1,4,5},
                {5,1,1,2,4}};

        List<int[]> r = pacificAtlantic(m);
        for (int i = 0; i < r.size(); i++) {
            System.out.println("x:"+r.get(i)[0]+" y:"+r.get(i)[1]);
        }
    }
}
