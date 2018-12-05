package dp;

import org.junit.Test;

/**
 * @author
 * @create 2018-12-05 21:44
 **/
public class Solution123 {
    public int maxProfit(int[] prices) {
        int n=prices.length;
        if (n==0||n==1){
            return 0;
        }
        //第一次交易
        int hold1=Integer.MIN_VALUE;
        int unhold1=0;
        //第二次交易
        int hold2=Integer.MIN_VALUE;
        int unhold2=0;
        for (int i = 0; i < n; i++) {
            hold1=Math.max(hold1,-prices[i]);
            unhold1=Math.max(unhold1,hold1+prices[i]);
            hold2=Math.max(hold2,unhold1-prices[i]);
            unhold2=Math.max(unhold2,hold2+prices[i]);
        }
        return unhold2;
    }

    @Test
    public void test(){
        int[] p={3,3,5,0,0,3,1,4};
        int r = maxProfit(p);
        System.out.println(r);
    }
}
