package array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 示例：
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
   输出: 5
 *
 */
public class Solution215 {

    public int findKthLargest(int[] nums, int k) {

        k=nums.length-k;
        int l=0,h=nums.length-1;
        while (l<h){
            int pos = partition(nums, l, h);
            if (pos>k)
                h=pos-1;
            else if (pos<k)
                l=pos+1;
            else break;

        }
        return nums[k];



    }


    private int partition(int[] nums,int l,int h){
        int i=l+1,j=h;
        int pivot=nums[l];
        while (true){
            while (nums[i]<pivot&&i!=h)
                i++;
            while (nums[j]>=pivot&&j!=l)
                j--;
            if (i>=j)
                break;
            swap(nums,i,j);

        }
        swap(nums,l,j);
        return j;

    }

    private void swap(int[] nums,int i,int j){
        int t=nums[i];
        nums[i]=nums[j];
        nums[j]=t;
    }

    public int findKthLargest1(int[] nums, int k) {
        int n=nums.length;

        Arrays.sort(nums);

        return nums[n-k];

    }

    @Test
    public void test(){
        int[] nums = {3, 2, 1, 5, 6, 4};
        int kthLargest = findKthLargest1(nums, 2);
        System.out.println(kthLargest);

    }
}
