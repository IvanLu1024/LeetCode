package find;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。

     你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。

     示例:

     给定 nums = [2, 7, 11, 15], target = 9

     因为 nums[0] + nums[1] = 2 + 7 = 9
     所以返回 [0, 1]
 *
 * @author IvanLu
 * @create 2018-10-21 16:43
 **/
public class Solution1 {

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if (nums==null||nums.length==0){
            return res;
        }
        //Key：nums[i]数值中的数值，Value:i,下标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int com = target - nums[i];
            //每次搜索当前的情况，避免了一次性放入所有的元素，导致覆盖了相同元素的下标
            if (map.containsKey(com)) {
                //获取到目标下标
                res[0] = map.get(com);
                res[1] = i;
                return res;
            }
            map.put(nums[i], i);
        }
        return res;
    }

    @Test
    public void test() {
        int[] nums1 = {2, 7, 11, 15};
        int[] res = twoSum(nums1, 18);
        for (int i:res){
            System.out.print(i+" ");
        }


    }
}
