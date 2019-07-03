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

    public int missingNumber1(int[] nums) {
        if(nums==null||nums.length==0){
            return 0;
        }

        int n=nums.length;
        //预期结果为等差数列：{1+2+...+n}的结果
        int res=n*(n+1)/2;
        //缺失的数字就是和预期结果之间的差
        for(int i=0;i<n;i++){
            res-=nums[i];
        }
        return res;
    }
    @Test
    public void test(){
        /*{3,0,1}
        * {9,6,4,2,3,5,7,0,1}*/
        int[] nums={3,0,1,4};
        int i = missingNumber1(nums);
        System.out.println(i);
    }
}
