package math;

/**
 * @Author Ivan 14:22
 * @Description TODO
 */
public class Solution371 {
    //两个整数a, b; a ^ b是无进位的相加； a&b得到每一位的进位；
    // 让无进位相加的结果与进位不断的异或， 直到进位为0；
    public int getSum(int a, int b) {
        int sum,carry;
        do{
            sum=a^b;
            carry=(a&b)<<1;
            a=sum;
            b=carry;
        }while(carry!=0);
        return sum;
    }

}
