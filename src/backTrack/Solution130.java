package backTrack;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 *
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 示例:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 *
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 *
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
 *
 * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 *
 */
public class Solution130 {
    private int m,n;
    private boolean[][] isO;
    private int[][] d={{-1,0},{0,1},{1,0},{0,-1}};

    //判断当前坐标是否在给定区域内
    private boolean inArea(int x,int y){
        return x>=0&&x<m&&y>=0&&y<n;
    }


    //将所有和边界O相连的O都标记出来。
    public void solve(char[][] board) {
        m=board.length;
        if (m==0){
            return;
        }
        n=board[0].length;
        isO =new boolean[m][n];
        for (int i = 0; i < m; i++) {
            if (board[i][0]=='O'&&!isO[i][0]){
                dfs(board,i,0);
            }
            if (board[i][n-1]=='O'&&!isO[i][n-1]){
                dfs(board,i,n-1);
            }
        }
        for (int i = 0; i < n; i++) {
            if (board[0][i]=='O'&&!isO[0][i]){
                dfs(board,0,i);
            }
            if (board[m-1][i]=='O'&&!isO[m-1][i]){
                dfs(board,m-1,i);
            }
        }
        for (int i = 1; i < m-1; i++) {
            for (int j = 1; j < n-1; j++) {
                if (!isO[i][j]&&board[i][j]=='O'){
                    board[i][j]='X';
                }
            }
        }
    }
    private void dfs(char[][] board,int x,int y){
        System.out.println("x:"+x+","+"y:"+y);
        isO[x][y]=true;
        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if (inArea(newX,newY)&&board[newX][newY]=='O'&&!isO[newX][newY]){
                dfs(board,newX,newY);
            }
        }
    }


    @Test
    public void test(){

        /*[["O","X","O","O","X","X"],["O","X","X","X","O","X"],
        ["X","O","O","X","O","O"],["X","O","X","X","X","X"],
        ["O","O","X","O","X","X"],["X","X","O","O","O","O"]]*/
        /*String[] s={"XXXX", "XOOX", "XXOX", "XOXX"};*/
        String[] s={"OXOOXX","OXXXOX","XOOXOO","XOXXXX","OOXOXX","XXOOOO"};
        char[][] b=new char[s.length][s[0].length()];
        for (int i = 0; i < s.length; i++) {
            b[i]=s[i].toCharArray();
        }

        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                System.out.print(b[i][j]);
            }
            System.out.println();
        }
        solve(b);
        System.out.println("--------------");
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                System.out.print(b[i][j]);
            }
            System.out.println();
        }
    }

}
