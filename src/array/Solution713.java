package array;

import org.junit.Test;

/**
 * 给定一个正整数数组 nums。

 找出该数组内乘积小于 k 的连续的子数组的个数。

 示例 1:

 输入: nums = [10,5,2,6], k = 100
 输出: 8
 解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
 说明:

 0 < nums.length <= 50000
 0 < nums[i] < 1000
 0 <= k < 10^6
 */
public class Solution713 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums==null||nums.length==0){
            return 0;
        }
        //滑动窗口是nums[l,r),初始情况为0
        int l=0,r=0;
        int n=nums.length;
        int count=0;
        int p=1;
        while (l<n&&l<=r){
            if (r<n&&p*nums[r]<k){
                p*=nums[r];
                r++;
            }else if (l==r){
                l++;
                r++;
            }else {
                //统计满足条件子数组
                count+=r-l;
                p/=nums[l];
                l++;
            }
        }
        return count;

    }
    private boolean isLessThanK(int[] nums,int l,int r,int k){
        int res=1;
        for (int i = l; i <= r; i++) {
            res*=nums[i];
            if (res>=k){
                return false;
            }
        }
        return true;
    }
    @Test
    public void test(){
        int[] nums={10,5,2,6};
        int k=100;
        int res = numSubarrayProductLessThanK(nums, k);
        System.out.println(res);
    }
}
