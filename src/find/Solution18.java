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

    //对撞指针法，将问题简化为4->3->2
    public List<List<Integer>> fourSum(int[] nums, int target){
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            //去重
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            //转化为three sum
            for (int j = i + 1; j < nums.length - 2; j++) {
                //去重
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                //设置对撞指针
                int l = j + 1, h = nums.length - 1;
                //设置新的搜索目标
                int newTarget = target - nums[i] - nums[j];
                //转化为two sum
                while (l < h) {
                    if (nums[l] + nums[h] == newTarget) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[h]));
                        //去重操作
                        while (l < h && nums[l] == nums[l + 1]) l++;
                        while (l < h && nums[h] == nums[h - 1]) h--;
                        l++;
                        h--;
                    } else if (nums[l] + nums[h] < newTarget) {
                        //增大
                        l++;
                    } else {
                        //减小
                        h--;
                    }
                }

            }
        }
        return res;

    }

    @Test
    public void test(){
        int[] nums={1, 0, -1, 0, -2, 2};
        List<List<Integer>> lists = fourSum(nums,0);
        System.out.println(lists);

    }
}
