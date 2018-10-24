package find;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

     示例:

     输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
     输出:
     [
     ["ate","eat","tea"],
     ["nat","tan"],
     ["bat"]
     ]
     说明：

     所有输入均为小写字母。
     不考虑答案输出的顺序。
 *
 * @author
 * @create 2018-10-24 22:47
 **/
public class Solution49 {
    //使用查找表的方式，搞明白需要找出的是什么--字符串
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res=new ArrayList<>();
        if (strs==null||strs.length==0){
            return res;
        }
        //用于记录字母异位词集合的map
        Map<String,List<String>> record=new HashMap<>();
        for (String s:strs){
            char[] chars = s.toCharArray();
            //将字符串做排序处理后，字母异位词就可以映射到一起
            Arrays.sort(chars);
            String sortedStr = String.valueOf(chars);
            if (record.containsKey(sortedStr)){
                record.get(sortedStr).add(s);
            }else {
                List<String> list=new ArrayList<>();
                list.add(s);
                record.put(sortedStr,list);
            }
        }
        res.addAll(record.values());
        return res;

    }
    @Test
    public void test(){
        String[] strings={"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> res = groupAnagrams(strings);
        System.out.println(res);
    }
}
