package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Solution283 {

    //时间复杂度：O(n)
    //空间复杂度：O(n)
    public void moveZeroes(int[] nums) {
        if (nums==null)
            return;
        List<Integer> temp=new ArrayList<>();
        for (int i=0;i<nums.length;i++){
            if (nums[i]!=0)
                temp.add(nums[i]);
        }
        for (int i=0;i<temp.size();i++){
            nums[i]=temp.get(i);
        }
        for (int i=temp.size();i<nums.length;i++){
            nums[i]=0;
        }

    }

    //时间复杂度：O(n)
    //空间复杂度：O(1)
    public void moveZeroes1(int[] nums) {
        if (nums==null)
            return;
        int k=0;//k:表示[0,k)区间内均为非零元素
        //遍历数组，当遇到非零元素时，将其放入[0,k)期间
        //保证[0,k)中所有的非零元素都按照顺序排列在[0,k)中
        for (int i=0;i<nums.length;i++){
            if (nums[i]!=0)
                nums[k++]=nums[i];
        }
        for (int i=k;i<nums.length;i++){
            nums[i]=0;
        }



    }

    public void moveZeroes2(int[] nums) {
        if (nums==null)
            return;
        int k=0;//k:表示[0,k)区间内均为非零元素
        //遍历数组，当遇到非零元素时，将其放入[0,k)期间
        //保证[0,k)中所有的非零元素都按照顺序排列在[0,k)中
        //同时保证[k，i）为0
        for (int i=0;i<nums.length;i++){
            if (nums[i]!=0){
                if (k!=i){
                    int t=nums[i];
                    nums[i]=nums[k];
                    nums[k]=t;
                    k++;
                }else
                    k++;
            }

        }


    }
    @Test
    public void test(){
        int[] ints = {0, 1, 0, 3, 12};
        moveZeroes1(ints);
        for (int i:ints){
            System.out.print(i+",");
        }

    }
}
