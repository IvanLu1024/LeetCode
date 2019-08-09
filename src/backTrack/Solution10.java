package backTrack;

import org.junit.Test;

/**
 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。

 '.' 匹配任意单个字符。
 '*' 匹配零个或多个前面的元素。
 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。

 说明:

 s 可能为空，且只包含从 a-z 的小写字母。
 p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 示例 1:

 输入:
 s = "aa"
 p = "a"
 输出: false
 解释: "a" 无法匹配 "aa" 整个字符串。
 示例 2:

 输入:
 s = "aa"
 p = "a*"
 输出: true
 解释: '*' 代表可匹配零个或多个前面的元素, 即可以匹配 'a' 。因此, 重复 'a' 一次, 字符串可变为 "aa"。
 示例 3:

 输入:
 s = "ab"
 p = ".*"
 输出: true
 解释: ".*" 表示可匹配零个或多个('*')任意字符('.')。
 示例 4:

 输入:
 s = "aab"
 p = "c*a*b"
 输出: true
 解释: 'c' 可以不被重复, 'a' 可以被重复一次。因此可以匹配字符串 "aab"。
 示例 5:

 输入:
 s = "mississippi"
 p = "mis*is*p*."
 输出: false


 */
public class Solution10 {
    //回溯法

    //我们采用从后往前匹配，为什么这么匹配，
    // 因为如果我们从前往后匹配，每个字符我们都得判断是否后面跟着“*”，
    // 而且还要考虑越界的问题。但是从后往前没这个问题，一旦遇到“*”，前面必然有个字符。
    public boolean isMatch(String s, String p) {
        //从后前向开始搜索
        return match(s.toCharArray(),p.toCharArray(),s.length()-1,p.length()-1);
    }
    private boolean match(char[] s,char[] p,int i,int j){
        if (j==-1){
            //j=-1，表明p已经搜索结束，若i=-1的时候表明s也同时搜索结束，
            // 则匹配成功，否则没有匹配上
            if (i==-1){
                return true;
            }else {
                return false;
            }
        }
        //若i=-1，但是j>0,并且p中还存在*的时候继续搜索
        if (p[j]=='*'){
            //若*前面的元素可以匹配上则继续向前匹配
            if (i>-1&&(p[j-1]=='.'||p[j-1]==s[i])) {
                if (match(s, p, i - 1, j)){
                    return true;
                }
            }
            //表明直接跳过*
            return match(s,p,i,j-2);
        }
        //如果j遇到的不是“*”，那么我们就直接看s[i]和p[j]是否匹配，若不匹配就说明错了，返回
        if (i>-1&&(p[j]=='.'||p[j]==s[i]))
            return match(s,p,i-1,j-1);
        return false;
    }

    @Test
    public void test(){
        String s="mississippi";
        String p="mis*is*p*.";
        boolean b = isMatch(s, p);
        System.out.println(b);

    }
}
