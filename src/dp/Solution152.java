package dp;

import org.junit.Test;

/**
 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。

 示例 1:

 输入: [2,3,-2,4]
 输出: 6
 解释: 子数组 [2,3] 有最大乘积 6。
 示例 2:

 输入: [-2,0,-1]
 输出: 0
 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。

 */
public class Solution152 {
    public int maxProduct(int[] nums) {
        if (nums==null||nums.length==0){
            return 0;
        }
        int max=1,min=1;
        int res=Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            //若当前数值为负数则将最大值和最小值数值交换，以便得到更大的乘积
            if (nums[i]<0){
                max=max^min;
                min=max^min;
                max=max^min;
            }
            max=Math.max(max*nums[i],nums[i]);
            min=Math.min(min*nums[i],nums[i]);

            res=Math.max(res,max);
        }
        return res;
    }
    @Test
    public void test(){
        int[] nums={2,3,-2,4};
        int i = maxProduct(nums);
        System.out.println(i);
    }
}
