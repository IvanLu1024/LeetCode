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

//    对int类型的每个数每一位单独统计出现1的次数, 如果出现的次数不能整除3
//     则说明唯一存在的数在这一位上为1
    public int singleNumber1(int[] nums){
        int res=0;
        for(int i=0;i<32;i++){
            int bit=1<<i;
            int bitCount=0;
            for(int num:nums){
                if((num&bit)!=0){
                    bitCount++;
                }
            }
            if(bitCount%3!=0){
                res|=bit;
            }
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
