package find;

import org.junit.Test;

import java.util.*;

/**
 *给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 *
 * 示例 1:
 *
 * 输入:
 * "tree"
 *
 * 输出:
 * "eert"
 *
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 示例 2:
 *
 * 输入:
 * "cccaaa"
 *
 * 输出:
 * "cccaaa"
 *
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * 示例 3:
 *
 * 输入:
 * "Aabb"
 *
 * 输出:
 * "bbAa"
 *
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 *
 */
public class Solution451 {
    public String frequencySort(String s) {
        int[] freq=new int[256];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)]++;
        }
        Map<Integer,List<Character>> map=new HashMap<>();
        for (int i = 0; i < 256; i++) {
            if (freq[i]==0)
                continue;
            if (!map.containsKey(freq[i]))
             {
                map.put(freq[i],new ArrayList<>());
            }
            map.get(freq[i]).add((char)i);
        }
        StringBuilder sb=new StringBuilder();
        for (int i = s.length(); i > 0; i--) {
            if (map.containsKey(i)){
                List<Character> characters = map.get(i);
                for (Character c:characters) {
                    for (int j = 0; j < i; j++) {
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }
    @Test
    public void test(){
        String s="Aabb";
        String res = frequencySort(s);
        System.out.println(res);
    }

}
