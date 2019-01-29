package array;

import org.junit.Test;

/**
 假设按照升序排序的数组在预先未知的某个点上进行了旋转。

 ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。

 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。

 示例 1:

 输入: nums = [2,5,6,0,0,1,2], target = 0
 输出: true
 示例 2:

 输入: nums = [2,5,6,0,0,1,2], target = 3
 输出: false
 进阶:

 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。

 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？


 * @create 2019-01-29 21:49
 **/
public class Solution81 {
    //和33题是类似的
    public boolean search(int[] nums, int target) {
        if (nums==null||nums.length==0){
            return false;
        }
        int l=0,h=nums.length-1;
        while (l<=h){
            //两边夹逼去重操作
            if (l!=h&&nums[l]==nums[h]){
                l++;
                while (l!=h&&nums[l]==nums[l-1]) l++;
                while (l!=h&&nums[h]==nums[h-1]) h--;
            }
            int mid = l+(h-l)/2;
            if (nums[mid]==target){
                return true;
            }
            //nums[l,mid]为递增区间
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
        return false;
    }
    @Test
    public void test(){
        int[] nums={1,1,1,2};
        int target=2;
        boolean flag = search(nums, target);
        System.out.println(flag);
    }
}
