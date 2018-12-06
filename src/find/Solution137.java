package find;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入: [2,2,3,2]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [0,1,0,1,0,1,99]
 * 输出: 99
 *
 */
public class Solution137 {
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

    //对所有数而言，每一个数的第i位累加起来去mod3
    //然后mod的结果返还i位给res(存最终结果的变量)
    //逢3不进位的加法
    public int singleNumber1(int[] nums){
        int n = nums.length;
        if (n==1){
            return nums[0];
        }
        int res=0;
        for (int i = 0; i < 32; i++) {
            int sum=0;
            for (int num:nums){
                sum+=(num>>i)&1;
                sum=sum%3;
            }
            res=res|(sum<<i);
        }
        return res;
    }

    @Test
    public void test(){
        int[] nums={2,2,3,2};
        int i = singleNumber1(nums);
        System.out.println(i);
    }
}
