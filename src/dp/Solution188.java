package dp;

import org.junit.Test;

import java.util.Arrays;

public class Solution188 {
    //122题+123题
    public int maxProfit(int k, int[] prices) {
        int n=prices.length;
        if (n==0||n==1||k==0){
            return 0;
        }
        //当2k>n,相当于可以进行不限次数的交易
        //退化为122题
        if (k>n/2){
            return maxProfit(prices);
        }
        int[] holds=new int[k];
        int[] unholds=new int[k];
        Arrays.fill(holds,Integer.MIN_VALUE);
        Arrays.fill(unholds,0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                unholds[j] = Math.max(unholds[j], holds[j] + prices[i]);
                if (j==0){
                    holds[j]=Math.max(holds[j],-prices[i]);
                }else {
                    holds[j] = Math.max(holds[j], unholds[j-1] - prices[i]);
                }
            }
        }
        return Math.max(unholds[k-1],holds[k-1]);
    }

    private int maxProfit(int[] prices){
        int n=prices.length;
        if (n==0||n==1){
            return 0;
        }
        int res=0;
        for (int i = 1; i <n ; i++) {
            if (prices[i]>prices[i-1]){
                res+=prices[i]-prices[i-1];
            }
        }
        return res;
    }
    @Test
    public void test(){
        int[] p={3,3,5,0,0,3,1,4};
        int k=2;
        int r = maxProfit(k, p);
        System.out.println(r);
    }
}
