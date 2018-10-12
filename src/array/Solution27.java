package array;

import org.junit.Test;

/**
 *
 *  [0,1,2,2,3,0,4,2],val=2
 *
 *  函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 */
public class Solution27 {

    //时间复杂度：O(n^2)
    //空间复杂度：O(1)
    public int removeElement(int[] nums, int val) {
        if (nums==null||nums.length==0)
            return 0;
        int count=0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]!=val)
                count++;
        }

        for (int i=0;i<nums.length;i++){
            if (nums[i]==val) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j]!=val){
                        int t=nums[i];
                        nums[i]=nums[j];
                        nums[j]=t;
                    }

                }
            }
        }
        return count;

    }

    //时间复杂度：O(n)
    //空间复杂度：O(1)
    public int removeElement1(int[] nums, int val) {
        if (nums==null||nums.length==0)
            return 0;
        int count=0;
        //遍历数组，只记录不等于val的元素
        for (int i=0;i<nums.length;i++){
            if (nums[i]!=val)
               nums[count++]=nums[i];
        }

        return count;

    }

    @Test
    public void test(){
        int[] ints = {0, 1, 2, 2, 3, 0, 4, 2};
        int i = removeElement1(ints, 2);
        System.out.println(i);
        for (int t:ints){
            System.out.print(t+" ");
        }

    }
}
