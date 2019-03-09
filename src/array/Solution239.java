package array;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums==null||nums.length==0){
            return new int[0];
        }
        int n = nums.length;
        int[] res=new int[n-k+1];
        int l=0,r=k-1;
        PriorityQueue<Integer> pq=new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i <= n; i++) {
            if (i<=r){
                pq.offer(nums[i]);
            }else if (i<=n-1){
                res[i-k]=pq.peek();
                r++;
                pq.remove(nums[l++]);
                pq.offer(nums[r]);
            }else {
                res[i-k]=pq.peek();
            }
        }
        return res;
    }
    @Test
    public void test(){
        int[] nums={1,3,-1,-3,5,3,6,7};
        int k=3;
        int[] res = maxSlidingWindow(nums, k);
        for (int i:res){
            System.out.print(i+",");
        }
    }
}
