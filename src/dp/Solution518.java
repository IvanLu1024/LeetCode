package dp;

import org.junit.Test;

/**
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 *
 * 注意: 你可以假设
 *
 * 0 <= amount (总金额) <= 5000
 * 1 <= coin (硬币面额) <= 5000
 * 硬币种类不超过500种
 * 结果符合32位符号整数
 * 示例 1:
 *
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * 示例 2:
 *
 * 输入: amount = 3, coins = [2]
 * 输出: 0
 * 解释: 只用面额2的硬币不能凑成总金额3。
 * 示例 3:
 *
 * 输入: amount = 10, coins = [10]
 * 输出: 1
 *
 */
public class Solution518 {
    private int[][] memo;
    public int change(int amount, int[] coins) {
        memo=new int[coins.length][amount+1];
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j < amount + 1; j++) {
                memo[i][j]=-1;
            }
        }
        int res = tryChange(0, amount, coins);
        return res;
    }
    private int tryChange(int index,int amount,int[] coins){
        if (index==coins.length){
            if (amount==0){
                return 1;
            }else {
                return 0;
            }
        }
        if (memo[index][amount]!=-1){
            return memo[index][amount];
        }
        int count=0;
        for (int i = 0; i*coins[index] <= amount; i++) {
            count+=tryChange(index+1,amount-i*coins[index],coins);
        }
        memo[index][amount]=count;
        return count;
    }

    public int change1(int amount, int[] coins) {
        memo=new int[coins.length+1][amount+1];
        memo[coins.length][0]=1;
        for (int i = coins.length-1; i >=0 ; i--) {
            for (int j = 0; j <= amount; j++) {
                for (int k = 0; k*coins[i]<=j; k++) {
                    memo[i][j]+=memo[i+1][j-k*coins[i]];
                }
            }
        }
        return memo[0][amount];

    }

    //进一步优化
    public int change2(int amount, int[] coins) {
        int[] dp=new int[amount+1];
        //注意初始值的设置，dp[0]表示满足条件的解，计数
        dp[0]=1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                //j:表示金额
                dp[j]+=dp[j-coins[i]];
            }
        }
        return dp[amount];

    }
    @Test
    public void test(){
        int[] coins={1, 2, 5};
        int amount=5;
        int r = change2(amount, coins);
        System.out.println(r);
    }

}
