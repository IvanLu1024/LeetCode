package string;

import org.junit.Test;

/**

 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。

 如果不存在最后一个单词，请返回 0 。

 说明：一个单词是指由字母组成，但不包含任何空格的字符串。

 示例:

 输入: "Hello World"
 输出: 5
 *
 *
 * @create 2019-01-29 21:30
 **/
public class Solution58 {

    public int lengthOfLastWord(String s) {
        if (s==null||s.length()==0){
            return 0;
        }
        String[] strs = s.split(" ");
        if (strs.length==0){
            return 0;
        }
        return strs[strs.length-1].length();
    }
    @Test
    public void test(){
        String s="Hello World";
        int i = lengthOfLastWord(s);
        System.out.println(i);

    }
}
