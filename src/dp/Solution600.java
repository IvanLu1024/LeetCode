package dp;

import org.junit.Test;

/**
 * 给定一个正整数 n，找出小于或等于 n 的非负整数中，其二进制表示不包含 连续的1 的个数。

 示例 1:

 输入: 5
 输出: 5
 解释:
 下面是带有相应二进制表示的非负整数<= 5：
 0 : 0
 1 : 1
 2 : 10
 3 : 11
 4 : 100
 5 : 101
 其中，只有整数3违反规则（有两个连续的1），其他5个满足规则。

 说明: 1 <= n <= 10^9
 *
 */
public class Solution600 {
    public int findIntegers(int num) {
        int[] memo = new int[2];
        memo[0]=1;
        for (int i = 1; i <= num; i++) {
            if (isContains(i)){
                memo[i%2]=memo[(i-1)%2]+1;
            }else {
                memo[i%2]=memo[(i-1)%2];
            }
        }
        return memo[num%2];
    }
    private boolean isContains(int num){
        String s = Integer.toBinaryString(num);
        char[] chars = s.toCharArray();
        for (int i = 1; i < s.length(); i++) {
            if (chars[i]==chars[i-1]&&chars[i]=='1'){
                return false;
            }
        }
        return true;
    }
    @Test
    public void test(){
        int num=1000000000;
        int res = findIntegers(num);
        System.out.println(res);
    }
}
