package array;

import org.junit.Test;

/**
 * @Author Ivan 15:22
 * @Description TODO
 */
public class Solution334 {
    public boolean increasingTriplet(int[] nums) {
        if (nums==null||nums.length==0){
            return false;
        }
        //设置三元组的第一个元素和第二个元素
        int num1=Integer.MAX_VALUE;
        int num2=Integer.MAX_VALUE;
        for (int i :nums) {
            //确保num1是当前遍历的最小的元素
            if (i<=num1){
                num1=i;
            }else if (i<=num2){
                //num2为当前遍历的第二小的元素
                num2=i;
            }else {
                //存在i>num2>num1，即存在三元组
                return true;
            }
        }
        return false;
    }
    @Test
    public void test(){
        int[] nums={1,2,3,4,5};
        boolean b = increasingTriplet(nums);
        System.out.println(b);
    }
}
