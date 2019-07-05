package find;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定平面上 n 对不同的点，“回旋镖” 是由点表示的元组 (i, j, k) ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。

     找到所有回旋镖的数量。你可以假设 n 最大为 500，所有点的坐标在闭区间 [-10000, 10000] 中。

     示例:

     输入:
     [[0,0],[1,0],[2,0]]

     输出:
     2

     解释:
     两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]

 * 时间复杂度O(n^2)
 * 空间复杂度O(n)
 * @author
 * @create 2018-10-25 20:15
 **/

public class Solution447 {

    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            //记录元组i到其他所有点的距离对应的次数
            Map<Double, Integer> record = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    //欧式距离
                    double distance = Math.pow((points[i][0] - points[j][0]), 2) + Math.pow((points[i][1] - points[j][1]), 2);
                    record.put(distance, record.getOrDefault(distance, 0) + 1);
                }
            }
            //从c个方案里面挑选出2个，即Cn2的排列
            for (Integer c : record.values()) {
                if (c >= 2) {
                    res += c * (c - 1);
                }
            }

        }

        return res;

    }


    @Test
    public void test(){
/*[[0,0],[1,0],[-1,0],[0,1],[0,-1]]*/

        int[][] nums = new int[][]{{0,0},{1,0},{-1,0},{0,1},{0,-1}};
        int res = numberOfBoomerangs(nums);
        System.out.println(res);
    }
}
