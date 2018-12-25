package stack;

import org.junit.Test;

import java.util.Stack;

/**
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 *
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 *
 * 示例 1:
 *
 * 输入: "3+2*2"
 * 输出: 7
 * 示例 2:
 *
 * 输入: " 3/2 "
 * 输出: 1
 * 示例 3:
 *
 * 输入: " 3+5 / 2 "
 * 输出: 5
 * 说明：
 *
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 */
public class Solution227 {
    /*使用一个栈保存数字，如果该数字之前的符号是加或减，那么把当前数字压入栈中，注意如果是减号，
    则加入当前数字的相反数，因为减法相当于加上一个相反数。
    如果之前的符号是乘或除，那么从栈顶取出一个数字和当前数字进行乘或除的运算，再把结果压入栈中，
    那么完成一遍遍历后，所有的乘或除都运算完了，再把栈中所有的数字都加起来就是最终结果了。*/
    public int calculate(String s) {
        if (s==null||s.length()==0){
            return 0;
        }
        Stack<Integer> stack=new Stack<>();
        int num=0,res=0;
        char op='+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isNum(c)){
                num=num*10+(c-'0');
            }
            if ((c < '0' && c != ' ') || i == s.length() - 1){
                if (op=='+')stack.push(num);
                if (op=='-')stack.push(-num);
                if (op=='*')stack.push(stack.pop()*num);
                if (op=='/')stack.push(stack.pop()/num);
                op=c;
                num=0;
            }
        }
        while (!stack.empty()){
            res+=stack.pop();
        }
        return res;
    }

    public int calculate1(String s) {
        if (s==null||s.length()==0){
            return 0;
        }
        Stack<Integer> stack=new Stack<>();
        int num=0,res=0;
        char op='+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isNum(c)){
                num=num*10+(c-'0');
            }
            //** 注意不要忘记使用break！！！
            if ((c < '0' && c != ' ') || i == s.length() - 1){
                switch (op){
                    case '+':stack.push(num);break;
                    case '-':stack.push(-num);break;
                    case '*':stack.push(stack.pop()*num);break;
                    case '/':stack.push(stack.pop()/num);break;
                }
                op=c;
                num=0;
            }
        }
        while (!stack.empty()){
            res+=stack.pop();
        }
        return res;
    }

    private boolean isNum(Character c){
        return c>='0'&&c<='9';
    }
    @Test
    public void test(){
        String s="3+2*2";
        int res = calculate1(s);
        System.out.println(res);
    }
}
