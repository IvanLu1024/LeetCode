package array;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

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

    public int findKthLargest2(int[] nums, int k) {
        //维护一个大顶堆
        PriorityQueue<Integer> pq=new PriorityQueue<>(Comparator.reverseOrder());
        //堆的大小设置为n-k+1,目的是找到第（n-k+1）小的元素，也就是第k大的元素
        k=nums.length-k+1;
        //过程中维护堆的大小，当堆的大小大于k则将堆顶出队
        for (int i:nums){
            pq.offer(i);
            if (pq.size()>k){
                pq.poll();
            }
        }
        return pq.peek();
    }

    @Test
    public void test(){

        String s="abc";
        System.out.println(s.substring(0,2));

    }
}
