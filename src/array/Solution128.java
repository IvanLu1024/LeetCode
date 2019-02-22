package array;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 给定一个未排序的整数数组，找出最长连续序列的长度。

 要求算法的时间复杂度为 O(n)。

 示例:

 输入: [100, 4, 200, 1, 3, 2]
 输出: 4
 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。

 */
public class Solution128 {
    public int longestConsecutive(int[] nums) {
        if (nums==null||nums.length==0){
            return 0;
        }
        //K:num,V:连续序列的长度
        Map<Integer,Integer> map=new HashMap<>();
        int maxLen=0;
        for(int num:nums){
            //当前是新加入的数字的时候
            if (!map.containsKey(num)){
                //左右的相邻数字
                int left=map.getOrDefault(num-1,0);
                int right=map.getOrDefault(num+1,0);
                //计算当前连续序列的长度
                int len=left+right+1;
                maxLen=Math.max(len,maxLen);
                map.put(num,len);
                //更新左右边界
                map.put(num-left,len);
                map.put(num+right,len);
            }
        }
        return maxLen;
    }
    @Test
    public void test(){
        int[] nums={1,2,1,0};
        int i = longestConsecutive(nums);
        System.out.println(i);
    }
}
