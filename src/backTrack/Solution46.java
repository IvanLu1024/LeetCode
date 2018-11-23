package backTrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 *
 *
 */
public class Solution46 {

    private List<List<Integer>> res=new ArrayList<>();
    //用于记录该数字是否被使用过
    private boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        if (nums==null||nums.length==0){
            return res;
        }
        //boolean数组默认值为false
        used=new boolean[nums.length];
        List<Integer> p=new ArrayList<>();
        generatePermute(nums,0,p);
        return res;
    }

    //p中保存了index个元素的排列
    //向这个排序的尾部放入一个第index+1个元素，获得一个index+1的元素排列
    private void generatePermute(int[] nums,int index,List<Integer> p){
        //递归终止条件
        if (index==nums.length){
            System.out.println("permute completed : "+p);
            res.add(new ArrayList<>(p));
            return;
        }
        for (int i = 0; i <nums.length ; i++) {
            if (!used[i]){
                //将nums[i]添加到p中
                p.add(nums[i]);
                used[i]=true;
                System.out.println(nums[i]+" -> p: "+p);
                generatePermute(nums,index+1,p);
                //回溯
                p.remove(p.size()-1);
                used[i]=false;
                System.out.println("backTracking ... current P:"+p);
            }
        }
    }
    @Test
    public void test(){
        int[] nums={1,2,3};
        List<List<Integer>> r = permute(nums);
        System.out.println(r);
    }
}
