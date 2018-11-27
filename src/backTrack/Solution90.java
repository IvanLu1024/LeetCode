package backTrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution90 {
    private List<List<Integer>> res=new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums==null||nums.length==0){
            return res;
        }
        Arrays.sort(nums);
        generate(nums,0,new ArrayList<>());
        return res;
    }

    private void generate(int[] nums,int index,List<Integer> list){
        //当满足条件就加入结果的集合中，确保子集的长度不超过原始集合
        if (index<=nums.length){
            res.add(new ArrayList<>(list));
        }
        for (int i = index; i <nums.length ; i++) {
            //去重
            if (i>index&&nums[i]==nums[i-1]){
                continue;
            }
            list.add(nums[i]);
            generate(nums,i+1,list);
            list.remove(list.size()-1);
        }
        return;
    }

    @Test
    public void test(){
        int[] nums={1,2,2};
        List<List<Integer>> subsets = subsetsWithDup(nums);
        System.out.println(subsets);
    }
}
