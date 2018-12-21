package backTrack;

import org.junit.Test;
import utils.ArrayUtils;

public class Solution733 {
    private int m,n;
    private boolean[][] visited;
    private int[][] d={{-1,0},{0,1},{1,0},{0,-1}};
    private int color;
    private int newColor;

    //判断当前坐标是否在给定区域内
    private boolean inArea(int x,int y){
        return x>=0&&x<m&&y>=0&&y<n;
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        m=image.length;
        if (m==0){
            return image;
        }
        n=image[0].length;
        visited=new boolean[m][n];
        n=image[0].length;
        color=image[sr][sc];
        this.newColor=newColor;
        generate(image,sr,sc);
        return image;
    }
    private void generate(int[][] image,int x,int y){
            if (image[x][y]==color){
                image[x][y]=newColor;
            }
        visited[x][y]=true;

        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if (inArea(newX,newY)&&!visited[newX][newY]&&image[newX][newY]==color){
                generate(image,newX,newY);
            }
        }
    }
    @Test
    public void test(){
        /*[1,1,1],[1,1,0],[1,0,1]*/
        /*[[0,0,0],[1,0,0]]
1
0
2*/
        int[][] image={{0,0,0},{1,0,0}};
        int sr=1;
        int sc=0;
        int newColor=2;
        int[][] res = floodFill(image, sr, sc, newColor);
        Integer[][] r=new Integer[res.length][res[0].length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                r[i][j]=res[i][j];
            }
        }
        ArrayUtils.print2DimenArray(r);
    }
}
