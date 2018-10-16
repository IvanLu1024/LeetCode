package array;

import org.junit.Test;

/**
 * 输入: numbers = [2, 7, 11, 15], target = 9
   输出: [1,2]

   解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。

   思路：对撞指针
 *
 */
public class Solution167 {


    public int[] twoSum(int[] numbers, int target) {
        int l=0,h=numbers.length-1;
        int[] res = new int[2];
        while (l<h){
            if (numbers[l]+numbers[h]==target)
            {
                res[0]=l+1;
                res[1]=h+1;
            }
            if (numbers[l]+numbers[h]<target)
                l++;
            else h--;
        }
        return res;

    }

    @Test
    public void test(){
        int[] nums = {2, 7, 11, 15};
        int[] res = twoSum(nums, 9);
        System.out.println(res[0]);
        System.out.println(res[1]);

    }
}
