package math;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。

 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：

 "123"
 "132"
 "213"
 "231"
 "312"
 "321"
 给定 n 和 k，返回第 k 个排列。

 说明：

 给定 n 的范围是 [1, 9]。
 给定 k 的范围是[1,  n!]。

 示例 1:

 输入: n = 3, k = 3
 输出: "213"

 示例 2:

 输入: n = 4, k = 9
 输出: "2314"

 */
public class Solution60 {
    private boolean[] used;
    private int[] memo;
    //回溯法+剪枝
    public String getPermutation(int n, int k) {
        memo=new int[n+1];
        used=new boolean[n+1];
        factorial(n,memo);
        int[] nums=new int[n];
        for (int i = 0; i < n; i++) {
            nums[i]=i+1;
        }
        return generate(nums,0,n,k,"");
    }
    private String generate(int[] nums,int index,int n,int k,String s){
        if (index==n){
            return s;
        }
        //获取当前节点中叶子结点的个数
        int ps=memo[n-index-1];
        for (int i = 0; i < n; i++) {
            if (used[i]) continue;
            //若当前叶子总数小于k直接跳过
            if (ps<k){
                k-=ps;
                continue;
            }
            s=s+nums[i];
            used[i]=true;
            return generate(nums,index+1,n,k,s);
        }
        return "";

    }

    private void factorial(int n,int[] memo){
        int f=1;
        memo[0]=1;
        for (int i = 1; i <=n; i++) {
            f*=i;
            memo[i]=f;
        }
    }


    public String getPermutation1(int n, int k){
        //用来记录阶乘
        int[] memo=new int[n+1];
        //候选数字集合
        List<Integer> cands=new ArrayList<>();
        k-=1;
        StringBuilder sb = new StringBuilder();
        factorial(n,memo,cands);
        //
        for (int i = n-1; i >=0; i--) {
            //待加入的候选数字
            int index=k/memo[i];
            sb.append(cands.remove(index));
            k-=index*memo[i];
        }
        return sb.toString();
    }
    //预处理，先计算出阶乘
    private void factorial(int n,int[] memo,List<Integer> cands){
        int f=1;
        memo[0]=1;
        for (int i = 1; i <=n ; i++) {
            cands.add(i);
            f*=i;
            memo[i]=f;
        }
    }
    @Test
    public void test(){
        int n=3,k=3;
        String s = getPermutation(n, k);
        System.out.println(s);
    }
}
