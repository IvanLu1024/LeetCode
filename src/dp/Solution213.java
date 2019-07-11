package dp;

import org.junit.Test;

/**
 * @author
 * @create 2018-12-04 21:31
 **/
public class Solution213 {
    //与198题类似，相当于做两次抢劫，一次的范围是[0,n-2],
    // 另一次的范围是[1,n-1]，取两次抢劫的最大值即可
    public int rob(int[] nums) {
        if(nums==null||nums.length==0)
            return 0;
        int n=nums.length;
        if(n==1)
            return nums[0];
        if(n==2)
            return Math.max(nums[0],nums[1]);
        int res=Math.max(rob(nums,0,n-2),rob(nums,1,n-1));
        return res;
    }
    //抢劫：起始点为l,终点为h的最大收益
    private int rob(int[] nums,int l,int h){
        int[] memo=new int[h-l+1];
        memo[0]=nums[l];
        memo[1]=Math.max(nums[l],nums[l+1]);
        for(int i=2;i<=h-l;i++){
            memo[i]=Math.max(nums[i+l]+memo[i-2],memo[i-1]);
        }
        return memo[h-l];
    }
    @Test
    public void test(){
        int[] nums={2,3,2};
        int res = rob(nums);
        System.out.println(res);

    }
}
