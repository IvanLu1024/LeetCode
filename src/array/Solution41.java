package array;

import org.junit.Test;

/**
 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。

 示例 1:

 输入: [1,2,0]
 输出: 3
 示例 2:

 输入: [3,4,-1,1]
 输出: 2
 示例 3:

 输入: [7,8,9,11,12]
 输出: 1
 说明:

 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。


 * @create 2019-02-16 15:20
 **/
public class Solution41 {
    public int firstMissingPositive(int[] nums) {
        if (nums==null||nums.length==0){
            return 0;
        }
        //遍历一遍数组将数组中的元素数组大于0
        // 并且小于等于数组长度的元素放置到数值减一下标对应的位置上
        for (int i = 0; i <nums.length ; ) {
            if (nums[i]>0&&nums[i]<=nums.length&&nums[nums[i]-1]!=nums[i]){
                int temp=nums[nums[i]-1];
                nums[nums[i]-1]=nums[i];
                nums[i]=temp;
            }else {
                i++;
            }
        }
        //再遍历一遍数组，若数组下标和数值不是对应的是数值相差为1的话，
        // 那么此时就是答案，若遍历完都没有结果则答案为数组长度加1
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]!=i+1){
                return i+1;
            }
        }
        return nums.length+1;
    }
    @Test
    public void test(){
        int[] nums={7,8,9,11,12};
        int i = firstMissingPositive(nums);
        System.out.println(i);
    }
}
