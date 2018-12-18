package array;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 给定一个未经排序的整数数组，找到最长且连续的的递增序列。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,4,7]
 * 输出: 3
 * 解释: 最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
 * 示例 2:
 *
 * 输入: [2,2,2,2,2]
 * 输出: 1
 * 解释: 最长连续递增序列是 [2], 长度为1。
 * 注意：数组长度不会超过10000。
 */
public class Solution674 {
    public int findLengthOfLCIS(int[] nums) {
        if (nums==null||nums.length==0){
            return 0;
        }
        int count=1;
        Queue<Integer> queue=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        queue.offer(count);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i]>nums[i-1]){
                count++;
            }else {
                count=1;
            }
            if (count>=queue.peek()){
                queue.offer(count);
            }
        }
        return queue.peek();
    }

    public int findLengthOfLCIS1(int[] nums){
        if (nums==null||nums.length==0||nums.length==1){
            return nums.length;
        }
        int count=1;
        int max=0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i]>nums[i-1]){
                count++;
            }else {
                count=1;
            }
            max=Math.max(max,count);
        }
        return max;

    }
    @Test
    public void test(){
        int[] nums={1};
        int r = findLengthOfLCIS1(nums);
        System.out.println(r);
    }
}
