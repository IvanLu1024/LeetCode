package array;

import org.junit.Test;

/**
 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。

 示例:

 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 输出: 6


 */
public class Solution42 {
    public int trap(int[] height) {
        int n = height.length;
        if (n==0){
            return 0;
        }
        int[] left,right;
        left=new int[n];
        right=new int[n];
        //先初始化左边界
        for (int i = 1; i < n; i++) {
            left[i]=Math.max(height[i-1],left[i-1]);
        }
        //初始化右边界
        for (int i = n-2; i >=0 ; i--) {
            right[i]=Math.max(height[i+1],right[i+1]);
        }
        int water=0;
        //计算雨水量，计算height[1,n-2]的范围，因为两边不能累积雨水
        for (int i = 1; i < n-1; i++) {
            int h=Math.min(left[i],right[i]);
            //累积雨水，当存在高度差的时候就可以累积雨水
            water+=Math.max(0,h-height[i]);
        }
        return water;
    }
    @Test
    public void test(){
        int[] height={0,1,0,2,1,0,1,3,2,1,2,1};
        int res = trap(height);
        System.out.println(res);
    }
}
