package array;

import org.junit.Test;

import java.util.*;

/**
 给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。

 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。

 示例 1:

 输入: [3,2,3]
 输出: [3]
 示例 2:

 输入: [1,1,1,3,3,2,2,2]
 输出: [1,2]


 */

public class Solution229 {
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        if (n==0){
            return res;
        }
        Map<Integer,Integer> freq= new HashMap<>();
        for (int i:nums){
            freq.put(i,freq.getOrDefault(i,0)+1);
        }
        for (int i:freq.keySet()){
            if (freq.get(i)>(n/3)){
                res.add(i);
            }
        }
        return res;
    }

    //摩尔投票算法
    //和169题是类似的
    public List<Integer> majorityElement1(int[] nums){
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        if (n==0){
            return res;
        }
        //因为出现次数超过[n/2]的元素，最多只有2个
        int num1,num2,count1=0,count2=0;
        num1=num2=nums[0];
        //首先寻找这两个众数的候选数num1和num2
        for (int i:nums){
            if (i==num1){
                count1++;
            }else if (i==num2){
                count2++;
            }
            else if (count1==0){
                num1=i;
                count1++;
            }
            else if (count2==0){
                num2=i;
                count2++;
            }else {
                count1--;
                count2--;
            }
        }
        //再根据其次数确定是否为众数
        count1=count2=0;
        for (int i:nums){
            if (i==num1){
                count1++;
            }else if (i==num2){
                count2++;
            }
        }
        //将结果加入结果集合中
        if (count1>n/3){
            res.add(num1);
        }
        if (count2>n/3){
            res.add(num2);
        }
        return res;
    }
    @Test
    public void test(){
        int[] nums={};



    }





}
