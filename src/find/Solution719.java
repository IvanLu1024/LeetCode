package find;

import org.junit.Test;

import java.util.Arrays;
import java.util.TreeSet;


/**
 * 给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。

 示例 1:

 输入：
 nums = [1,3,1]
 k = 1
 输出：0
 解释：
 所有数对如下：
 (1,3) -> 2
 (1,1) -> 0
 (3,1) -> 2
 因此第 1 个最小距离的数对是 (1,1)，它们之间的距离为 0。

 提示:

 2 <= len(nums) <= 10000.
 0 <= nums[i] < 1000000.
 1 <= k <= len(nums) * (len(nums) - 1) / 2.

 *
 */
public class Solution719 {
    public int smallestDistancePair(int[] nums, int k) {
        int[] distance=new int[1000000];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j <nums.length ; j++) {
                int d=Math.abs(nums[i]-nums[j]);
                distance[d]++;
            }
        }
        int index=0;
        for (int i = 0; i < distance.length; i++) {
            index+=distance[i];
            if (k<=index){
                return i;
            }
        }
        return -1;
    }

    /**
     * 我们首先对nums进行排序，这样就可以得到distance的最小值low和最大值high了。
     * 然后二分查找：对于一个介于low和high之间的数mid，我们统计差值小于mid的一共有多少个，
     * 如果小于k，那么说明说明mid的取值偏小，所以修改low的值；否则修改high的值。这样不断迭代，
     * 最终当low > high的时候，low即为所求。
     * @param nums
     * @param k
     * @return
     */
    //二分搜索
    public int smallestDistancePair1(int[] nums, int k){
        Arrays.sort(nums);
        int n=nums.length;
        int l=0,h=nums[n-1]-nums[0];
        while (l<=h){
            int mid=l+(h-l)/2,j=0,count=0;
            for (int i = 0; i < n; i++) {
                //统计差值小于mid一共有多少对，由于此时数组是递增的
                while (j<n&&nums[j]-nums[i]<=mid){
                    j++;
                }
                count+=j-i-1;
            }
            if (count<k){
                l=mid+1;
            }else {
                h=mid-1;
            }
        }
        return l;
    }

    @Test
    public void test(){
        int[] nums={1,6,1};
        int k=3;
        int r = smallestDistancePair1(nums, k);
        System.out.println(r);
    }
}
