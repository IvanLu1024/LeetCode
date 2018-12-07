package find;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。
 *
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 *
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */
public class Solution16 {
    public int threeSumClosest(int[] nums, int target) {
        int min=Integer.MAX_VALUE;
        int res=Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int l=i+1,h=nums.length-1;
            int v1=nums[i];
            while (l<h){
                if (nums[l]+nums[h]+v1==target){
                    return nums[l]+nums[h]+v1;
                }else {
                    int d = Math.abs(target - v1 - nums[l] - nums[h]);
                    if (d < min) {
                        res = v1 + nums[l] + nums[h];
                        min = d;
                    }
                    if (nums[l]+nums[h]+v1>target){
                        h--;
                    }
                    if (nums[l]+nums[h]+v1<target)
                    l++;
                }
            }
        }
        return res;
    }
    @Test
    public void test(){
        int[] nums={-1,0,1,1,55};
        int t=3;
        int res = threeSumClosest(nums, t);
        System.out.println(res);
    }
}
