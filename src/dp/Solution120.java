package dp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 说明：
 *
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 */
public class Solution120 {
    /**
     * 状态转移方程为：
     * f(0,0)=t[0][0]
     *
     * f(i,0)=t[i][0]+f(i-1,0)
     *
     * f(i,i)=t[i][i]+f(i-1,i-1)
     *
     * f(i,j)=t[i][j]+min{f(i-1,j-1),f(i-1,j)}
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        if (n==0){
            return 0;
        }
        for (int i = 1; i < n; i++) {
            triangle.get(i).set(0,triangle.get(i-1).get(0)+triangle.get(i).get(0));
            triangle.get(i).set(i,triangle.get(i).get(i)+triangle.get(i-1).get(i-1));
            for (int j = 1; j <i ; j++) {
                triangle.get(i).set(j,triangle.get(i).get(j)+Math.min(triangle.get(i-1).get(j-1),triangle.get(i-1).get(j)));
            }
        }
        Collections.sort(triangle.get(n-1));
        return triangle.get(n-1).get(0);
    }

    public int minimumTotal1(List<List<Integer>> triangle){
        int n = triangle.size();
        if (n==0){
            return 0;
        }
        int[] memo=new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (j==0){
                    memo[j]+=triangle.get(i).get(0);
                }else if (i==j){
                    memo[j]=triangle.get(i).get(i)+memo[i-1];
                }else {
                    memo[j]=triangle.get(i).get(j)+Math.min(memo[j],memo[j-1]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(memo[i]+" ");
        }
        int min=memo[0];
        for (int i = 0; i < n; i++) {
            min=Math.min(min,memo[i]);
        }
        return min;
        
    }
    @Test
    public void test(){
        Integer[] a1={2};
        Integer[] a2={3,4};
        Integer[] a3={6,5,7};
        Integer[] a4={4,1,8,3};
        List<List<Integer>> tri=new ArrayList<>();
        tri.add(Arrays.asList(a1));
        tri.add(Arrays.asList(a2));
        tri.add(Arrays.asList(a3));
        tri.add(Arrays.asList(a4));

        int i = minimumTotal1(tri);
        System.out.println("---");
        System.out.println(i);

    }

}
