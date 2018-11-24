package backTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 输入: 4
 输出: [
 [".Q..",  // 解法 1
 "...Q",
 "Q...",
 "..Q."],

 ["..Q.",  // 解法 2
 "Q...",
 "...Q",
 ".Q.."]
 ]
 解释: 4 皇后问题存在两个不同的解法。
 *
 */
public class Solution51 {
    private List<List<String>> res=new ArrayList<>();
    private boolean[] col,diag1,diag2;

    public List<List<String>> solveNQueens(int n) {
        if (n==0){
            return res;
        }
        col=new boolean[n];
        diag1 = new boolean[2 * n - 1];
        diag2 = new boolean[2 * n - 1];
        List<Integer> row=new LinkedList<>();
        putQueue(n,0,row);
        return res;


    }

    // 尝试在一个n皇后问题中, 摆放第index行的皇后位置
    private void putQueue(int n,int index,List<Integer> row){
        if (index==n){
            res.add(new ArrayList<>(generateBoard(n,row)));
        }
        for (int i = 0; i < n; i++) {
            // 尝试将第index行的皇后摆放在第i列
            if (!col[i]&&!diag1[index+i]&&!diag2[index-i+n-1]){
                col[i]=true;
                diag1[index+i]=true;
                diag2[index-i+n-1]=true;
                row.add(i);
                putQueue(n,index+1,row);
                col[i]=false;
                diag1[index+i]=false;
                diag2[index-i+n-1]=false;
                row.remove(row.size()-1);
            }
        }
    }

    private List<String> generateBoard(int n, List<Integer> row) {

        assert row.size()==n;

        List<String> board=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] chars = new char[n];
            Arrays.fill(chars,'.');
            chars[row.get(i)]='Q';
            board.add(new String(chars));
        }
        return board;
    }
}
