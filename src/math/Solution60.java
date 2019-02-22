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
    //回溯法，效率很低
    public String getPermutation(int n, int k) {
        List<String> res = new ArrayList<>();
        used=new boolean[n+1];
        generate(n,1,new StringBuilder(),res);
        return res.get(k-1);
    }
    private void generate(int n,int index, StringBuilder s, List<String> res){
        if (s.length()==n){
            res.add(s.toString());
            return;
        }
        for (int i = 1; i <=n ; i++) {
            if (!used[i]){
                s.append(i);
                used[i]=true;
                generate(n,index+1,s,res);
                s.deleteCharAt(s.length()-1);
                used[i]=false;
            }
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
        int n=4,k=9;
        String s = getPermutation1(n, k);
        System.out.println(s);
    }
}
