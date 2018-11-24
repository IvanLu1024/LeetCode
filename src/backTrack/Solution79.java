package backTrack;

import org.junit.Test;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。

 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

 示例:

 board =
 [
 ['A','B','C','E'],
 ['S','F','C','S'],
 ['A','D','E','E']
 ]

 给定 word = "ABCCED", 返回 true.
 给定 word = "SEE", 返回 true.
 给定 word = "ABCB", 返回 false.
 *
 */
public class Solution79 {
    private boolean[][] visited;
    //用来表示上右下左这四个方向
    private int[][] d={{-1,0},{0,1},{1,0},{0,-1}};
    private int m,n;


    public boolean exist(char[][] board, String word) {
        visited=new boolean[board.length][board[0].length];
        m=board.length;
        n=board[0].length;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (search(board,word,0,i,j)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean search(char[][] board,String word,int index,int startX,int startY ){
        if (index==word.length()-1){
            if (board[startX][startY]==word.charAt(index)) {
                System.out.println("found completed x:"+startX+" y:"+startY);
                return true;
            }
        }
        if (board[startX][startY]==word.charAt(index)){
            visited[startX][startY]=true;
            //按照上右下左（顺时针）的顺序搜索
            for (int i = 0; i < 4; i++) {
                int newStartX = startX + d[i][0];
                int newStartY = startY + d[i][1];
                System.out.println("seraching x:"+newStartX+" y:"+newStartY+" target:"+word.charAt(index));
                if (inArea(newStartX,newStartY)&&!visited[newStartX][newStartY]){
                    System.out.println("seraching x:"+newStartX+" y:"+newStartY+" current :"+board[newStartX][newStartY]);
                    if (search(board,word,index+1,newStartX,newStartY)){
                        return true;
                    }
                }
            }
            //回溯
            System.out.println("backTracking ... x:"+startX+" y:"+startY);
            visited[startX][startY]=false;
        }
        return false;

    }

    //判断当前坐标是否在给定区域内
    private boolean inArea(int x,int y){
        return x>=0&&x<m&&y>=0&&y<n;
    }

    @Test
    public void test(){
        char[][] board={{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word="SEE";
        boolean exist = exist(board, word);
        System.out.println(exist);
    }
}
