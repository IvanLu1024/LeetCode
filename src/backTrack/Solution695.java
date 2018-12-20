package backTrack;

import org.junit.Test;


public class Solution695 {
    private int m,n;
    private boolean[][] visited;
    private int[][] d={{-1,0},{0,1},{1,0},{0,-1}};

    public int maxAreaOfIsland(int[][] grid) {
        m=grid.length;
        if (m==0){
            return 0;
        }
        n=grid[0].length;
        visited=new boolean[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]&&grid[i][j]==1){
                    res=Math.max(res,dfs(grid,i,j));
                }
            }
        }
        return res;

    }
    //返回的值就是[x,y]所在位置的岛屿的面积
    private int dfs(int[][] grid,int x,int y){
        visited[x][y]=true;
        int res=1;
        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            //满足此条件，说明寻找到一个位置，加入该岛屿
            //相邻位置为1的区域为岛屿
            if (inArea(newX,newY)&&!visited[newX][newY]&&grid[newX][newY]==1){
                res+=dfs(grid,newX,newY);
            }
        }
        return res;
    }
    //判断当前坐标是否在给定区域内
    private boolean inArea(int x,int y){
        return x>=0&&x<m&&y>=0&&y<n;
    }

    @Test
    public void test(){
        int[][] g={{0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}};
        int r = maxAreaOfIsland(g);
        System.out.println(r);
    }
}
