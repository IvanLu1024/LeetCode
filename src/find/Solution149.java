package find;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *
 * 给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。

     示例 1:

     输入: [[1,1],[2,2],[3,3]]
     输出: 3
     解释:
     ^
     |
     |        o
     |     o
     |  o
     +------------->
     0  1  2  3  4
     示例 2:

     输入: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
     输出: 4
     解释:
     ^
     |
     |  o
     |     o        o
     |        o
     |  o        o
     +------------------->
     0  1  2  3  4  5  6
 *
 * @author
 * @create 2018-10-25 21:31
 */

public class Solution149 {

    private class Point {
      int x;
     int y;
     Point() { x = 0; y = 0; }
     Point(int a, int b) { x = a; y = b; }
  }

    public int maxPoints(Point[] points) {
        int res=0;
        if (points==null||points.length==0){
            return res;
        }
        if(points.length==1){
            return ++res;
        }

        for (int i = 0; i <points.length ; i++) {
            Map<Double,Integer> record=new HashMap<>();
            int same=1;
            int maxCount=0;
            for (int j = i+1; j < points.length; j++) {
                if (points[i].x==points[j].x&&points[j].y==points[j].y){
                    ++same;
                }else {
                    double angle = slope(points[i],points[j]);
                    int time = record.getOrDefault(angle, 0) + 1;
                    record.put(angle,time);
                    maxCount=Math.max(maxCount,time);
                }

            }
            res=Math.max(res,maxCount+same);


        }

        return res;

    }
    private double  slope(Point p1,Point p2){
       return Math.atan2((p2.y-p1.y),(p2.x-p1.x));
    }

    @Test
   public void test(){
/*[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]

[[1,1],[1,1],[2,2],[2,2]]*/

        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        Point[] points={p1,p1,p2,p2};
        int res = maxPoints(points);
        System.out.println(res);


    }
}
