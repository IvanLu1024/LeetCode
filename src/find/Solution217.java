package find;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

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

    @Test
    public void test(){
        int[] nums={1,2,4,5};
        int k=1;
        boolean res = containsDuplicate(nums);
        System.out.println(res);
    }
}
