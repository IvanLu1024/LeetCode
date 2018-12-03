package dp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 */
public class Solution139 {
    private boolean[] memo;
    /*
    dp[i]表示字符串s[0...i)能否拆分成符合要求的子字符串。
    如果S能够被“字典集合”（dict）中的单词拼接而成，所以满足下列方程：
    状态转移方程：
    f(0)=true
    f(i)=f(j)&&dict.contains(s[j,i))，0<j<i
    * */
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        if (n==0){
            return false;
        }
        memo=new boolean[n+1];
        memo[0]=true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                String tStr = s.substring(j, i);
                if (memo[j]&&wordDict.contains(tStr)){
                    memo[i]=true;
                    break;
                }
            }
        }
        return memo[n];
    }
    @Test
    public void test(){
        List<String> dict=new ArrayList<>();
        String[] strings={"leet", "code"};
        for (String s: strings) {
            dict.add(s);
        }
        String string="leetcode";
        boolean b = wordBreak(string, dict);
        System.out.println(b);


    }
}
