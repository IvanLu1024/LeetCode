package find;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。

 示例 1:

 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 输出: [2]
 示例 2:

 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 输出: [9,4]
 说明:

 输出结果中的每个元素一定是唯一的。
 我们可以不考虑输出结果的顺序。
 *
 */
public class Solution349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> record=new HashSet<>();
        Set<Integer> res=new HashSet<Integer>();
        for (int i:nums1){
            record.add(i);
        }
        for (int i:nums2){
           if ( record.contains(i)){
               res.add(i);
           }
        }
        int[] reslut = new int[res.size()];
        int j=0;
        for (Integer i:res){
            reslut[j++]=i;
        }
        return reslut;

    }
    @Test
    public void test(){
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        int[] res = intersection(nums1, nums2);
        for (int i:res){
            System.out.print(i+" ");
        }

    }


}
