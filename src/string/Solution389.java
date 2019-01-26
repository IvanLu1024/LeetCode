package string;

import org.junit.Test;

/**
 给定两个字符串 s 和 t，它们只包含小写字母。

 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。

 请找出在 t 中被添加的字母。



 示例:

 输入：
 s = "abcd"
 t = "abcde"

 输出：
 e

 解释：
 'e' 是那个被添加的字母。
 */
public class Solution389 {
    //使用异或运算:相同为0，不同为1
    public char findTheDifference(String s, String t) {
        if (s==null||s.length()==0){
            return t.charAt(0);
        }
        char res=s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            res^=s.charAt(i);
        }
        for (int i = 0; i < t.length(); i++) {
            res^=t.charAt(i);
        }
        return res;
    }
    @Test
    public void test(){
        String s="abcd";
        String t="abcde";
        char res = findTheDifference(s, t);
        System.out.println(res);
    }
}
