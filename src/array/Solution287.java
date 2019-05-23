package array;

import org.junit.Test;

/**
 * @Author Ivan 16:11
 * @Description TODO
 */
public class Solution287 {
    /**
     * 先用快慢两个下标都从0开始，快下标每轮映射两次，慢下标每轮映射一次，
     * 直到两个下标再次相同。这时候保持慢下标位置不变，再用一个新的下标从0开始，
     * 这两个下标都继续每轮映射一次，当这两个下标相遇时，就是环的起点，也就是重复的数。
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int fast=0;
        int slow=0;
        while (true){
            fast=nums[nums[fast]];
            slow=nums[slow];
            if (fast==slow)
                break;
        }
        fast=0;
        while (fast!=slow){
            fast=nums[fast];
            slow=nums[slow];
        }
        return slow;
    }
    @Test
    public void test(){
        int[] nums={3,1,3,4,2};
        int duplicate = findDuplicate(nums);
        System.out.println(duplicate);
    }
}
