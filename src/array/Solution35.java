package array;

import org.junit.Test;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: [1,3,5,6], 2
 * 输出: 1
 */
public class Solution35 {
    public int searchInsert(int[] nums, int target) {
        int l=0,h=nums.length-1;
        while (l<h){
            int mid=l+(h-l)/2;
            if (nums[mid]==target){
                return mid;
            }else if (nums[mid]>target){
                h=mid-1;
            }else {
                l=mid+1;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]>target){
                return i;
            }
        }
        return nums.length;
    }
    @Test
    public void test(){
        int[] nums={1};
        int t=1;
        int i = searchInsert(nums, t);
        System.out.println(i);


    }
}
