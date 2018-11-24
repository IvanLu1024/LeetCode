package backTrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

     示例:

     输入: n = 4, k = 2
     输出:
     [
     [2,4],
     [3,4],
     [2,3],
     [1,2],
     [1,3],
     [1,4],
     ]
 *
 * @author
 * @create 2018-11-24 14:51
 **/
public class Solution77 {
    private List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        if (n<=0||n<k||k<=0){
            return res;
        }
        List<Integer> c=new ArrayList<>();
        generateCombine(n,k,1,c);
        return res;

    }

    private void generateCombine(int n, int k, int start, List<Integer> c) {
        if (c.size()==k){
            System.out.println("Completed "+c);
            res.add(new ArrayList<>(c));
            return;
        }
        //此时有(k-c.size())个空位需要填充，那么[i...n]中至少要有(k-c.size())个元素
        //则n-i>=(k-c.size())-1确保[i...n]中至少要有(k-c.size())个元素
        // --> i<=n-(k-c.size())+1
        for (int i = start; i <=n-(k-c.size())+1; i++) {
            c.add(i);
            System.out.println(i+"-> c:"+c);
            generateCombine(n,k,i+1,c);
            c.remove(c.size()-1);
            System.out.println("backTracking..."+c);
        }
    }
    @Test
    public void test(){
        int n=4,k=4;
        List<List<Integer>> r = combine(n, k);
        System.out.println(r);
    }
}
