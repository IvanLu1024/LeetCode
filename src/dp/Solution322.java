package dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。
 *
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。

 示例 1:

 输入: coins = [1, 2, 5], amount = 11
 输出: 3
 解释: 11 = 5 + 5 + 1
 示例 2:

 输入: coins = [2], amount = 3
 输出: -1
 说明:
 你可以认为每种硬币的数量是无限的。
 *
 */
public class Solution322 {

    private int max_count;
    private int[] memo;
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        max_count=amount+1;
        if (n==0){
            return -1;
        }
        memo=new int[amount+1];
        Arrays.fill(memo,-1);
        int count=tryCoinChange(coins,amount);
        return count==max_count?-1:count;


    }
    private int tryCoinChange(int[] coins,int amount){
        if (amount==0){
            return 0;
        }
        if (memo[amount]!=-1){
            return memo[amount];
        }
        int res=max_count;
        for (int i = 0; i < coins.length; i++) {
            if (coins[i]<=amount){
                res=Math.min(res,1+tryCoinChange(coins,amount-coins[i]));
                memo[amount]=res;
            }
        }
        return res;
    }


    /**
     * 状态转移方程：C（i）=min{c(i),c(i-coin)+1}
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange1(int[] coins, int amount){
        int c=amount;
        memo=new int[c+1];
        int n=coins.length;
        memo[0]=0;
        //初始化，设置一个大于amount的值即可
        for (int i = 1; i <=c ; i++) {
                memo[i]=amount+1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <=c ; j++) {
                memo[j]=Math.min(memo[j],memo[j-coins[i]]+1);
            }
        }
        //若结果为大于amount表示没有结果
        return memo[c]==amount+1?-1:memo[c];
    }


    @Test
    public void test(){
        int[] c={2};
        int amount=3;
        int i = coinChange1(c, amount);
        System.out.println(i);
    }
}
