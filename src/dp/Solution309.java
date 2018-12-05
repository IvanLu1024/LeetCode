package dp;

import org.junit.Test;

/**
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 *
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 */
public class Solution309 {
    public int maxProfit(int[] prices) {
        int n=prices.length;
        if (n<=1){
            return 0;
        }
        int[] hold=new int[n];  //hold[i]:第i天持有股票的最大收益
        int[] unhold=new int[n];    //unhold[i]:第i天不持有股票的最大收益


        hold[0]=-prices[0];
        hold[1]=Math.max(hold[0],-prices[1]);
        unhold[1]=Math.max(unhold[0],hold[0]+prices[1]);
        for (int i = 2; i < n; i++) {
            //没有买入和买入
            hold[i] = Math.max(hold[i-1], unhold[i-2] - prices[i]);
            //没有卖出和卖出
            unhold[i] = Math.max(unhold[i-1], hold[i-1] + prices[i]);

        }
        return unhold[n-1];
    }
    @Test
    public void test(){
        int[] p={1,2,3,0,2};
        int r = maxProfit(p);
        System.out.println(r);
    }
}
