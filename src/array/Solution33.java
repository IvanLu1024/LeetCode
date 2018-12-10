package array;

import org.junit.Test;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 */
public class Solution33 {
    public int search(int[] nums, int target) {
        if (nums==null||nums.length==0){
            return -1;
        }
        int l=0,h=nums.length-1;
        while (l<=h){
            int mid=l+(h-l)/2;
            if (nums[mid]==target){
                return mid;
            }
            //判断nums[l,mid]是否为递增序列
            if (nums[l]<=nums[mid]){
                //如果目标值在这个范围内，注意要使用等于，因为边界值可能是目标值
                if (nums[l]<=target&&target<=nums[mid]){
                    h=mid-1;
                }else {
                    l=mid+1;
                }
            }else {
                if (nums[mid]<=target&&target<=nums[h]){
                    l=mid+1;
                }else {
                    h=mid-1;
                }
            }

        }

        return -1;
    }
    @Test
    public void test(){
        int[] nums={5,1,3};
        int target=3;
        int index = search(nums, target);
        System.out.println(index);

    }

}
