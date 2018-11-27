package backTrack;


import org.junit.Test;

public class Solution52 {
    private int res=0;
    private boolean[] col,diag1,diag2;

    public int totalNQueens(int n) {
        if (n==0){
            return res;
        }
        col=new boolean[n];
        diag1=new boolean[2*n-1];
        diag2=new boolean[2*n-1];
        putQueens(n,0);
        return res;
    }
    private void putQueens(int n,int index){
        if (index==n){
            res++;
        }
        for (int i = 0; i < n; i++) {
            if (!col[i]&&!diag1[i+index]&&!diag2[i-index+n-1]){
                col[i]=true;
                diag1[i+index]=true;
                diag2[i-index+n-1]=true;
                putQueens(n,index+1);
                col[i]=false;
                diag1[i+index]=false;
                diag2[i-index+n-1]=false;
            }
        }
    }
    @Test
    public void test(){
        int n=4;
        int i = totalNQueens(n);
        System.out.println(i);
    }

}
