package string;

import org.junit.Test;

/**

 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：

 1.     1
 2.     11
 3.     21
 4.     1211
 5.     111221
 1 被读作  "one 1"  ("一个一") , 即 11。
 11 被读作 "two 1s" ("两个一"）, 即 21。
 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。

 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。

 注意：整数顺序将表示为一个字符串。



 示例 1:

 输入: 1
 输出: "1"
 示例 2:

 输入: 4
 输出: "1211"
 *
 * @create 2019-02-16 14:36
 **/
public class Solution38 {
    public String countAndSay(int n) {
        if (n<=0){
            return "";
        }
        if(n==1){
            return "1";
        }
        String s = countAndSay(n - 1);
        char[] chars = s.toCharArray();
        int count=1;
        //用于记录前一个位置的字符
        char pre=chars[0];
        StringBuilder res=new StringBuilder();
        //根据n-1，进行报数，并构建新的字符串
        for (int i = 1; i < chars.length; i++) {
            //相邻位置相同则累加
            if (chars[i]==pre){
                count++;
            }
            //不同则报数
            else {
                res.append(count);
                res.append(pre);
                pre=chars[i];
                count=1;
            }
        }
        //最后位置不要忘记添加上
        res.append(count);
        res.append(pre);

        return res.toString();
    }
    @Test
    public void test(){
        int n=4;
        String s = countAndSay(n);
        System.out.println(s);
    }
}
