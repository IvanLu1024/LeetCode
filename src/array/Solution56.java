package array;

import org.junit.Test;

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

    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if (n<=1){
            return intervals;
        }
        //将区间集合按照起始点升序排列
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        LinkedList<int[]> list=new LinkedList<>();
        list.add(intervals[0]);
        for (int i = 1; i < n; i++) {
            if (isCross(intervals[i],list.getLast())){
                //先删除原区间
                int[] last = list.removeLast();
                int[] merge = merge(intervals[i], last);
                //再插入合并以后的区间
                list.add(merge);
            }else {
                list.add(intervals[i]);
            }
        }
        int[][] res = list.toArray(new int[list.size()][2]);
        return res;
    }

    //合并两个区间，起始点取最小值，终点取最大值
    private int[] merge(int[] interval1,int[] interval2){
        return new int[]{Math.min(interval1[0],interval2[0]),Math.max(interval1[1],interval2[1])};
    }
    //判断两个区间是否重叠
    private boolean isCross(int[] interval1,int[] interval2){
        return interval1[0]<=interval2[1];
    }

    @Test
    public void test(){
        int[][] intervals={{1,3},{2,6},{8,10},{15,18}};
        int[][] res = merge(intervals);
        System.out.println(res);
    }
}
