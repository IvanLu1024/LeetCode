package string;

import org.junit.Test;

/**
 * @Author Ivan 14:30
 * @Description TODO
 */
public class Solution415 {
    public String addString(String num1,String num2){
        //确保num1的长度较长
        if (num1.length()<num2.length()){
            String t=num1;
            num1=num2;
            num2=t;
        }
        if (num2.length()==1&&num2.charAt(0)=='0'){
            return num1;
        }
        int n1=num1.length();
        int n2=num2.length();
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        int[] res=new int[n1+1];
        for (int i = n1-1,j=n2-1; i >=0; i--) {
            if (j>=0){
                int sum=chars1[i]-'0'+chars2[j]-'0';
                res[i+1]=sum;
                j--;
            }else {
                res[i+1]=chars1[i]-'0';
            }
        }
        //进位处理
        for (int i = n1; i >0; i--) {
            int out=res[i]/10;
            res[i-1]+=out;
            res[i]%=10;
        }
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i <= n1; i++) {
            if (sb.length()==0&&res[i]==0){
                continue;
            }
            sb.append(res[i]);
        }
        return sb.toString();
    }

    public String addString1(String num1,String num2){
        int n1 = num1.length()-1;
        int n2 = num2.length()-1;
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        int out=0;
        StringBuilder sb=new StringBuilder();
        while (n1>=0||n2>=0||out>0){
            int sum=out;
            if (n1>=0){
                sum+=chars1[n1--]-'0';
            }
            if (n2>=0){
                sum+=chars2[n2--]-'0';
            }
            sb.append(sum%10);
            out=sum/10;
        }
        return sb.reverse().toString();
    }

    @Test
    public void test(){
        String num1 = "0";
        String num2 = "0";
        String res = addString1(num1, num2);
        System.out.println(res);
    }
}
