package dp;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。

 注意:

 可以认为区间的终点总是大于它的起点。
 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 示例 1:

 输入: [ [1,2], [2,3], [3,4], [1,3] ]

 输出: 1

 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 示例 2:

 输入: [ [1,2], [1,2], [1,2] ]

 输出: 2

 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 示例 3:

 输入: [ [1,2], [2,3] ]

 输出: 0

 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 * @author
 * @create 2018-12-03 20:06
 **/
public class Solution435 {

    public class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
  }
  private class  myComparator implements Comparator<Interval>{
      @Override
      public int compare(Interval o1, Interval o2) {
          if (o1.start!=o2.start){
              return o1.start-o2.start;
          }else {
              return o1.end-o2.end;
          }
      }
  }


    //类似于LIS(最长上升子序列)，300题
    public int eraseOverlapIntervals(Interval[] intervals) {
        int n = intervals.length;
        if (n==0){
            return 0;
        }
        //按照起始位置从小到大排列
        Arrays.sort(intervals,new myComparator());
        // memo[i]表示以intervals[i]为结尾的区间能构成的最长不重叠区间序列
        int[] memo=new int[n];
        Arrays.fill(memo,1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (intervals[i].start>=intervals[j].end){
                    memo[i]=Math.max(memo[i],memo[j]+1);
                }
            }
        }
        int max=0;
        for (int i = 0; i < n; i++) {
            max=Math.max(memo[i],max);
        }
        return n-max;
    }


}
