package backTrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

 说明：解集不能包含重复的子集。

 示例:

 输入: nums = [1,2,3]
 输出:
 [
 [3],
 [1],
 [2],
 [1,2,3],
 [1,3],
 [2,3],
 [1,2],
 []
 ]
 *
 */
public class Solution78 {
    private List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        if (nums==null||nums.length==0){
            return res;
        }
        generate(nums,0,new ArrayList<>());
        return res;

    }
    private void generate(int[] nums,int index,List<Integer> list){
        //当满足条件就加入结果的集合中，确保子集的长度不超过原始集合
        if (index<=nums.length){
            System.out.println(list+"-> res");
            res.add(new ArrayList<>(list));
        }
        for (int i = index; i <nums.length ; i++) {
            list.add(nums[i]);
            System.out.println("current index:"+i+" "+nums[i]+"->"+list);
            generate(nums,i+1,list);
            list.remove(list.size()-1);
            System.out.println("backTracking..."+list);
        }
        return;
    }
    @Test
    public void test(){
        int[] nums={1,2,3};
        List<List<Integer>> subsets = subsets(nums);
        System.out.println(subsets);
    }
}
