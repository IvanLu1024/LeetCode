package array;

import org.junit.Test;

/**
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 *
 * 示例 1:
 *
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 * 注意：
 *
 * 输入的数组只包含 0 和1。
 * 输入数组的长度是正整数，且不超过 10,000。
 */
public class Solution485 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n =nums.length;
        if (n==0){
            return 0;
        }
        if (n==1){
            if (nums[0]==1){
                return 1;
            }else {
                return 0;
            }
        }
        int count=1;
        int max=0;
        if (nums[0]==1){
            max=count;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i]==1){
                if (nums[i]==nums[i-1]) {
                    count++;
                }else {
                    count=1;
                }
            }else {
                count=0;
            }
            max=Math.max(max,count);
        }
        return max;
    }

    public int findMaxConsecutiveOnes1(int[] nums){
        int n =nums.length;
        if (n==0){
            return 0;
        }
        int count=0;
        int max=0;
        for (int i = 0; i < n; i++) {
            if (nums[i]==1){
                count++;
            }else {
                max=Math.max(max,count);
                count=0;
            }
        }
        return max>count?max:count;
    }
    @Test
    public void test(){
        int[] nums={1,1,0,1,1,1};
        int res = findMaxConsecutiveOnes1(nums);
        System.out.println(res);
    }
}
