package find;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 454
 *
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。

 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。

 例如:

 输入:
 A = [ 1, 2]
 B = [-2,-1]
 C = [-1, 2]
 D = [ 0, 2]

 输出:
 2

 解释:
 两个元组如下:
 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 *
 * @author
 * @create 2018-10-24 22:27
 **/

public class Solution454 {
    //使用查找表的方式
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        //首先初始化map
        //K：sum；V：count
        Map<Integer,Integer> record=new HashMap<>();
        for (int i = 0; i <C.length ; i++) {
            for (int j = 0; j < D.length; j++) {
                int sumCD = C[i] + D[j];
                record.put(sumCD,record.getOrDefault(sumCD,0)+1);
            }
        }
        //统计符合要求的元组个数
        int res=0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int target=0-A[i]-B[j];
                if (record.containsKey(target)){
                    res+=record.get(target);
                }
            }
        }
        return res;
    }
    @Test
    public void test(){
        int[] A={1, 2};
        int[] B={-2,-1};
        int[] C={-1, 2};
        int[] D={0, 2};
        int res = fourSumCount(A, B, C, D);
        System.out.println(res);

    }
}
