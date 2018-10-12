package array;

import org.junit.Test;

/**
 * 实例：
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 *
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 */
public class Solution26 {

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] != nums[i])
                nums[count++] = nums[i - 1];


        }
        nums[count]=nums[nums.length-1];
        return count+1;

    }

    public int removeDuplicates1(int[] nums){
        if (nums!=null&&nums.length!=0){
            int cur=0;
            for (int i=1;i<nums.length;i++){
                if (nums[i]!=nums[cur]){
                    cur++;
                    nums[cur]=nums[i];
                }

            }
            return cur+1;


        }else
            return 0;

    }

    @Test
    public void test() {
        int[] ints = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int i = removeDuplicates1(ints);
        System.out.println(i);
        for (int t : ints) {
            System.out.print(t + " ");
        }
    }
}
