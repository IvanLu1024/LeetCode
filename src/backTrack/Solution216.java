package backTrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。

 说明：

 所有数字都是正整数。
 解集不能包含重复的组合。
 示例 1:

 输入: k = 3, n = 7
 输出: [[1,2,4]]
 示例 2:

 输入: k = 3, n = 9
 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class Solution216 {
    private List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        if (k==0||n==0){
            return res;
        }
        generate(k,n,0,1,new ArrayList<>());
        return res;

    }
    private void generate(int k,int target,int index,int start,List<Integer> list){
        if (index==k&&target==0){
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (i<=target){
                list.add(i);
                generate(k,target-i,index+1,i+1,list);
                list.remove(list.size()-1);
            }
        }
    }
    @Test
    public void test(){
        int k=3;
        int n=15;
        List<List<Integer>> lists = combinationSum3(k, n);
        /*[[1,5,9],[1,6,8],[2,4,9],[2,5,8],[2,6,7],[3,4,8],[3,5,7],[4,5,6]]*/
        System.out.println(lists);
    }
}
