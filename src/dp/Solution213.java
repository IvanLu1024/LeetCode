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
        int n=nums.length;
        if (n==0){
            return 0;
        }
        if(n==1){
            return nums[0];
        }
        if(n==2){
            return Math.max(nums[0],nums[1]);
        }
        return Math.max(rob(nums,0,n-2),rob(nums,1,n-1));

    }
    private int rob(int[] nums,int start,int end){
        int n=end-start+1;
        int[] memo=new int[n];
        memo[0]=nums[start];
        for (int i = 1; i <n ; i++) {
            memo[i]=Math.max(memo[i-1],nums[start+i]+(i-2>=0?memo[i-2]:0));
        }
        return memo[n-1];
    }
    @Test
    public void test(){
        int[] nums={2,3,2};
        int res = rob(nums);
        System.out.println(res);

    }
}
