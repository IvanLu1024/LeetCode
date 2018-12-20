package array;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class Solution179 {
    private class my implements Comparator<String>{
        @Override
        public int compare(String i1, String i2) {
            String s1 = i1 + i2;
            String s2 = i2 + i1;
            return s2.compareTo(s1);
        }
    }

    public String largestNumber(int[] nums) {
        String[] snums=new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            snums[i]=String.valueOf(nums[i]);
        }
        Arrays.sort(snums,new my());
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            sb.append(snums[i]);
        }
        String res = sb.toString();
        if (res.charAt(0)=='0'){
            return "0";
        }
        return res;
    }
    @Test
    public void test(){
        int[] nums={999999998,999999997,999999999};
        String s = largestNumber(nums);
        System.out.println(s);
    }
}
