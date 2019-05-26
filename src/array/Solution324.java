package array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author Ivan 14:06
 * @Description TODO
 */
public class Solution324 {
    public void wiggleSort(int[] nums) {
        int[] temp = nums.clone();
        //首先将数组排序
        Arrays.sort(temp);
        int n = nums.length;
        int mid=(n+1)/2,j=n;
        for (int i = 0; i < n; i++) {
            //将大于中位数的数从大到小放到奇数位
            //将小于中位数的数从大到小放到偶数位
            if (i%2!=0){
                nums[i]=temp[--j];
            }else {
                nums[i]=temp[--mid];
            }
        }

    }
    @Test
    public void test(){
        int[] nums={1, 3, 2, 2, 3, 1};
        wiggleSort(nums);
        for (int i:nums){
            System.out.print(i+",");
        }
    }
}
