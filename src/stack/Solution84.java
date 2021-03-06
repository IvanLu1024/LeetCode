package stack;

import org.junit.Test;

import java.util.Stack;

/**
 * 示例:
 *
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 */
public class Solution84 {
    //基本思想就是，将数值压栈，直到这些数值代表的高度的柱子不能向右再扩展长度了，
    // 就计算其面积大小，pop出来，留下还可以拓展的，一直到最后
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (n==0){
            return 0;
        }
        if (n==1){
            return heights[0];
        }
        //栈来记录从栈顶到栈底递增序列的下标
        Stack<Integer> stack = new Stack<>();
        int res=0;
        //处理的柱子宽度为0-n
        for (int i = 0; i <= n; i++) {
            //为了避免数组越界，将高度进行了统一处理
            int num=i==n?0:heights[i];
            //如果当前遍历的栈为空或者高度大于或等于栈顶元素的值的时候，入栈
            if (stack.empty()||num>=heights[stack.peek()]){
                stack.push(i);
            }//当前遍历的高度小于栈顶的值的时候，出栈
            else {
                int top = stack.pop();
                //计算当前的宽度
                //当栈为空的时候表明，当前高度为最小高度
                int w = stack.empty()?i:i-1-stack.peek();
                //记录最大面积
                res=Math.max(res,w*heights[top]);

                //为了从当前位置继续开始
                i--;
            }
        }
        return res;
    }
    @Test
    public void test(){
        int[] h={2,1,5,6,2,3};
        int i = largestRectangleArea(h);
        System.out.println(i);
    }
}
