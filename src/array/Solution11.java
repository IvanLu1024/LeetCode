package array;

import org.junit.Test;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 示例:
 *
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 */
public class Solution11 {
    public int maxArea(int[] height) {
        int l=0,h=height.length-1;
        int max=0;
        while (l<h){
            int s=Math.min(height[l],height[h])*(h-l);
            if (height[l]<height[h])
            {
                l++;
            }else {
                h--;
            }
            max=Math.max(max,s);
        }
        return max;
    }
    @Test
    public void test(){
        int[] arr={1,8,6,2,5,4,8,3,7};
        int maxArea = maxArea(arr);
        System.out.println(maxArea);

    }
}
