package array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 */
public class Solution34 {
    public int[] searchRange(int[] nums, int target) {
        int[] res={-1,-1};
        if (nums.length==0||nums==null){
            return res;
        }
        int last = lastOccurance(nums, target);
        //当target比数组中的最小值还要小的时候
        if (last<0||nums[last]!=target){
            return res;
        }
        int first = firstOccurance(nums, target);
        res[0]=first;
        res[1]=last;
        return res;
    }
    //寻找target出现的第一个位置
    private int firstOccurance(int[] nums,int target){
        int l=0,h=nums.length-1;
        while (l<=h){
            int mid=l+(h-l)/2;
            if (nums[mid]<target){
                l=mid+1;
            }else {
                h=mid-1;
            }
        }
        return l;
    }
    //寻找target出现的最后一个位置
    //注意一点，如果target比数组最小值还小，那么返回-1
    private int lastOccurance(int[] nums,int target){
        int l=0,h=nums.length-1;
        while (l<=h){
            int mid=l+(h-l)/2;
            if (nums[mid]>target){
                h=mid-1;
            }else {
                l=mid+1;
            }
        }
        //注意返回的是h，此时h<l
        return h;
    }

    @Test
    public void test(){
        int[] nums={5,7,7,8,8,10};
        int target=8;
        int[] range = searchRange(nums, target);
        System.out.println(range[0]+"---"+range[1]);

    }
}
