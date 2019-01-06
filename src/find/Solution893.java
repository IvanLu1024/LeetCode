package find;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 你将得到一个字符串数组 A。

 如果经过任意次数的移动，S == T，那么两个字符串 S 和 T 是特殊等价的。



 一次移动包括选择两个索引 i 和 j，且 i％2 == j％2，并且交换 S[j] 和 S [i]。

 现在规定，A 中的特殊等价字符串组是 A 的非空子集 S，这样不在 S 中的任何字符串与 S 中的任何字符串都不是特殊等价的。



 返回 A 中特殊等价字符串组的数量。



 示例 1：

 输入：["a","b","c","a","c","c"]
 输出：3
 解释：3 组 ["a","a"]，["b"]，["c","c","c"]
 示例 2：

 输入：["aa","bb","ab","ba"]
 输出：4
 解释：4 组 ["aa"]，["bb"]，["ab"]，["ba"]
 示例 3：

 输入：["abc","acb","bac","bca","cab","cba"]
 输出：3
 解释：3 组 ["abc","cba"]，["acb","bca"]，["bac","cab"]
 示例 4：

 输入：["abcd","cdab","adcb","cbad"]
 输出：1
 解释：1 组 ["abcd","cdab","adcb","cbad"]


 提示：

 1 <= A.length <= 1000
 1 <= A[i].length <= 20
 所有 A[i] 都具有相同的长度。
 所有 A[i] 都只由小写字母组成。
 *
 */
public class Solution893 {
    //使用哈希表的解法，是49题的变式
    public int numSpecialEquivGroups(String[] A) {
        if (A==null||A.length==0){
            return 0;
        }
        //K：小组标志，V：频次
        //这里只需要统计小组的数量，频次用不到，所以同样可以使用Set来实现
        Map<String,Integer> freq=new HashMap<>();
        for (String s:A){
            //分别记录偶数位组成的字符串和奇数位组成的字符串
            StringBuilder even = new StringBuilder();
            StringBuilder odd = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (i%2==0){
                    even.append(s.charAt(i));
                }else {
                    odd.append(s.charAt(i));
                }
            }
            char[] evenArr = even.toString().toCharArray();
            char[] oddArr = odd.toString().toCharArray();
            //将偶数位字符串和奇数位字符串分别排序，这样确保了唯一
            Arrays.sort(evenArr);
            Arrays.sort(oddArr);
            String evenStr = new String(evenArr);
            String oddStr = new String(oddArr);
            //将排序好的奇数位字符串和偶数位字符串作为小组的标志
            freq.put(oddStr+evenStr,freq.getOrDefault(evenStr,0)+1);
        }
        return freq.size();
    }
    @Test
    public void test(){
        String[] strings={"abcd","cdab","adcb","cbad"};
        int i = numSpecialEquivGroups(strings);
        System.out.println(i);
    }
}
