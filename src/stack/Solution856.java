package stack;

import org.junit.Test;

/**
 * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
 *
 * () 得 1 分。
 * AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
 * (A) 得 2 * A 分，其中 A 是平衡括号字符串。
 *
 *
 * 示例 1：
 *
 * 输入： "()"
 * 输出： 1
 * 示例 2：
 *
 * 输入： "(())"
 * 输出： 2
 * 示例 3：
 *
 * 输入： "()()"
 * 输出： 2
 * 示例 4：
 *
 * 输入： "(()(()))"
 * 输出： 6
 *
 *
 * 提示：
 *
 * S 是平衡括号字符串，且只含有 ( 和 ) 。
 * 2 <= S.length <= 50
 */
public class Solution856 {
    public int scoreOfParentheses(String S) {
        return score(S,0,S.length()-1);

    }
    private int score(String s,int l,int r){
        //() 得 1 分
        if (l+1==r){
            return 1;
        }
        int b=0;
        for (int i = l; i < r; i++) {
            if (s.charAt(i)=='(')
                b++;
            if (s.charAt(i)==')')
                b--;
            //balance状态,score("(A)(B)") = score("(A)") + score("(B)")
            if (b==0){
                return score(s,l,i)+score(s,i+1,r);
            }
        }
        //score("(A)") = 2 * score("A") ，其中 A 是平衡括号字符串
        return 2*score(s,l+1,r-1);
    }

    public int scoreOfParentheses1(String S){
        int res=0;
        //表示括号的深度
        int balance=0;
        char[] chars = S.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i]=='(')
                balance++;
            else {
                balance--;
                //存在一个() 闭合的括号
                if (chars[i-1]=='('){
                    res+=(1<<balance);
                }
            }
        }
        return res;
    }

    @Test
    public void test(){
        String s="()()";
        int i = scoreOfParentheses1(s);
        System.out.println(i);
    }
}
