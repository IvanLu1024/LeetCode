package find;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
 *
 * 找出所有满足条件且不重复的三元组。

     注意：答案中不可以包含重复的三元组。

     例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

     满足要求的三元组集合为：
     [
     [-1, 0, 1],
     [-1, -1, 2]
     ]
 *
 * @author
 * @create 2018-10-22 22:04
 **/
public class Solution15 {

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums==null||nums.length==0){
            return null;
        }
        List<List<Integer>> res=new ArrayList<>();
        Map<Integer,Integer> numMap=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int target=-nums[i];
            for (int j = 0; j <nums.length ; j++) {
                int cmp=target-nums[j];
                if (numMap.containsKey(cmp)){
                    List<Integer> list=new LinkedList<>();
                    list.add(-target);
                    list.add(cmp);
                    list.add(nums[j]);
                    res.add(list);
                }
                numMap.put(nums[j],j);
            }
        }
        return  res;

    }

    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        //首先做排序处理
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i++) {
            if (i>0&&nums[i]==nums[i-1]) continue;
            int l=i+1,h=nums.length-1,target=-nums[i];
            while (l<h){
                if (nums[l]+nums[h]==target){
                   res.add(Arrays.asList(nums[l],nums[h],nums[i]));
                   while (l<h&&nums[l]==nums[l+1]) l++;
                   while (l<h&&nums[h]==nums[h-1]) h--;
                   l++;
                   h--;
                }else if (nums[l]+nums[h]>target){
                    h--;
                }else {
                    l++;
                }
            }

        }

        return res;

    }

    @Test
    public void test(){
        int[] nums={-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum1(nums);
        System.out.println(lists);

    }



}
