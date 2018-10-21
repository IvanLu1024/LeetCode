package find;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。

 示例 1:

 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 输出: [2,2]
 示例 2:

 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 输出: [4,9]
 说明：

 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 我们可以不考虑输出结果的顺序。
 *
 */
public class Solution350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map=new HashMap<>();
        for (int i:nums1){
            Integer count = map.get(i);
            map.put(i,count==null?1:count+1);
        }
        List<Integer> res=new ArrayList<>();
        for (int i:nums2){
            int c=map.get(i)==null?0:map.get(i);
            if (c>0){
                res.add(i);
                map.put(i,map.get(i)-1);
            }
        }
        int[] reslut = new int[res.size()];
        for (int i=0;i<reslut.length;i++){
            reslut[i]=res.get(i);
        }
        return reslut;
    }
    @Test
    public void test(){
        int[] nums1 = {4,9,5,4};
        int[] nums2 = {9,4,9,8,4,4};
        int[] res = intersect(nums1, nums2);
        for (int i:res){
            System.out.print(i+" ");
        }
    }
}


