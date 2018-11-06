package array;

import org.junit.Test;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 *
 */
public class Solution125 {
    public boolean isPalindrome(String s) {
        if (s==null||s.length()==0){
            return true;
        }
        char[] chars = s.toLowerCase().toCharArray();
        int l=0,h=chars.length-1;
        while (l<h){
            while (l<h&&!isNumApl(chars[l])) l++;
            while (l<h&&!isNumApl(chars[h])) h--;

            if (chars[l]==chars[h]){
                l++;
                h--;
            }else {
                return false;
            }
        }
        return true;
    }
    private boolean isNumApl(char c){
        if (c>='a'&&c<='z'){
            return true;
        }
        if (c>='A'&&c<='Z'){
            return true;
        }
        if (c>='0'&&c<='9'){
            return true;
        }
        return false;
    }
    @Test
    public void test(){
        String s="A man, a plan, a canal: Panama";
        boolean res = isPalindrome(s);
        System.out.println(res);


    }





}
