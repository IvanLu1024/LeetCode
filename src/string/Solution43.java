package string;

import org.junit.Test;

/**
 *
 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

 示例 1:

 输入: num1 = "2", num2 = "3"
 输出: "6"
 示例 2:

 输入: num1 = "123", num2 = "456"
 输出: "56088"
 说明：

 num1 和 num2 的长度小于110。
 num1 和 num2 只包含数字 0-9。
 num1 和 num2 均不以零开头，除非是数字 0 本身。
 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 */
public class Solution43 {
    //将乘法的过程拆分如同小学学习的乘法的步骤
    public String multiply(String num1, String num2) {
        if (num1.length()==0||num2.length()==0){
            return null;
        }
        int n1 = num1.length();
        int n2 = num2.length();
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        if (chars1[0]=='0'||chars2[0]=='0'){
            return "0";
        }
        int[ ] res = new int[n1+n2];
        StringBuilder sb = new StringBuilder();
        for (int i = n1-1; i >=0; i--) {
            for (int j = n2-1; j >=0 ; j--) {
                int i1 = chars1[i]-'0';
                int j2 = chars2[j]-'0';
                //当前位数的乘积
                int mul = i1*j2;
                int hIndex = i+j;
                int lIndex = i+j+1;
                //加上之前这一位上的数值
                int sum = mul + res[lIndex];
                //进位
                res[hIndex]+=sum/10;
                res[lIndex]=sum%10;
            }
        }
        for(int i:res){
            if (sb.length()==0&&i==0){
                continue;
            }
            sb.append(i);
        }
        return sb.toString();
    }

    public String multiply1(String num1, String num2){
        int n1 = num1.length();
        int n2 = num2.length();
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        if (chars1[0]=='0'||chars2[0]=='0'){
            return "0";
        }
        int[ ] res = new int[n1+n2];
        //首先计算各位上的乘积
        for (int i = n1-1; i >=0; i--) {
            for (int j = n2-1; j >=0 ; j--){
                int mul = (chars1[i] - '0') * (chars2[j] - '0');
                res[i+j+1] += mul;
            }
        }
        //进位的处理
        for (int i = res.length-1; i >0; i--) {
            int out = res[i]/10;
            res[i-1] +=out;
            res[i]%=10;
        }
        StringBuilder sb = new StringBuilder();
        for(int i:res){
            if (sb.length()==0&&i==0){
                continue;
            }
            sb.append(i);
        }
        return sb.toString();

    }
    @Test
    public void test(){
        String num1 = "123";
        String num2 = "456";
        String res = multiply1(num1, num2);
        System.out.println(res);
    }
}
