package find;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution819 {
    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String,Integer> freqMap=new HashMap<>();
        Set<String> bannedSet=new HashSet<>();
        for (String b:banned){
            bannedSet.add(b);
        }
        int start=0;
        while (!Character.isLetter(paragraph.charAt(start))){
            start++;
        }
        for (int i = start+1; i <= paragraph.length(); ) {
            if (i==paragraph.length()||!Character.isLetter(paragraph.charAt(i))){
                //记得要全部转为小写数字
                String word = paragraph.substring(start, i).toLowerCase();
                if (!bannedSet.contains(word)){
                    freqMap.put(word,freqMap.getOrDefault(word,0)+1);
                }
                start=i;
                //寻找下一次循环的起始点，这样确保了paragraph[start，i）为字母
                while (start<paragraph.length()&&!Character.isLetter(paragraph.charAt(start))){
                    start++;
                }
                i=start+1;
            }else {
                i++;
            }
        }

        int maxCount=0;
        for (String s:freqMap.keySet()){
            maxCount=Math.max(maxCount,freqMap.get(s));
        }
        String res="";
        for (String s:freqMap.keySet()){
            if (freqMap.get(s)==maxCount){
                res=s;
            }
        }
        return res;
    }
    @Test
    public void test(){
        String p="Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] b={"hit"};
        String s = mostCommonWord(p, b);
        System.out.println(s);
    }
}
