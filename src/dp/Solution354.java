package dp;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，

 这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。

 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。

 说明:
 不允许旋转信封。

 示例:

 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 输出: 3
 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。



 */
public class Solution354 {
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        if (n==0){
            return 0;
        }
        int[] memo=new int[n];
        Arrays.fill(memo,1);
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0]!=o2[0]){
                    return o1[0]-o2[0];
                }else {
                    return o1[1]-o2[1];
                }
            }
        });
        int max=1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0]>envelopes[j][0]&&envelopes[i][1]>envelopes[j][1]){
                    memo[i]=Math.max(memo[i],memo[j]+1);
                }
                max=Math.max(memo[i],max);
            }
        }
        return max;
    }

    public int maxEnvelopes1(int[][] envelopes){
        int n = envelopes.length;
        if (n==0){
            return 0;
        }
        int len=0;
        int[] memo=new int[n];
        //先按照width升序排列，如果width相等就按照height降序排列
        //这样可以保证依次遍历数组的时候,
        // 后面的width始终比前面的大并且如果高度也大于前面的就一定可以包含前面的
        //这样就转化成了一位数组的LIS问题
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0]==o2[0]){
                    return o2[1]-o1[1];
                }
                return o1[0]-o2[0];
            }
        });
        for (int[] envelope:envelopes){
            int l=0,h=len;
            while (l<h){
                int mid=l+(h-l)/2;
                if (envelope[1]>memo[mid])  l=mid+1;
                else h=mid;
            }
            memo[l]=envelope[1];
            if (l==len){
                len++;
            }
        }
        return len;
    }
    @Test
    public void test(){
        int[][] envelopes={{5,4},{6,4},{6,7},{2,3}};
        int i = maxEnvelopes1(envelopes);
        System.out.println(i);
    }
}
