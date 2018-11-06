package array;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * 编写一个函数，其作用是将输入的字符串反转过来。
 *
 * 示例 1:
 *
 * 输入: "hello"
 * 输出: "olleh"
 *
 */
public class Solution344 {

    public String reverseString(String s) {
        if (s==null||s.length()==0){
            return null;
        }
        char[] chars = s.toCharArray();
        int l=0,h=chars.length-1;
        while (l<h){
            char t=chars[l];
            chars[l]=chars[h];
            chars[h]=t;
            l++;
            h--;
        }
        return new String(chars);
    }
    @Test
    public void test(){
        String s="A man, a plan, a canal: Panama";
        String s1 = reverseString(s);
        System.out.println(s1);
    }
}
