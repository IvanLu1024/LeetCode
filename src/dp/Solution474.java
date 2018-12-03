package dp;

import org.junit.Test;

/**
 * 在计算机界中，我们总是追求用有限的资源获取最大的收益。
 *
 * 现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
 *
 * 你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
 *
 * 注意:
 *
 * 给定 0 和 1 的数量都不会超过 100。
 * 给定字符串数组的长度不会超过 600。
 * 示例 1:
 *
 * 输入: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 * 输出: 4
 *
 * 解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
 * 示例 2:
 *
 * 输入: Array = {"10", "0", "1"}, m = 1, n = 1
 * 输出: 2
 *
 * 解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1" 。
 *
 */
public class Solution474 {

    private int[][] memo;
    public int findMaxForm(String[] strs, int m, int n) {
        memo=new int[m+1][n+1];
        for (String str:strs){
            int count0=0;
            int count1=0;
            char[] chars = str.toCharArray();
            for (int i = 0; i <chars.length ; i++) {
                if (chars[i]=='0'){
                    count0++;
                }
                if (chars[i]=='1'){
                    count1++;
                }
            }
            for (int i = m; i >=count0 ; i--) {
                for (int j = n; j >=count1 ; j--) {
                    memo[i][j]=Math.max(memo[i][j],memo[i-count0][j-count1]+1);
                }
            }
        }
        return memo[m][n];
    }
    @Test
    public void  test(){
        String[] strings={"10", "0001", "111001", "1", "0"};
        int m=5;
        int n=3;
        int res = findMaxForm(strings, m, n);
        System.out.println(res);

    }
}
