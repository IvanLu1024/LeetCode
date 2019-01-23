package array;

import org.junit.Test;

import java.util.Arrays;

/**
 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

 示例:

 输入: [1,2,3,4]
 输出: [24,12,8,6]
 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。

 进阶：
 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）

 */
public class Solution238 {
    //时间复杂度：O(n^2)
    //暴力法
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        if (n==0){
            return res;
        }
        for (int i = 0; i < n; i++) {
            res[i] = 1;
            for (int j = 0; j < n; j++) {
                if (i==j){
                    continue;
                }
                res[i]*=nums[j];
            }
        }
        return res;
    }

    //时间复杂O（n）
    public int[] productExceptSelf1(int[] nums){
        int n = nums.length;
        int[] res = new int[n];
        if (n==0){
            return res;
        }
        //从左向右直到i-1的位置
        res[0]=1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i-1] * nums[i-1];
        }
        //从右向左直到i+1的位置
        int right=1;    //从右开始的累乘积
        for (int i = n-1; i >=0; i--) {
            res[i] *=right;
            right *=nums[i];
        }
        return res;
    }
    @Test
    public void test(){
        int[] nums={2,5,7,9};
        int[] res = productExceptSelf1(nums);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]+" ");
        }
    }
}
