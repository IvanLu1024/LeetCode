package backTrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 */
public class Solution47 {
    private List<List<Integer>> res=new ArrayList<>();
    private boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums==null||nums.length==0){
            return res;
        }
        //首先将数组排序一下
        Arrays.sort(nums);
        List<Integer> p=new ArrayList<>();
        used=new boolean[nums.length];
        generatePermuteUnique(nums,0,p);
        return res;
    }

    private void generatePermuteUnique(int[] nums, int index, List<Integer> p) {
        if (index==nums.length){
            System.out.println("permute completed : "+p);
            res.add(new ArrayList<>(p));
        }
        for (int i = 0; i < nums.length; i++) {
            if(i>0 && ( nums[i]==nums[i-1]) && !used[i-1]){
                //避免重复值,由于数组已经排序过了，所以只需要比较相邻的元素
                System.out.println("skip ... ");
                continue;
            }
            if(used[i]){//避免重复值
                System.out.println("skip ... ");
                continue;
            }
            p.add(nums[i]);
            used[i]=true;
            System.out.println(nums[i]+" -> p: "+p);
            generatePermuteUnique(nums,index+1,p);
            p.remove(p.size()-1);
            used[i]=false;
            System.out.println("backTracking ... current P:"+p);
        }

    }
    @Test
    public void test(){
        int[] nums={1,1,2};
        List<List<Integer>> r = permuteUnique(nums);
        System.out.println(r);

    }
}
