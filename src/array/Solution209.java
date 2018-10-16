package array;

import org.junit.Test;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。
 *
 * 如果不存在符合条件的连续子数组，返回 0。
 示例:

 输入: s = 7, nums = [2,3,1,2,4,3]
 输出: 2
 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 *
 */
public class Solution209 {

    public int minSubArrayLen(int s, int[] nums) {
        int i=0,j=-1;//滑动窗口num[i,j]，由于初始化的时候没有数值，所以j=-1
        int sum=0;
        int minCount=nums.length+1;
        while (i<nums.length){
            if (sum<s&&j<nums.length-1){
                sum+=nums[++j];
            }else {
                sum-=nums[i++];
            }
            if (sum>=s){
                if (j-i+1<minCount)//由于是nums[i,j]，前闭后闭区间
                    minCount=j-i+1;
            }
        }
        if (minCount==nums.length+1)
            return 0;
        return minCount;

    }
    @Test
    public void test(){
        int[] nums = {2,3,1,2,4,3};
        int i = minSubArrayLen(9, nums);
        System.out.println(i);

    }
}
