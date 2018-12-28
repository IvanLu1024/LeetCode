package backTrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 */
public class Solution22 {
    private List<String> res;
    //回溯法
    public List<String> generateParenthesis(int n) {
        res=new ArrayList<>();
        generate(n,n,"");
        return res;
    }
    //left：剩余的左括号的数量
    //right：剩余的右括号的数量
    private void generate(int left,int right,String s){
        if (left==0&&right==0){
            System.out.println("finished : "+s);
            res.add(s);
            return;
        }
        //填充左括号
        if (left>0){
            System.out.println("add left -> "+s);
            generate(left-1,right,s+"(");
        }
        //当前的左括号数量大于当前的右括号的数量的时候，填充右括号
        if (left<right){
            System.out.println("add right -> "+s);
            generate(left,right-1,s+")");
        }
    }
    @Test
    public void test(){
        int n=3;
        List<String> list = generateParenthesis(n);
        System.out.println(list);
    }
}
