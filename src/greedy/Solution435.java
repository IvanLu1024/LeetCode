package greedy;

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
          if (o1.end!=o2.end){
              return o1.end-o2.end;
          }else {
              return o1.start-o2.start;
          }
      }
  }

    //使得区间按照结尾从小到大排列
    //贪心策略：尽量选择结尾靠前的区间，每次选择结尾最早的，并且和前一个区间没有重叠的
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length==0){
            return 0;
        }
        Arrays.sort(intervals,new  myComparator() );
        int res=1;
        int pre=0;  //遍历过程中之前使用的区间索引
        for (int i = 1; i < intervals.length; i++) {
            //如果区间没有重叠的时候
            if (intervals[i].start>=intervals[pre].end){
                res++;
                pre=i;  //记录
            }
        }
        return intervals.length-res;
    }
    @Test
    public void test(){

    }

}
