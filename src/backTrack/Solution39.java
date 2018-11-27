package backTrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

 candidates 中的数字可以无限制重复被选取。

 说明：

 所有数字（包括 target）都是正整数。
 解集不能包含重复的组合。
 示例 1:

 输入: candidates = [2,3,6,7], target = 7,
 所求解集为:
 [
 [7],
 [2,2,3]
 ]
 示例 2:

 输入: candidates = [2,3,5], target = 8,
 所求解集为:
 [
 [2,2,2,2],
 [2,3,3],
 [3,5]
 ]

 *
 */
public class Solution39 {
    private List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates==null||candidates.length==0){
            return res;
        }
        generateCS(candidates,target,0,new ArrayList<>());
        return res;

    }
    private void generateCS(int[] c,int target,int start, List<Integer> list){
        if (target==0){
            System.out.println("completed :"+list);
            res.add(new ArrayList<>(list));
        }
        for (int i = start; i < c.length; i++) {
            //剪枝，由于待搜索的数组都是正整数，则当前搜索位置元素大于目标则直接跳过
           if (c[i]<=target){
               list.add(c[i]);
               System.out.println("current :"+c[i]+" target:"+target);
               //由于candidates 中的数字可以无限制重复被选取，所以每次开始位置为当前位置
               generateCS(c,target-c[i],i,list);
               System.out.println("backTracking..."+list);
               list.remove(list.size()-1);
           }else {
               System.out.println("step over");
           }
        }
    }
    @Test
    public void test(){
        int[] c={2,3,5};
        int target=8;
        List<List<Integer>> r = combinationSum(c, target);
        System.out.println(r);
    }
}
