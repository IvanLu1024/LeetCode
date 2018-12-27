package find;

import org.junit.Test;

import java.util.Arrays;

/**
 * 统计所有小于非负整数 n 的质数的数量。
 *
 * 示例:
 *
 * 输入: 10
 * 输出: 4
 * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 *
 */
public class Solution204 {
    //使用 埃拉托斯特尼筛法
    public int countPrimes(int n) {
        boolean[] memo=new boolean[n];
        Arrays.fill(memo,true);
        for (int i = 2; i*i<n ; i++) {
            if (!memo[i])
                continue;
            for (int j = i*i; j <n ; j+=i) {
                memo[j]=false;
            }
        }
        int count=0;
        for (int i = 2; i <n ; i++) {
            if (memo[i]){
                count++;
            }

        }
        return count;
    }

    @Test
    public void test(){
        int n=10;
        int i = countPrimes(n);
        System.out.println(i);
    }

}
