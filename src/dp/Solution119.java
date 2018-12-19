package dp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution119 {
    public List<Integer> getRow(int rowIndex) {
        int[][] memo=new int[rowIndex+1][rowIndex+1];
        List<Integer> res=new ArrayList<>();
        memo[0][0]=1;
        if (rowIndex<0){
            return res;
        }
        for (int i = 1; i <= rowIndex; i++) {
            memo[i][0]=1;
            for (int j = 1; j <i ; j++) {
                memo[i][j]=memo[i-1][j-1]+memo[i-1][j];
            }
            memo[i][i]=1;
        }
        for (int i = 0; i <= rowIndex; i++) {
            res.add(memo[rowIndex][i]);
        }

        return res;
    }

    //优化空间复杂度
    public List<Integer> getRow1(int rowIndex) {
        int[] memo=new int[rowIndex+1];
        List<Integer> res=new ArrayList<>();
        memo[0]=1;
        if (rowIndex<0){
            return res;
        }
        for (int i = 1; i <= rowIndex; i++) {
            //必须从右到左进行递推，不然会造成中间结果的丢失
            for (int j = i; j >=1 ; j--) {
                memo[j]=memo[j-1]+memo[j];
            }
        }
        for (int i = 0; i <= rowIndex; i++) {
            res.add(memo[i]);
        }

        return res;
    }
    @Test
    public void test(){
        int rowIndex=3;
        List<Integer> row = getRow1(rowIndex);
        System.out.println(row);
    }
}
