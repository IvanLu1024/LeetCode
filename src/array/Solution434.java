package array;

import org.junit.Test;

/**
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 *
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 *
 * 示例:
 *
 * 输入: "Hello, my name is John"
 * 输出: 5
 *
 */
public class Solution434 {

    public int countSegments(String s) {
        int count=0;
        int start=0;
        if (s.length()==0||s==null){
            return count;
        }
        //搜索起始位置
        while (start<s.length()&&s.charAt(start)==' '){
            start++;
        }
        //
        for (int i = start+1; i <= s.length(); i++) {
            //当出现了不连续的空格的时候或者超过搜索范围的时候表示有一个单词，此时计数器加一
            if ((i==s.length()||(s.charAt(i)!=' '&&s.charAt(i-1)==' '))){
                count++;
            }
        }

        return count;
    }
    @Test
    public void test(){
        String s="Hello, my name is John";
        int i = countSegments(s);
        System.out.println(i);
    }
}
