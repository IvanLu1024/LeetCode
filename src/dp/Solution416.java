package dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

 注意:

 每个数组中的元素不会超过 100
 数组的大小不会超过 200
 示例 1:

 输入: [1, 5, 11, 5]

 输出: true

 解释: 数组可以分割成 [1, 5, 5] 和 [11].


 示例 2:

 输入: [1, 2, 3, 5]

 输出: false

 解释: 数组不能分割成两个元素和相等的子集.
 *
 *
 */
public class Solution416 {
    // memo[i][c] 表示使用索引为[0...i]的这些元素,是否可以完全填充一个容量为c的背包
    // -1 表示为未计算; 0 表示不可以填充; 1 表示可以填充
    private int[][] memo;

    //记忆化搜索
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (nums==null||n==0){
            return false;
        }
        int sum=0;
        for (int i = 0; i <n ; i++) {
            sum+=nums[i];
        }
        if (sum%2!=0){
            return false;
        }
        memo=new int[n][sum/2+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < sum / 2 + 1; j++) {
                memo[i][j]=-1;
            }
        }
        return tryPartition(nums,n-1,sum/2);

    }

    // 使用nums[0...index], 是否可以完全填充一个容量为sum的背包
    private boolean tryPartition(int[] nums,int index,int sum){
        if (sum==0){
            return true;
        }
        if (index<0||sum<0){
            return false;
        }
        if (memo[index][sum]!=-1){
            return memo[index][sum]==1;
        }
        memo[index][sum]=(tryPartition(nums,index-1,sum)||tryPartition(nums,index-1,sum-nums[index]))?1:0;
        return memo[index][sum]==1;
    }

    private boolean[] dp;
    public boolean canPartition1(int[] nums){
        int n = nums.length;
        if (nums==null||n==0){
            return false;
        }
        int sum=0;
        for (int i = 0; i <n ; i++) {
            sum+=nums[i];
        }
        if (sum%2!=0){
            return false;
        }
        int c=sum/2;
        dp=new boolean[c+1];
        //初始化：只考虑第一个元素，看看是否能够填满这个背包
        for (int i = 0; i <= c; i++) {
                dp[i]=(nums[0]==i);
        }
        for (int i = 1; i < n; i++) {
            for (int j = c; j >=nums[i] ; j--) {
                dp[j]=dp[j]||dp[j-nums[i]];
            }
        }
        return dp[c];

    }
    @Test
    public void test(){
        int[] nums={1, 5, 11, 5};
        boolean b = canPartition1(nums);
        System.out.println(b);

    }
}
