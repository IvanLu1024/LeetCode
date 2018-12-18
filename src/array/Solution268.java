package array;

import org.junit.Test;

/**
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 *
 * 示例 1:
 *
 * 输入: [3,0,1]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [9,6,4,2,3,5,7,0,1]
 * 输出: 8
 */
public class Solution268 {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        boolean[] flag=new boolean[n+1];
        for (int i = 0; i < n; i++) {
            flag[nums[i]]=true;
        }
        for (int i = 0; i <= n; i++) {
            if (!flag[i]){
                return i;
            }
        }
        return 0;
    }
    @Test
    public void test(){
        int[] nums={9,6,4,2,3,5,7,0,1};
        int i = missingNumber(nums);
        System.out.println(i);
    }
}
