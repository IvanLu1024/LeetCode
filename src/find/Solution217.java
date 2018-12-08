package find;

import org.junit.Test;

import java.util.*;

/**
     * 给定一个整数数组，判断是否存在重复元素。

     如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 * @author
 * @create 2018-10-27 15:34
 **/
public class Solution217 {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> record=new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (record.contains(nums[i])){
                return true;
            }else {
                record.add(nums[i]);
            }
        }
        return false;

    }
    public boolean containsDuplicate1(int[] nums) {
        List<Integer> record=new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (record.contains(nums[i])){
                return true;
            }else {
                record.add(nums[i]);
            }
        }
        return false;
    }

    //通过排序的方法
    public boolean containsDuplicate2(int[] nums){
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i]==nums[i-1]){
                return true;
            }
        }
        return false;
    }

    @Test
    public void test(){
        int[] nums={1,2,4,5,1};
        int k=1;
        boolean res = containsDuplicate2(nums);
        System.out.println(res);
    }

}
