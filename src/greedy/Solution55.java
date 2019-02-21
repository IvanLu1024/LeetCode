package greedy;

import org.junit.Test;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。

 数组中的每个元素代表你在该位置可以跳跃的最大长度。

 判断你是否能够到达最后一个位置。

 示例 1:

 输入: [2,3,1,1,4]
 输出: true
 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
 示例 2:

 输入: [3,2,1,0,4]
 输出: false
 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 *
 * @author
 * @create 2019-01-27 21:41
 **/
public class Solution55 {
    //贪心策略：每次都走能走的最大范围
    public boolean canJump(int[] nums) {
        if (nums==null||nums.length==0){
            return true;
        }
        //最大范围
        int maxReach = nums[0];
        //确保最大范围要不小于当前位置的下标；
        for (int i = 1; i < nums.length-1&&maxReach>=i; i++) {
            //如果当前位置跳跃以后能够超过最大范围就更新最大范围
            if (i+nums[i]>maxReach){
                maxReach = i+nums[i];
            }
        }
        return maxReach>=nums.length-1;
    }

    @Test
    public void test(){
        int[] nums = {1,2,3};
        boolean f = canJump(nums);
        System.out.println(f);
    }
}
