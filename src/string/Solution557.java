package string;

import org.junit.Test;

/**
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。

 示例 1:

 输入: "Let's take LeetCode contest"
 输出: "s'teL ekat edoCteeL tsetnoc"
 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 *
 */
public class Solution557 {
    public String reverseWords(String s) {
        if (s==null||s.length()==0){
            return "";
        }
        //在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格
        //所以按照空格来切分原字符串
        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();
        //将每个单词都翻转，并且按照空格来分割重新构建一个新的字符串
        for (String str:strs){
            char[] chars = str.toCharArray();
            reverse(chars);
            String reStr = new String(chars);
            sb.append(reStr+" ");
        }
        String res = sb.toString();
        //为了去除最后一个单词后面的空格
        return res.trim();
    }
    private void reverse(char[] chars){
        int l=0,h=chars.length-1;
        while (l<h){
            char c=chars[l];
            chars[l]=chars[h];
            chars[h]=c;
            l++;
            h--;
        }
    }
    @Test
    public void test(){
        String s="Let's take LeetCode contest";
        String res = reverseWords(s);
        System.out.println(res);
    }
}
