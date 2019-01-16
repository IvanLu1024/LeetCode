package array;

import org.junit.Test;

import java.util.Arrays;
/**
 * 还记得童话《卖火柴的小女孩》吗？现在，你知道小女孩有多少根火柴，请找出一种能使用所有火柴拼成一个正方形的方法。
 *
 * 不能折断火柴，可以把火柴连接起来，并且每根火柴都要用到。
 *
 * 输入为小女孩拥有火柴的数目，每根火柴用其长度表示。输出即为是否能用所有的火柴拼成正方形。
 *
 * 示例 1:
 *
 * 输入: [1,1,2,2,2]
 * 输出: true
 *
 * 解释: 能拼成一个边长为2的正方形，每边两根火柴。
 * 示例 2:
 *
 * 输入: [3,3,3,3,4]
 * 输出: false
 *
 * 解释: 不能用所有火柴拼成一个正方形。
 * 注意:
 *
 * 给定的火柴长度和在 0 到 10^9之间。
 * 火柴数组的长度不超过15。
 *
 */
public class Solution473 {
    public boolean makesquare(int[] nums) {
        if (nums==null||nums.length==0){
            return false;
        }
        int sum=0;
        for (int i:nums){
            sum+=i;
        }
        if (sum%4!=0){
            return false;
        }
        int side = sum/4;
        int[] sides={side,side,side,side};
        //使用排序来进行剪枝操作
        Arrays.sort(nums);
        //从大到小来遍历数组
        return dfs(nums,nums.length-1,sides);
    }

    private boolean dfs(int[] nums, int index, int[] sides){
        if (index==-1){
            //若数组遍历结束，并且使得正方形都相等则满足条件，返回true
            for (int i:sides){
                if (i>0){
                    return false;
                }
            }
            return true;
        }
        for (int i = 0; i < sides.length; i++) {
            if (sides[i]>=nums[index]){
                int t = sides[i];
                sides[i]-=nums[index];
                if (dfs(nums,index-1,sides)){
                    return true;
                }
                //回溯
                sides[i] = t;
            }
        }
        return false;
    }
    @Test
    public void test(){
        int[] nums={3,3,3,3,4};
        boolean b = makesquare(nums);
        System.out.println(b);
    }
}
