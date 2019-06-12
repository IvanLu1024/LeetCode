package dp;

import org.junit.Test;

import java.util.Arrays;

/**
 *给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。

 示例:

 nums = [1, 2, 3]
 target = 4

 所有可能的组合为：
 (1, 1, 1, 1)
 (1, 1, 2)
 (1, 2, 1)
 (1, 3)
 (2, 1, 1)
 (2, 2)
 (3, 1)

 请注意，顺序不同的序列被视作不同的组合。

 因此输出为 7。
 进阶：
 如果给定的数组中含有负数会怎么样？
 问题会产生什么变化？
 我们需要在题目中添加什么限制来允许负数的出现？

 *
 */
public class Solution377 {
    private int[] memo;
    public int combinationSum4(int[] nums, int target) {
        memo=new int[target+1];
        Arrays.fill(memo,-1);
        return findCombinationSum(nums,target);
    }

    private int findCombinationSum(int[] nums,int target){
        if (target==0){
            return 1;
        }
        if (memo[target]!=-1&&target>=0){
            return memo[target];
        }
        int count=0;
        for (int i = 0; i < nums.length; i++) {
            if (target>=nums[i]){
                count+=findCombinationSum(nums,target-nums[i]);

            }
        }
        memo[target]=count;
        return count;
    }

    private int[] dp;
    public int combinationSum41(int[] nums, int target){
        int c=target;
        int n=nums.length;
        if (n==0){
            return 0;
        }
        dp=new int[c+1];
        dp[0]=1;

        for (int i = 1; i <=c ; i++) {
            for (int j = 0; j < n; j++) {
                if (i>=nums[j]){
                    dp[i]+=dp[i-nums[j]];
                }
            }
        }

        return dp[c];

    }
    @Test
    public void test(){
        int[] nums={1,2,3};
        int target=4;
        int res = combinationSum41(nums, target);
        String s="";
        s.length();
        System.out.println(res);
    }
}
