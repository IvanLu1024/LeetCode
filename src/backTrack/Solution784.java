package backTrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 *
 * 示例:
 * 输入: S = "a1b2"
 * 输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * 输入: S = "3z4"
 * 输出: ["3z4", "3Z4"]
 *
 * 输入: S = "12345"
 * 输出: ["12345"]
 * 注意：
 *
 * S 的长度不超过12。
 * S 仅由数字和字母组成。
 *
 */
public class Solution784 {
    public List<String> letterCasePermutation(String S) {
        List<String> res=new ArrayList<>();
        generate(S.toCharArray(),0,res);
        return res;
    }

    private void generate(char[] chars,int index,List<String> res){
        //当index到达chars的最后一个位置后面一个则说明搜索结束
        //因为此时最后一个位置也处理结束
        if (index==chars.length){
            res.add(new String(chars));
            return;
        }
        if (isNum(chars[index])){
            generate(chars,index+1,res);
            return;
        }
        chars[index]=Character.toUpperCase(chars[index]);
        generate(chars,index+1,res);

        chars[index]=Character.toLowerCase(chars[index]);
        generate(chars,index+1,res);

    }

    private boolean isNum(char c){
        if (c>='0'&&c<='9'){
            return true;
        }else {
            return false;
        }
    }

    private boolean isApl(char c){
        if (c>='a'&&c<='z'||c>='A'&&c<='Z'){
            return true;
        }else {
            return false;
        }
    }

    private boolean isCap(char c){
        if (c>='A'&&c<='Z'){
            return true;
        }else {
            return false;
        }
    }
    @Test
    public void test(){
        String S="a1ba";
        List<String> res = letterCasePermutation(S);
        System.out.println(res);
    }
}
