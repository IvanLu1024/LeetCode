package array;

import org.junit.Test;

/**
 * 峰值元素是指其值大于左右相邻值的元素。
 *
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 *
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 *
 * 你可以假设 nums[-1] = nums[n] = -∞。
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2:
 *
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 * 说明:
 *
 * 你的解法应该是 O(logN) 时间复杂度的。
 *
 */
public class Solution162 {
    public int findPeakElement(int[] nums) {
        int l=0,r=nums.length-1;
        int mid=l+(r-l)/2;
        while (l<=r){
            //当搜索区域只有一个元素的时候直接返回
            if (l==r){
                return l;
            }
            //将搜索区域移动到较大的区域中搜索
            if (nums[mid]<nums[mid+1]){
                l=mid+1;
            }else {
                r=mid;
            }
            mid=l+(r-l)/2;
        }
        return mid;
    }

    public int findPeakElement1(int[] nums){
        for (int i = 1; i < nums.length-1; i++) {
            if (nums[i]>nums[i-1]&&nums[i]>nums[i+1]){
                return i;
            }
        }
        return nums[0]>nums[nums.length-1]?0:nums.length-1;
    }

    @Test
    public void test(){
        int[] nums={2,1};
        int i = findPeakElement(nums);
        System.out.println(i);
    }
}
