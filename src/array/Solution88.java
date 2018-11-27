package array;

import org.junit.Test;

/**
 * 示例:

 输入:
 nums1 = [1,2,3,0,0,0], m = 3
 nums2 = [2,5,6],       n = 3

 输出: [1,2,2,3,5,6]
 *
 */

public class Solution88 {
    private int[] aux;


    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums2.length==0||nums1.length==0)
            return;
        if (nums1.length==1)
            nums1[0]=nums2[0];

        int index1=m-1,index2=n-1;
        int last=m+n-1;


        while (index1>=0&&index2>=0){
            if (nums1[index1]<nums2[index2])
                nums1[last--]=nums2[index2--];
            else
                nums1[last--]=nums1[index1--];
        }
        //当index2<0时，说明nums2数组已经排序结束，剩余的nums1数组元素保持初始位置

        //当index1<0,则说明num1的元素都取完了，那剩下的num2的元素可一次全部写进nums1。
        while (index2>=0){
            nums1[last--]=nums2[index2--];
        }









    }
    @Test
    public void test(){
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        merge(nums1,3,nums2,3);
        for (int i:nums1){
            System.out.print(i+" ");
        }

    }






}
