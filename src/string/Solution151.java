package string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串，逐个翻转字符串中的每个单词。

 示例:

 输入: "the sky is blue",
 输出: "blue is sky the".
 说明:

 无空格字符构成一个单词。
 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 进阶: 请选用C语言的用户尝试使用 O(1) 空间复杂度的原地解法。
 *
 */
public class Solution151 {
    //思路：遍历一遍字符串，将该字符串中的单词记录在一个集合中，
    // 最后再将集合从后向前重新构建一个新的字符串
    public String reverseWords(String s) {
        //为了去除字符串首尾两边的空格
        String trim = s.trim();
        int n = trim.length();
        if (n==0){
            return "";
        }
        char[] chars = trim.toCharArray();
        //定义一个起始位置，为了方便后面截取字符串
        int start=0;
        //存放单词的集合
        List<String> list = new ArrayList<>();
        //*这里需要注意subString的起始位置和终止位置的细节！
        for (int i = start; i < n; ) {
            //当遍历到尾部的时候，直接截取
            if (i==n-1){
                String substring = trim.substring(start, i+1);
                list.add(substring);
            }
            //当不为空格的时候，继续向前移动
            if (chars[i]!=' '){
                i++;
            }else   //当前位置为空格的时候，此时需要截取字符串
                {
                    //subString：trim[start,i),前闭后开
                String substring = trim.substring(start, i);
                list.add(substring);
                start=i+1;
                //为了处理连续空格，确保下次循环的起始位置为字符
                while (chars[start]==' '){
                    start++;
                }
                i=start;
            }

        }
        //将集合从后向前重新构建新的字符串
        StringBuilder sb= new StringBuilder();
        for (int i = list.size()-1; i >=0; i--) {
            if (i!=0){
                sb.append(list.get(i)+" ");
            }else {
                sb.append(list.get(i));
            }
        }
        return sb.toString();
    }
    @Test
    public void test(){
        String s="the sky is blue";
        String res = reverseWords(s);
        System.out.println(res);
    }
}
