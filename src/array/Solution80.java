package array;

import org.junit.Test;

/**
 * 26题的变式
 *
 * 给定 nums = [0,0,1,1,1,1,2,3,3],
 *
 * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 */
public class Solution80 {

    public int removeDuplicates(int[] nums) {
        if (nums!=null&&nums.length!=0){
            int cur=0,count=1;
            for (int i=1;i<nums.length;i++){
                if (nums[cur]==nums[i]){
                    count++;
                }else {
                    count=1;
                }
                if (count<=2){
                    cur++;
                    nums[cur]=nums[i];
                }
            }
            return cur+1;


        }
        else
            return 0;

    }

    //利用增强for循环
    public int removeDuplicates1(int[] nums) {
        if (nums!=null&&nums.length!=0){
            int count=0;
            for (int num:nums){
                if (count<2||num>nums[count-2])
                    nums[count++]=num;

            }
            return count;



        }
        else
            return 0;

    }



    @Test
    public void test() {
        int[] ints = {0,0,1,1,1,1,2,3,3};
        int i = removeDuplicates1(ints);
        System.out.println(i);
        for (int t : ints) {
            System.out.print(t + " ");
        }
    }

}
