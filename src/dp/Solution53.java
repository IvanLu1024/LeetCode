package dp;

import org.junit.Test;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 */
public class Solution53 {
    //时间复杂度：O(n^2)
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n==0){
            return 0;
        }
        if (n==1){
            return nums[0];
        }
        int[] memo=new int[n];
        int sum=0;
        for (int i = 0; i < n; i++) {
            sum+=nums[i];
            memo[i]=sum;
        }
        int max=memo[n-1];
        for (int i = 0; i < n; i++) {
            max=Math.max(max,memo[i]);
            for (int j = i+1; j <n ; j++) {
                max=Math.max(max,memo[j]-memo[i]);
            }
        }
        return max;
    }

    public int maxSubArray1(int[] nums){
        //全局最大值
        int max=nums[0];
        //局部最大值
        int curMax=nums[0];
        for (int i = 1; i < nums.length; i++) {
            //继续累加
            if (curMax>=0){
                curMax+=nums[i];
            }else//curMax<0:重新在i位置选择连续子数组
                {
                curMax=nums[i];
            }
            //从局部最大值中选择出全局最大值
            max=Math.max(max,curMax);
        }
        return max;
    }
    @Test
    public void test(){
        int[] nums={-2,1,-3,4,-1,2,1,-5,4};
        int i = maxSubArray1(nums);
        System.out.println(i);
    }
}
