package dp.knapsack;

public class Solution1 {

    //记忆化搜索
    private int[][] memo;
    public int knapsack(int[] w, int[] v, int C){
        int n=w.length;
        if (n==0||C==0){
            return 0;
        }
        memo=new int[n][C+1];
        return bestValue(w,v,n-1,C);


    }

    // 用 [0...index]的物品,填充容积为c的背包的最大价值
    private int bestValue(int[] w,int[] v,int index,int C){
        if (index<0||C<0){
            return 0;
        }
        if (memo[index][C]!=-1){
            return memo[index][C];
        }

        //不选择当前物品的价值
        int res = bestValue(w, v, index - 1, C);
        if (w[index]<=C){
            res=Math.max(res,v[index]+bestValue(w,v,index-1,C-w[index]));
        }
        memo[index][C]=res;
        return res;
    }
}
