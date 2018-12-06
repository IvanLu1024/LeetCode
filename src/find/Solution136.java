package find;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 *
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 */
public class Solution136 {
    //利用map
    public int singleNumber(int[] nums) {
        Map<Integer,Integer> record=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            record.put(nums[i],record.getOrDefault(nums[i],0)+1);
        }
        int res=-1;
        for (Integer i:record.keySet()){
            if (record.get(i)==1){
                res=i;
            }
        }
        return res;
    }

    //利用异或运算的性质
    // ^:为异或运算（性质：对于任何数x，都有x^x=0，x^0=x）
    public int singleNumber1(int[] nums){
        int res=0;
        for (int i : nums) {
            res^=i;
        }
        return res;
    }
    @Test
    public void test(){
        int[] nums={2,2,1};
        int i = singleNumber1(nums);
        System.out.println(i);
    }
}
