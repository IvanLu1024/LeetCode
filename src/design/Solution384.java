package design;

import java.util.Random;

/**
 * @Author Ivan 15:22
 * @Description TODO
 */
public class Solution384 {

    class Solution {
        private int[] nums;
        private int[] cloneNums;
        private int len;
        private Random random;

        public Solution(int[] nums) {
            this.len=nums.length;
            this.cloneNums=nums.clone();
            this.nums=nums;
            this.random=new Random();
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            return this.nums;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            for(int i=len-1;i>=0;i--){
                int j = random.nextInt(i+1);
                swap(cloneNums,j,i);
            }
            return cloneNums;

        }
        private void swap(int[] nums,int i,int j){
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }
}
