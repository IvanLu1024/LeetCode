package find;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 你正在和你的朋友玩 猜数字（Bulls and Cows）游戏：你写下一个数字让你的朋友猜。每次他猜测后，你给他一个提示，
 *
 * 告诉他有多少位数字和确切位置都猜对了（称为“Bulls”, 公牛），有多少位数字猜对了但是位置不对（称为“Cows”, 奶牛）。你的朋友将会根据提示继续猜，直到猜出秘密数字。
 *
 * 请写出一个根据秘密数字和朋友的猜测数返回提示的函数，用 A 表示公牛，用 B 表示奶牛。
 *
 * 请注意秘密数字和朋友的猜测数都可能含有重复数字。
 *
 * 示例 1:
 *
 * 输入: secret = "1807", guess = "7810"
 *
 * 输出: "1A3B"
 *
 * 解释: 1 公牛和 3 奶牛。公牛是 8，奶牛是 0, 1 和 7。
 * 示例 2:
 *
 * 输入: secret = "1123", guess = "0111"
 *
 * 输出: "1A1B"
 *
 * 解释: 朋友猜测数中的第一个 1 是公牛，第二个或第三个 1 可被视为奶牛。
 * 说明: 你可以假设秘密数字和朋友的猜测数都只包含数字，并且它们的长度永远相等。
 *
 */
public class Solution299 {
    //使用查找表
    public String getHint(String secret, String guess) {
        //K：下标；V：数值（字符）
        Map<Integer,Character> map = new HashMap<>();
        //用来记录出现的数值（0-9）
        int[] freq=new  int[10];
        for (int i = 0; i < secret.length(); i++) {
            char c = secret.charAt(i);
            freq[c-'0']++;
            map.put(i,c);
        }
        int bulls=0;
        int cows=0;
        //首先统计bulls的个数
        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
            if (map.get(i).equals(c)){
                freq[c-'0']--;
                bulls++;
            }
        }
        //再来统计cows的个数
        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
                if (!map.get(i).equals(c)&&freq[c-'0']>0){
                    cows++;
                    freq[c-'0']--;
                }
        }
        return bulls+"A"+cows+"B";
    }

    public String getHint1(String secret, String guess){
        int[] sFreq=new int[10];
        int[] gFreq=new int[10];
        int bulls=0,cows=0;
        for (int i = 0; i < secret.length(); i++) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);
            if (s==g){
                bulls++;
            }else {
                sFreq[s-'0']++;
                gFreq[g-'0']++;
            }
        }
        for (int i = 0; i < 10; i++) {
            cows+=Math.min(sFreq[i],gFreq[i]);
        }
        return bulls+"A"+cows+"B";
    }
    @Test
    public void test(){
        /*"1122"
"1222"*/
        String s="1807";
        String g="7810";
        String res = getHint1(s, g);
        System.out.println(res);
    }
}
