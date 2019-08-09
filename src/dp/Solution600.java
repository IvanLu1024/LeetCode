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
        if (num==0){
            return 1;
        }
        if (num==1){
            return 2;
        }
        int bitCount=0;
        while((num>>bitCount)>0){
            bitCount++;
        }
        if ((num>>(bitCount-2))==3){
            return fi(bitCount);
        }else {
            int mask=(1<<(bitCount-1))-1;
            return fi(bitCount-1)+findIntegers(num&mask);
        }
    }

    private int fi(int n){
        if(n==0){
            return 0;
        }
        if(n==1){
            return 2;
        }
        if(n==2){
            return 3;
        }
        int[] memo=new int[n+1];
        memo[1]=2;
        memo[2]=3;
        for(int i=3;i<=n;i++){
            memo[i]=memo[i-1]+memo[i-2];
        }
        return memo[n];
    }
    @Test
    public void test(){
        int num=4;
        int res = findIntegers(num);
        System.out.println(res);
    }
}
