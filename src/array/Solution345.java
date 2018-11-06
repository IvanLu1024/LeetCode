package array;

import org.junit.Test;


/**
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 *
 * 示例 1:
 *
 * 输入: "hello"
 * 输出: "holle"
 *
 */
public class Solution345 {
    public String reverseVowels(String s) {
        char[] vowels={'a','e','i','o','u','A','E','I','O','U'};
        int[] freq=new int[256];
        for (char c:vowels) {
            freq[c]=1;
        }
        char[] chars = s.toCharArray();
        int l=0,h=chars.length-1;
        while (l<h){
            while (l<h&&freq[chars[l]]!=1)l++;
            while (l<h&&freq[chars[h]]!=1)h--;
            if (l<h){
                char t=chars[l];
                chars[l]=chars[h];
                chars[h]=t;
                l++;
                h--;
            }
        }
        return new String(chars);
    }

    @Test
    public void test(){
        String s="aaee";
        String s1 = reverseVowels(s);
        System.out.println(s1);
    }
}
