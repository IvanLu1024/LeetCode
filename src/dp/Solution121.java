package dp;

import org.junit.Test;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。

 注意你不能在买入股票前卖出股票。

 示例 1:

 输入: [7,1,5,3,6,4]
 输出: 5
 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 示例 2:

 输入: [7,6,4,3,1]
 输出: 0
 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * @author
 * @create 2018-12-03 21:04
 **/
public class Solution121 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n==0){
            return 0;
        }
        int[] memo=new int[n];
        for (int i = 1; i <n ; i++) {
            for (int j = 0; j < i; j++) {
                memo[i]=Math.max(memo[i],prices[i]-prices[j]);
            }
        }
        int res=0;
        for (int i = 0; i <n ; i++) {
            res=Math.max(res,memo[i]);
        }
        return res;
    }

    public int maxProfit1(int[] prices) {
        int n = prices.length;
        if (n==0){
            return 0;
        }
        int max=0;
        for (int i = 1; i <n ; i++) {
            for (int j = 0; j < i; j++) {
                max=Math.max(max,prices[i]-prices[j]);
            }
        }
        return max;
    }
    @Test
    public void test(){
        int[] p={7,1,5,3,6,4};
        int r = maxProfit1(p);
        System.out.println(r);

    }
}
