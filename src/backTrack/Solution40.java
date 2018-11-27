package backTrack;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

 candidates 中的每个数字在每个组合中只能使用一次。

 说明：

 所有数字（包括目标数）都是正整数。
 解集不能包含重复的组合。
 示例 1:

 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 所求解集为:
 [
 [1, 7],
 [1, 2, 5],
 [2, 6],
 [1, 1, 6]
 ]
 示例 2:

 输入: candidates = [2,5,2,1,2], target = 5,
 所求解集为:
 [
 [1,2,2],
 [5]
 ]

 *
 */
public class Solution40 {
    private List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates==null||candidates.length==0){
            return res;
        }
        Arrays.sort(candidates);
        generateCS(candidates,target,0,new ArrayList<>());
        return res;

    }
    private void generateCS(int[] c,int target,int start, List<Integer> list){
        if (target==0){
            System.out.println("completed :"+list);
            res.add(new ArrayList<>(list));
        }
        for (int i = start; i < c.length; i++) {
            //去重
            if (i>start&&c[i]==c[i-1]){
                System.out.println("repeat...");
                continue;
            }
            //剪枝，由于待搜索的数组都是正整数，则当前搜索位置元素大于目标则直接跳过
           if (c[i]<=target){
               list.add(c[i]);
               System.out.println("current :"+c[i]+"->"+list+" target:"+target);
               //由于candidates 中的数字可以无限制重复被选取，所以每次开始位置为当前位置
               generateCS(c,target-c[i],i+1,list);
               System.out.println("backTracking..."+list);
               list.remove(list.size()-1);
           }else {
               System.out.println("step over");
           }
        }
    }
    @Test
    public void test(){
        int[] c={2,5,2,1,2};
        int target=5;
        List<List<Integer>> r = combinationSum2(c, target);
        System.out.println(r);
    }
}
