package stack;

import org.junit.Test;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 *
 */
public class Solution20 {
    public boolean isValid(String s) {
        if (s==null||s.length()==0){
            return true;
        }
        char[] chars = s.toCharArray();
        Stack<Character> stack=new Stack<>();
        for (char c : chars) {
            if (c=='('||c=='['||c=='{'){
                stack.push(c);
            }else {
                if (stack.empty()){
                    return false;
                }
                Character match = stack.pop();
                switch (match){
                    case '(':match=')';break;
                    case '[':match=']';break;
                    case '{':match='}';break;
                }
                if (c!=match){
                    return false;
                }
            }
        }
        if (!stack.empty()){
            return false;
        }
        return true;

    }

    @Test
    public void test(){
        String s="([)]";
        boolean res = isValid(s);
        System.out.println(res);

    }
}
