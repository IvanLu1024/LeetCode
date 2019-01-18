package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。

 找到所有出现两次的元素。

 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？

 示例：

 输入:
 [4,3,2,7,8,2,3,1]

 输出:
 [2,3]
 *
 */
public class Solution442 {
    //使用set的解法
    public List<Integer> findDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        for (int i:nums){
            if (!set.contains(i)){
                set.add(i);
            }else {
                res.add(i);
            }
        }
        return res;
    }

    //**其中1 ≤ a[i] ≤ n （n为数组长度）**
    //
    public List<Integer> findDuplicates1(int[] nums){
        List<Integer> res = new ArrayList<>();
        if (nums==null||nums.length==0){
            return res;
        }
        for (int i = 0; i < nums.length; i++) {
            //记录出现过的元素，将其作为数组的下标
            //将该下标的元素转变为其相反数
            int index = Math.abs(nums[i]) - 1;
            //若当前下标对应的数值小于0则说明该元素已经出现过了，记录到集合中。
            if (nums[index]<0){
                res.add(index+1);
            }else {
                nums[index]=-nums[index];
            }
        }
        return res;
    }
    @Test
    public void test(){
        int[] nums={4,3,2,7,7,7,2,3,1};
        List<Integer> res = findDuplicates1(nums);
        System.out.println(res);
    }
}
