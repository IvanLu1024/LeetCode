package backTrack;

import org.junit.Test;

public class Solution37 {
    private boolean[][] col;    //用于记录每一行中出现的数字（0-9）
    private boolean[][] row;    //用于记录每一列中出现的数字
    private boolean[][] block;  //用于记录每3*3的宫中出现的数字
    public void solveSudoku(char[][] board) {
        col=new boolean[9][10];
        row=new boolean[9][10];
        block=new boolean[9][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j]=='.'){
                    continue;
                }
                col[i][board[i][j]-'0']=true;
                row[j][board[i][j]-'0']=true;
                block[i/3*3+j/3][board[i][j]-'0']=true;
            }
        }

        for (int i = 0; i < 81; i++) {
            if (board[i/9][i%9]=='.'){
                if (dfs(board,i,col,row,block)){
                    continue;
                }
            }
        }

    }

    private boolean dfs(char[][] board,int index,boolean[][] col,boolean[][] row,boolean[][] block){
        if (index==81){
            return true;
        }
        int start=index+1;

        for (; start <81 ; start++) {
            if (board[start/9][start%9]=='.'){
                break;
            }
        }
        int x=index/9,y=index%9;
        for (int i = 1; i <= 9; i++) {
            if (!col[x][i]&&!row[y][i]&&!block[x/3*3+y/3][i]){
                col[x][i]=true;
                row[y][i]=true;
                block[x/3*3+y/3][i]=true;
                board[x][y]=(char)('0'+i);
                if (dfs(board,start,col,row,block)){
                    return true;
                }
                col[x][i]=false;
                row[y][i]=false;
                block[x/3*3+y/3][i]=false;
                board[x][y]='.';
            }
        }
        return false;
    }
    @Test
    public void test(){

    }
}
