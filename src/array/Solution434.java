package array;

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
        while (!Character.isLetter(s.charAt(start))){
            start++;
        }
        for (int i = start+1; i <= s.length(); i++) {
            
        }

        return count;

    }
}
