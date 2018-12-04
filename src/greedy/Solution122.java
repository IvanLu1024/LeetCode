package greedy;

import org.junit.Test;

public class Solution122 {
    //贪心策略：只要有上升就进行交易
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n==0||n==1){
            return 0;
        }
        int max=0;
        for (int i = 1; i < n; i++) {
            if (prices[i]>prices[i-1]){
                max+=prices[i]-prices[i-1];
            }
        }
        return max;
    }
    @Test
    public void tset(){
        int[] p={7,1,5,3,6,4};
        int r = maxProfit(p);
        System.out.println(r);
    }
}
