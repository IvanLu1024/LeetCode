package dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 */
public class Solution300 {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n==0){
            return 0;
        }
        //memo[i]:表示数组nums中以下标为i的元素为结尾的上升序列的长度
        int[] memo=new int[n];
        Arrays.fill(memo,1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j]<nums[i]){
                    memo[i]=Math.max(memo[i],memo[j]+1);
                }
            }
        }
        int max=1;
        for (int i = 0; i < n; i++) {
            max=Math.max(max,memo[i]);
        }
        return max;
    }

    //二分查找解法
    public int lengthOfLIS1(int[] nums){
        int maxLen=0;
        //存储着所有长度为i+1的递增子序列中, 那个序列最小的数字
        //memo[]必然为递增数组
        int[] memo=new int[nums.length];
        for (int num:nums){
            int l=0,h=maxLen;
            //在memo中通过二分查找寻找num应当放入的位置
            while (l<h){
                int mid=l+(h-l)/2;
                if (memo[mid]<num) l=mid+1; //在左边继续查找
                else h=mid;                 //在右边继续查找
            }
            //将对应的数字放入相应的位置上
            memo[h]=num;
            //表示num比所有已知递增序列的尾数都大，则数组拓展的时候
            if (l==maxLen){
                maxLen++;
            }
        }
        return maxLen;
    }
    @Test
    public void test(){
        int[] nums={10,9,2,5,3,7,101,18};
        int i = lengthOfLIS1(nums);
        System.out.println(i);
    }
}
