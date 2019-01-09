package array;

import org.junit.Test;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 *
 */
public class Solution14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs==null||strs.length==0){
            return "";
        }
        StringBuilder sb= new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            //公共前缀的元素一定来自于第一个字符串
            char c = strs[0].charAt(i);
            //从其他所有字符串中按位搜索
            for (int j = 1; j < strs.length; j++) {
                //如果当前的字符串已经遍历结束或者存在不同的元素就直接返回结果
                if (i>=strs[j].length()||strs[j].charAt(i)!=c){
                    return sb.toString();
                }
            }
            //若其他字符串的这一位都相等的话则添加到结果集合中
            sb.append(c);
        }
        return sb.toString();
    }
    @Test
    public void test(){
        String[] strs={"flower","flow","flight"};
        String s = longestCommonPrefix(strs);
        System.out.println(s);
    }
}
