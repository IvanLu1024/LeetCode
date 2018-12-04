package dp;

import org.junit.Test;

import java.util.Arrays;

/**
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 *
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

 示例 1:

 输入: [1,2,3,1]
 输出: 4
 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 偷窃到的最高金额 = 1 + 3 = 4 。
 示例 2:

 输入: [2,7,9,3,1]
 输出: 12
 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 */
public class Solution198 {
    private int[] memo;
    public int rob(int[] nums) {
        int n = nums.length;
        if (n==0){
            return 0;
        }
        memo=new int[n];
        Arrays.fill(memo,-1);
        return tryRob(nums,0);

    }

    private int tryRob(int[] nums,int index){
        if (index>=nums.length){
            return 0;
        }
        int max=0;
        if (memo[index]!=-1){
            return memo[index];
        }
        for (int i = index; i < nums.length; i++) {
            max=Math.max(max,nums[i]+tryRob(nums,i+2));
        }
        memo[index]=max;
        return max;
    }

    public int rob1(int[] nums) {
        int n=nums.length;
        if (n==0){
            return 0;
        }
        //memo[i]是考虑nums[i...n-1]抢劫的最大收益
        memo=new int[n];
        Arrays.fill(memo,-1);
        memo[n-1]=nums[n-1];
        /**
         * 状态转移方程：
         * f(n-1)=max{ v(0)+f(2), v(1)+f(3),v(2)+f(4),...，v(n-3)+f(n-1),v(n-2),v(n-1)}
         */
        for (int i = n-2; i >=0 ; i--) {
            //抢劫nums[i,n-1]
            for (int j = i; j <n ; j++) {
                memo[i]=Math.max(memo[i],nums[j]+(j+2<n?memo[j+2]:0));
            }
        }
        return memo[0];

    }
    public int rob2(int[] nums) {
        int n=nums.length;
        if (n==0){
            return 0;
        }
        //memo[i]是考虑nums[i...n-1]抢劫的最大收益
        memo=new int[n];
        Arrays.fill(memo,-1);
        memo[0]=nums[0];
        /**
         * 状态转移方程：
         * f(n-1)=max{ v(n-1)+f(n-3), v(n-2)+f(n-4),...,v(3)+f(1),v(2),v(1)}
         */
        for (int i = 1; i <= n - 1; i++) {
            //抢劫nums[0,i]
            for (int j = i; j >=0 ; j--) {
                memo[i]=Math.max(memo[i],nums[j]+(j-2>=0?memo[j-2]:0));
            }
        }
        return memo[n-1];

    }

    public int rob3(int[] nums){
        int n = nums.length;
        if (n==0){
            return 0;
        }
        memo=new int[n];
        //Arrays.fill(memo,-1);
        memo[0]=nums[0];
        for (int i = 1; i < n; i++) {
            memo[i]=Math.max(memo[i-1],nums[i]+(i-2>=0?memo[i-2]:0));
        }
        return memo[n-1];
    }

    @Test
    public void test(){
        int[] nums={1,2,3,1};
        int rob = rob2(nums);
        System.out.println(rob);
    }
}
