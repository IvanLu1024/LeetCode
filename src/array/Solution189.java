package array;

import org.junit.Test;
import utils.ArrayUtils;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 *
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 说明:
 *
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的原地算法。
 *
 */
public class Solution189 {
    //暴力法
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k=k%n;
        for (int i = 0; i < k; i++) {
            int temp=nums[n-1];
            for (int j = n-1; j >=1; j--) {
                nums[j]=nums[j-1];
            }
            nums[0]=temp;
        }
    }
    //通过三次翻转
    public void rotate1(int[] nums, int k){
        int n = nums.length;
        k=k%n;
        //将整个数组翻转
        reverse(nums,0,n-1);
        //将前k个元素翻转
        reverse(nums,0,k-1);
        //将从k到数组末尾翻转
        reverse(nums,k,n-1);

    }
    //从数组下标从l到r的部分进行翻转
    private void reverse(int[] nums,int l,int r){
        while (l<r){
            int t=nums[r];
            nums[r--]=nums[l];
            nums[l++]=t;
        }
    }


    @Test
    public void test(){
        int[] nums={-1,-100,3,99};
        int k=2;
        rotate1(nums,k);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]+",");
        }
    }
}
