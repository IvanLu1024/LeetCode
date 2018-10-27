package find;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 示例 1:

 输入: nums = [1,2,3,1], k = 3, t = 0
 输出: true
 示例 2:

 输入: nums = [1,0,1,1], k = 1, t = 2
 输出: true
 示例 3:

 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
 输出: false
 *
 * @author
 * @create 2018-10-27 21:46
 **/
public class Solution220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        TreeSet<Long> record=new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            long n=nums[i];
            if (record.ceiling(n)!=null&&record.ceiling(n)<=(long)(n+t)||
                    record.floor(n)!=null&&record.floor(n)>=(long)(n-t)){
                return true;
            }else {
                record.add((long)nums[i]);
            }
            //确保查找表的长度为k
            if (record.size()==k+1){
                record.remove((long)nums[i-k]);
            }

        }
        return false;
    }
    @Test
    public void test(){
        int[] nums={1,5,9,1,5,9};
        int k=2;
        int t=3;
        boolean flag = containsNearbyAlmostDuplicate(nums, k, t);
        System.out.println(flag);
    }
}
