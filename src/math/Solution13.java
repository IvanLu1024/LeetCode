package math;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。

 字符          数值
 I             1
 V             5
 X             10
 L             50
 C             100
 D             500
 M             1000
 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

 I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。


 */
public class Solution13 {
    public int romanToInt(String s) {
        if (s==null||s.length()==0){
            return 0;
        }
        char[] dict={'M','D','C','L','X','V','I'};
        int[] nums={1000,500,100,50,10,5,1};
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < dict.length; i++) {
            map.put(dict[i],nums[i]);
        }
        int res=0;
        //从左向右遍历，当前的符号的值不小于右边符号的值的时候加上当前值
        //反之，小于的时候，则减去当前值
        for (int i = 0; i < s.length()-1; i++) {
            char cur = s.charAt(i);
            char right = s.charAt(i + 1);
            if (map.get(cur)>=map.get(right)){
                res+=map.get(cur);
            }else {
                res-=map.get(cur);
            }
        }
        res+=map.get(s.charAt(s.length()-1));
        return res;
    }
    @Test
    public void test(){
        String s="IX";
        int i = romanToInt(s);
        System.out.println(i);
    }
}
