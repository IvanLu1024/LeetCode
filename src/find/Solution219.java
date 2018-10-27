package find;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，
 * 并且 i 和 j 的差的绝对值最大为 k。

     示例 1:

     输入: nums = [1,2,3,1], k = 3
     输出: true
     示例 2:

     输入: nums = [1,0,1,1], k = 1
     输出: true
     示例 3:

     输入: nums = [1,2,3,1,2,3], k = 2
     输出: false
 *
 * @author
 * @create 2018-10-27 14:20
 **/
public class Solution219 {
    //使用滑动窗口+查找表的方法
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(k==0){
            return false;
        }
        if(k>nums.length){
            k=nums.length;
        }
        int l=0;
        Set<Integer> record=new HashSet<>();
        for (int i = 0; i < k; i++) {
            if (!record.contains(nums[i])){
                record.add(nums[i]);
            }else {
                return true;
            }
        }
        while (l<nums.length-k){
                if (record.contains(nums[l+k])){
                    return true;
                }else {
                    record.remove(nums[l]);
                    record.add(nums[l+k]);
                    l++;
                }
        }
        return false;
    }
    @Test
    public void test(){
         int[] nums={1,2,3,1,2,3};
         int k=2;
        boolean res = containsNearbyDuplicate(nums, k);
        System.out.println(res);
    }
}
