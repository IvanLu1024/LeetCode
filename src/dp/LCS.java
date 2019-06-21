package dp;

import org.junit.Test;

/**
 * @Author Ivan 15:21
 * @Description TODO
 */
public class LCS {

    public int lengthOfLCS(String s1,String s2){
        int m = s1.length();
        int n = s2.length();
        if(m==0||n==0){
            return 0;
        }
        int[][] memo = new int[m+1][n+1];
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(chars1[i-1]==chars2[j-1]){
                    memo[i][j]=1+memo[i-1][j-1];
                }else{
                    memo[i][j]=Math.max(memo[i-1][j],memo[i][j-1]);
                }
            }
        }
        int len = memo[m][n];
        return len;
    }
    @Test
    public void test(){
        String s1= "ABCDbb";
        String s2= "AEECDvb";
        int i = lengthOfLCS(s1, s2);
        char[] chars = s1.toCharArray();
        System.out.println(i);
    }
}
