package math;

import org.junit.Test;

public class Solution231 {

    public boolean isPowerOfTwo(int n) {
        if (n<0){
            return false;
        }
        for (int i = 0; i <= Math.sqrt(n); i++) {
            int num = 1 << i;
            if (num==n){
                return true;
            }else if (num>n){
                break;
            }
        }
        return false;
    }

    //位运算
    public boolean isPowerOfTwo1(int n){
        if (n<=0){
            return false;
        }
        //例如：1000 & 0111 = 0
        return (n&(n-1))==0;
    }

    //2的n次幂满足二进制表示中只有一位为1，其余位置均为0
    public boolean isPowerOfTwo2(int n){
        if (n<=0){
            return false;
        }
        String binStr = Integer.toBinaryString(n);
        int count=0;
        for (int i = 0; i < binStr.length(); i++) {
            if (binStr.charAt(i)=='1'){
                count++;
            }
            if (count>1){
                break;
            }
        }
        return count==1;
    }
    @Test
    public void test(){
        /*int sqrt = (int) Math.sqrt(8);
        System.out.println(2==2.0);
*/
        int n = 1024;
        boolean b = isPowerOfTwo2(n);
        System.out.println(b);
    }
}
