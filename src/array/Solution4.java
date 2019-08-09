package array;

import org.junit.Test;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

 你可以假设 nums1 和 nums2 不会同时为空。

 示例 1:

 nums1 = [1, 3]
 nums2 = [2]

 则中位数是 2.0
 示例 2:

 nums1 = [1, 2]
 nums2 = [3, 4]

 则中位数是 (2 + 3)/2 = 2.5
 *
 */
public class Solution4 {
    private int[] nums;
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double m;
        int n1 = nums1.length;
        int n2 = nums2.length;
        nums=new int[n1+n2];
        nums=merge(nums1,nums2);
        if ((n1+n2)%2!=0){
            //奇数的情况
            m=nums[(n1+n2)/2];
        }else {
            //偶数的情况
            m=((double) nums[(n1+n2)/2]+(double) nums[(n1+n2)/2-1])/2;
        }
        return m;
    }
    //归并排序--将两个已经排序好的数组合并为一个新的排序数组
    private int[] merge(int[] nums1,int[] nums2){
        if (nums1.length==0){
            return nums2;
        }
        if (nums2.length==0){
            return nums1;
        }
        int index1=0,index2=0,index=0;
        while (index1<nums1.length&&index2<nums2.length){
            if (nums1[index1]<nums2[index2]){
                nums[index++]=nums1[index1++];
            }else {
                nums[index++]=nums2[index2++];
            }
        }
        //nums1排序结束
        if (index1>=nums1.length){
            while (index2<nums2.length){
                nums[index++]=nums2[index2++];
            }
        }
        if (index2>=nums2.length){
            while (index1<nums1.length){
                nums[index++]=nums1[index1++];
            }
        }
        return nums;
    }

    //二分搜索
    public double findMedianSortedArrays1(int[] nums1, int[] nums2){
        int n1 = nums1.length;
        int n2 = nums2.length;
        if ((n1+n2)%2==1){
            return findKnum(nums1,0,nums2,0,(n1+n2)/2+1);
        }else {
            return (findKnum(nums1,0,nums2,0,(n1+n2)/2)+findKnum(nums1,0,nums2,0,(n1+n2)/2+1))/2.0;
        }
    }
    //寻找数组中第k小的元素
    private int findKnum(int[] nums1,int l1, int[] nums2,int l2,int k){
        if (l1>=nums1.length){
            return nums2[l2+k-1];
        }
        if (l2>=nums2.length){
            return nums1[l1+k-1];
        }
        if (k==1){
            return Math.min(nums1[l1],nums2[l2]);
        }

        int mid1 = Integer.MAX_VALUE;
        int mid2 = Integer.MAX_VALUE;
        if (l1+k/2-1<nums1.length){
            mid1 = nums1[l1+k/2-1];
        }
        if (l2+k/2-1<nums2.length){
            mid2 = nums2[l2+k/2-1];
        }
        if (mid1<mid2){
            // mid1 < mid2 在 nums1.right 和 nums2 之间搜索, 丢掉 k/2 个数.
            return findKnum(nums1,l1+k/2,nums2,l2,k-k/2);
        }else {
            return findKnum(nums1,l1,nums2,l2+k/2,k-k/2);
        }
    }

    @Test
    public void test(){
        int[] nums1={1,2};
        int[] nums2={3,4};
        double r = findMedianSortedArrays1(nums1, nums2);
        System.out.println(r);
    }
}
