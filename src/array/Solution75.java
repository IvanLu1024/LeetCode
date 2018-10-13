package array;

import org.junit.Test;

/**
 *
 * 输入: [2,0,2,1,1,0]
   输出: [0,0,1,1,2,2]
 */
public class Solution75 {

    //时间复杂度：O(n)
    //空间复杂度：O(1)
    public void sortColors(int[] nums) {
        int[] colors=new int[3];
        for (int i=0;i<nums.length;i++){
            if (nums[i]==0)
                colors[0]++;
            else if (nums[i]==1)
                colors[1]++;
            else if (nums[i]==2)
                colors[2]++;

        }
        for (int i=0;i<nums.length;i++){
            if (i<colors[0])
                nums[i]=0;
            else if (i>=colors[0]&&i<colors[0]+colors[1])
                nums[i]=1;
            else nums[i]=2;
        }

    }
    @Test
    public void test(){
        int[] nums = {2,0,2,1,1,0};
        sortColors(nums);
        for (int n:nums){
            System.out.print(n+",");
        }

    }
}
