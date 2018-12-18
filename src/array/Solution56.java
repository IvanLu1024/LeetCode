package array;

import java.util.*;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 */
public class Solution56 {
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }


    private class myComparable implements Comparator<Interval> {
        @Override
        public int compare(Interval i1, Interval i2) {
            if (i1.start!=i2.start){
                return i1.start-i2.start;
            }else {
                return i1.end-i2.end;
            }
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res=new LinkedList<>();
        int n =intervals.size();
        if (n==0){
            return res;
        }
        //将区间集合按照起始点升序排列
        Collections.sort(intervals,new  myComparable());
        res.add(intervals.get(0));
        for (Interval i:intervals){
            if (isCross(i,((LinkedList<Interval>) res).getLast())){
                Interval newInterval = merge(i, ((LinkedList<Interval>) res).getLast());
                //先删除旧的区间
                ((LinkedList<Interval>) res).removeLast();
                //再插入新的区间
                res.add(newInterval);
            }else {
                res.add(i);
            }
        }
        return res;
    }

    //判断两个区间是否重叠
    private boolean isCross(Interval i1,Interval i2){
        return i1.start<=i2.end;
    }

    //合并两个区间，起始点取最小值，终点取最大值
    private Interval merge(Interval i1,Interval i2){
        return new Interval(Math.min(i1.start,i2.start),Math.max(i1.end,i2.end));
    }
}
