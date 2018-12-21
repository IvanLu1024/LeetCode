package dp;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Solution873 {
    public int lenLongestFibSubseq(int[] A) {
        //K：数值；V：下标
        Map<Integer,Integer> indexMap=new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            indexMap.put(A[i],i);
        }
        int res=0;
        int n =A.length;
        //表示从i到j的斐波那契子数列的长度
        int[][] memo=new int[n][n];

        for (int i = n-1; i >=0 ; i--) {
            for (int j = i; j <n ; j++) {
                if (indexMap.get(A[i]+A[j])!=null){
                    int k = A[i] + A[j];
                    int pos = indexMap.get(k);
                    memo[i][j]=memo[j][pos]+1;
                    res=Math.max(res,memo[i][j]);
                }else {
                    memo[i][j]=2;
                }
            }
        }
        return res;
    }
    @Test
    public void test(){
        int[] a={1,5};
        int res = lenLongestFibSubseq(a);
        System.out.println(res);
    }
}
