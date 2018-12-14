package stackAndQueue;

import org.junit.Test;

import java.util.Stack;

/**
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 * 说明：
 *
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 示例 1：
 *
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: ((2 + 1) * 3) = 9
 * 示例 2：
 *
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: (4 + (13 / 5)) = 6
 *
 */
public class Solution150 {
    public int evalRPN(String[] tokens) {
        if (tokens==null||tokens.length==0){
            return 0;
        }
        Stack<String> stack=new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (!isSymbol(tokens[i])){
                stack.push(tokens[i]);
            }else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res=0;
                if (tokens[i].equals("+")){
                    res=num1+num2;
                }else if (tokens[i].equals("-")){
                    res=num1-num2;
                } else if (tokens[i].equals("*")) {
                    res=num1*num2;
                }else if (tokens[i].equals("/")){
                    res=num1/num2;
                }
                stack.push(String.valueOf(res));
            }
        }
        int res = Integer.parseInt(stack.pop());
        return res;

    }
    private boolean isSymbol(String s){
        if (s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/")){
            return true;
        }else {
            return false;
        }
    }

    @Test
    public void test(){
        String[] s={"4", "13", "5", "/", "+"};
        int i = evalRPN(s);
        System.out.println(i);
    }
}
