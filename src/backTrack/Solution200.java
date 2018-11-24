package backTrack;

import org.junit.Test;

/**
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 * 一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。

 示例 1:

 输入:
 11110
 11010
 11000
 00000

 输出: 1
 示例 2:

 输入:
 11000
 11000
 00100
 00011

 输出: 3
 *
 */
public class Solution200 {
    private int m,n;
    private boolean[][] visited;
    private int[][] d={{-1,0},{0,1},{1,0},{0,-1}};

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        m=grid.length;
        n=grid[0].length;
        int res=0;
        visited=new boolean[m][n];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]=='1'&&!visited[i][j]){
                    dfs(grid,i,j);
                    res++;

                }
            }
        }
        return res;
    }

    // 从grid[x][y]的位置开始,进行floodfill
    // 保证(x,y)合法,且grid[x][y]是没有被访问过的陆地
    private void dfs(char[][] grid,int x,int y){
        visited[x][y]=true;
        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if (inArea(newX,newY)&&!visited[newX][newY]&&grid[newX][newY]=='1'){
                dfs(grid,newX,newY);
            }
        }
        return;

    }
    //判断当前坐标是否在给定区域内
    private boolean inArea(int x,int y){
        return x>=0&&x<m&&y>=0&&y<n;
    }

    @Test
    public void test(){
        /*11110
 11010
 11000
 00000*/
        char[][] gird={
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}};
        /*char grid1[][] = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };*/
        char grid2[][] = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        int i = numIslands(gird);
        System.out.println(i);
    }
}
