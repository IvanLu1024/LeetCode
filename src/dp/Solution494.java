package dp;

import org.junit.Test;

/**
 *给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。
 *
 * 现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 *
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 * 示例 1:
 *
 * 输入: nums: [1, 1, 1, 1, 1], S: 3
 * 输出: 5
 * 解释:
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * 一共有5种方法让最终目标和为3。
 * 注意:
 *
 * 数组的长度不会超过20，并且数组中的值全为正数。
 * 初始的数组的和不会超过1000。
 * 保证返回的最终结果为32位整数。
 *
 */
public class Solution494 {
    /**
     *  思路：
     *  假设原数组为nums，目标值为S，那么原数组必然可以分成两个部分：
     *  一个部分里面的元素前面需要加-，即运算的时候应该是做减法，另一个部分里面的元素前面需要加+，即运算的时候应该是做加法；
     *  我们将做加法部分的数组记为P，做减法部分的数组记为N，
     *  举个例子，例如S = {1，2，3，4，5}，S = 3，那么有一种可以是1-2+3-4+5，即P = {1，3，5}，N = {2，4}；
     *  于是我们可以知道：S = sum(P) - sum(N)；
     *  那么sum(P) + sum(N) + sum(P) - sum(N) = sum(nums) + S = 2sum(P)；
     *  那么sum(P) = [S + sum(nums)] / 2； []表示向下取整
     *
     *  这样转化为0-1背包问题，其中背包的容量为sum(P)
     *
     */
    public int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
        if (n==0){
            return 0;
        }
        int sum=0;
        for (int i = 0; i < n; i++) {
            sum+=nums[i];
        }
        if (sum < S || (sum + S) % 2 != 0) {
            return 0;
        }
        int p=(sum+S)/2;
        //memo[i]:和为i最多有多少种方式
        int[] memo=new int[p+1];
        //和为0的情况只有一种，那就是所有元素均不取
        memo[0]=1;
        //对于每一个元素都有不取和取两种选择
        for(int i=0;i<n;i++){
            for(int j=p;j>=nums[i];j--){
                memo[j]=memo[j]+ memo[j-nums[i]];
            }
        }
        return memo[p];
    }
    @Test
    public void test(){
        int[] nums={1, 1, 1, 1, 1};
        int S=3;
        String s="";
        s.toCharArray();
        int res = findTargetSumWays(nums, S);
        System.out.println(res);
    }
}
