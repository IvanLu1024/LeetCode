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

    //*得到的结果没有去重
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

    //使用Map的方式
    public List<List<Integer>> threeSum2(int[] nums){
        List<List<Integer>> res=new ArrayList<>();
        //用于记录nums数组中各元素出现的次数
        Map<Integer,Integer> countMap=new HashMap<>();
        for (int i:nums){
            countMap.put(i,countMap.getOrDefault(i,0)+1);
        }
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            Integer v0 = entry.getKey();
            Integer count0 = entry.getValue();
            //如果出现次数超过3次，那么结果只能为0
            if (count0>=3){
                if (v0==0){
                    res.add(Arrays.asList(0,0,0));
                }
            }
            //如果出现次数超过2次
            if (count0>=2){
                int v2=0-2*v0;
                if (v2!=v0) {
                    if (countMap.containsKey(v2)) {
                        res.add(Arrays.asList(v0, v0, v2));
                    }
                }
            }
            //剩余的就是只出现一次的元素
            for (int v1:countMap.keySet()){
                int v2=0-v0-v1;
                //确保[[v0,v1,v2]...]内部有序，去重
                if (v2<=v1||v1<=v0||countMap.get(v2)==null)
                    continue;
                res.add(Arrays.asList(v0,v1,v2));
            }


        }

        return res;
    }




    @Test
    public void test(){
        int[] nums={-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum2(nums);
        System.out.println(lists);

    }



}
