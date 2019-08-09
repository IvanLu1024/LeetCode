package find;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution260 {
    public int[] singleNumber(int[] nums) {
        int[] res=new int[2];
        if (nums.length==2){
            res[0]=nums[0];
            res[1]=nums[1];
            return res;
        }
        Map<Integer,Integer> record=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            record.put(nums[i],record.getOrDefault(nums[i],0)+1);
        }
        int j=0;
        for (int i:record.keySet()){
            if (record.get(i)==1){
                res[j++]=i;
            }
        }
        return res;
    }
    public int[] singleNumber1(int[] nums){
        int diff=0;
        for (int i:nums){
            diff^=i;
        }

        int mask=1;
        //取异或值最后一个二进制位为1的数字作为mask，如果是1则表示两个数字在这一位上不同
        while ((diff&1)==0){
            mask=mask<<1;
            diff=diff>>1;

        }
        int[] res=new int[2];
        //利用mask将原数组分成两个只有一个数字是出现一次其余都是出现两次的数组
        for(int i:nums){
            if ((i&mask)==0){
                res[0]^=i;
            }else {
                res[1]^=i;
            }
        }
        return res;
    }

    @Test
    public void test(){
        int[] nums={1,2,1,3,2,5};
        int[] re = singleNumber1(nums);
        System.out.println(re[0]+","+re[1]);
    }

}
