package dp;

import org.junit.Test;

/**
 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 你可以对一个单词进行如下三种操作：

 插入一个字符
 删除一个字符
 替换一个字符
 示例 1:

 输入: word1 = "horse", word2 = "ros"
 输出: 3
 解释:
 horse -> rorse (将 'h' 替换为 'r')
 rorse -> rose (删除 'r')
 rose -> ros (删除 'e')
 示例 2:

 输入: word1 = "intention", word2 = "execution"
 输出: 5
 解释:
 intention -> inention (删除 't')
 inention -> enention (将 'i' 替换为 'e')
 enention -> exention (将 'n' 替换为 'x')
 exention -> exection (将 'n' 替换为 'c')
 exection -> execution (插入 'u')



 */
public class Solution72 {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word1.length() == 0) {
            return word2.length();
        }
        if (word2 == null || word2.length() == 0) {
            return word1.length();
        }

        int n1 = word1.length();
        int n2 = word2.length();

        //memo[i][j]: 从word1[0,i-1]转化为word2[0,j-1]的最少操作数
        int[][] memo=new int[n1+1][n2+1];

        for (int i = 0; i <= n1; i++) {
            memo[i][0]=i;   //将word1[0,i-1]转化为word2[0,-1](空字符串)的最少操作就是将当前字符串的字符全部删除
        }

        for (int i = 0; i <= n2; i++) {
            memo[0][i]=i;   //将word1[0,-1](空字符串)转化为word2[0,i-1]的最少操作就是插入当前字符
        }

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i-1)==word2.charAt(j-1)){
                    memo[i][j]=memo[i-1][j-1];
                }else {
                    memo[i][j]=1+min3(memo[i-1][j],memo[i][j-1],memo[i-1][j-1]);
                    //1+memo[i-1][j]:表示插入操作
                    //1+memo[i][j-1]:表示删除操作
                    //1+memo[i-1][j-1]:表示替换操作
                }
            }
        }
        return memo[n1][n2];
    }
    private int min3(int i,int j,int k){
        return Math.min(i,Math.min(j,k));
    }
    @Test
    public void test(){
        String word1="intention";
        String word2="execution";
        int i = minDistance(word1, word2);
        System.out.println(i);

    }
}
