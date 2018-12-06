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
        int bitFlag=diff&(~(diff-1));
        int[] res=new int[2];
        for(int i:nums){
            if ((i&bitFlag)==0){
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
