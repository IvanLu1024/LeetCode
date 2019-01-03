package array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 *
 * 示例 1:
 *
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 *
 */
public class Solution169 {
    //排序的解法
    public int majorityElement(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        return nums[n/2];
    }

    ///// Boyer-Moore Voting Algorithm(摩尔投票算法)
    public int majorityElement1(int[] nums){
        if (nums.length==0||nums==null)
            return 0;
        int majortiy=nums[0];
        int count=1;
        //遍历数组找到出现次数最多的元素
        for (int i=1;i<nums.length;i++){
            if (nums[i]==majortiy)
                count++;
            else
                count--;
            //此时说明，数量最多的元素不存在或者不超过半数
            if (count==0){
                majortiy=nums[i];
                count=1;
            }

        }
        //将计数器清零
        count=0;
        //再次遍历数组，统计出现最多元素的数量
        for (int e:nums){
            if (e==majortiy){
                count++;
            }
        }
        if (count>nums.length/2)
            return majortiy;
        else return 0;

    }
    @Test
    public void test(){
        int[] nums={2,2,1,1,1,2,2};
        int i = majorityElement(nums);
        System.out.println(i);

    }
}
