package array;

import org.junit.Test;

/**
 * 我们把符合下列属性的数组 A 称作山脉：
 *
 * A.length >= 3
 * 存在 0 < i < A.length - 1 使得A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
 * 给定一个确定为山脉的数组，返回任何满足 A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1] 的 i 的值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[0,1,0]
 * 输出：1
 * 示例 2：
 *
 * 输入：[0,2,1,0]
 * 输出：1
 *
 *
 * 提示：
 *
 * 3 <= A.length <= 10000
 * 0 <= A[i] <= 10^6
 * A 是如上定义的山脉
 *
 *
 */
public class Solution852 {
    //对撞指针法
    public int peakIndexInMountainArray(int[] A) {
        int l=0,r=A.length-1;
        while (l<r){
            if (A[l]<A[l+1]) {
                l++;
            }
            if (A[r]<A[r-1]){
                r--;
            }
        }
        return r;
    }

    //二分搜索法
    public int peakIndexInMountainArray1(int[] A){
        int l=0,r=A.length-1;
        int mid=l+(r-l)/2;
        while (l<r){
            if (A[mid-1]<A[mid]&&A[mid]>A[mid+1]){
               return mid;
            }
            //峰值在右侧部分
            if (A[mid-1]>A[mid]){
                r=mid;
            }else {
                l=mid;
            }
            mid=l+(r-l)/2;
        }
        return mid;

    }
    @Test
    public void test(){
        /*  1,2,3,4,5,3,2,1*/
        int[] nums={0,2,1,0 };
        int i = peakIndexInMountainArray1(nums);
        System.out.println(i);

    }
}
