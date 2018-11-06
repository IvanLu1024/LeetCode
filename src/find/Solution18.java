package find;

import org.junit.Test;

import java.util.*;

/*
*
 * 四数之和
 *
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。

 注意：

 答案中不可以包含重复的四元组。

 示例：

 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

 满足要求的四元组集合为：
 [
 [-1,  0, 0, 1],
 [-2, -1, 1, 2],
 [-2,  0, 0, 2]
 ]
 *
 * @author
 * @create 2018-10-22 22:08
 *
*/

public class Solution18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res=new ArrayList<>();
        Map<Integer,Integer> countMap=new HashMap<>();
        for (int n : nums) {
            countMap.put(n,countMap.getOrDefault(n,0)+1);
        }
        for (Map.Entry<Integer, Integer> entry:countMap.entrySet()){
            int v0=entry.getKey();
            int count0=entry.getValue();
            if (count0>=4){
                if (4*v0==target){
                    res.add(Arrays.asList(0,0,0,0));
                }
            }
            if (count0>=3){
                int v1=target-3*v0;
                if (v1!=v0){
                    if (countMap.containsKey(v1)){
                        res.add(Arrays.asList(v0,v0,v0,v1));
                    }
                }
            }
            if (count0>=2){
                for (int v1:countMap.keySet()){
                    int v2=target-2*v0-v1;
                    if (v2!=v1){
                        if (v1<=v0||v2<=v1||!countMap.containsKey(v2)){
                            continue;
                        }
                        res.add(Arrays.asList(v0,v0,v1,v2));

                    }
                }

            }
            for (int v1:countMap.keySet()){
                for (int v2:countMap.keySet()){
                    int v3=target-v0-v1-v2;
                    if (v3!=v2){
                        if (v0>=v1||v1>=v2||v2>=v3||!countMap.containsKey(v3)){
                            continue;
                        }
                        res.add(Arrays.asList(v0,v1,v2,v3));
                    }
                }
            }

        }
        return res;
    }

    //指针法，将问题简化为3->2
    public List<List<Integer>> fourSum1(int[] nums, int target){
        List<List<Integer>> res=new ArrayList<>();
        if (nums==null||nums.length<4){
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i==0||nums[i]!=nums[i-1]){
                for (int j =i+1; j <nums.length-2; j++) {
                    if (j==i+1||nums[j]!=nums[j-1]){
                        int l=j+1,h=nums.length-1;
                        int newTarget=target-nums[i]-nums[j];
                        while (l<h){
                            if (nums[l]+nums[h]==newTarget){
                                res.add(Arrays.asList(nums[i],nums[j],nums[l],nums[h]));
                                while (l<h&&nums[l]==nums[l+1]) l++;
                                while (l<h&&nums[h]==nums[h-1]) h--;
                                l++;
                                h--;
                            }else if (nums[l]+nums[h]<newTarget){
                                l++;
                            }else {
                                h--;
                            }
                        }
                    }

                }

            }
        }
        return res;

    }

    @Test
    public void test(){
        int[] nums={1, 0, -1, 0, -2, 2};
        List<List<Integer>> lists = fourSum1(nums,0);
        System.out.println(lists);

    }
}
