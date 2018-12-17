package find;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Solution804 {
    public int uniqueMorseRepresentations(String[] words) {
        String[] codes={".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        Set<String> codeSet=new HashSet<>();
        for (String word:words){
            char[] chars = word.toCharArray();
            StringBuilder sb=new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
                sb.append(codes[chars[i]-'a']);
            }
            codeSet.add(sb.toString());
        }
        return codeSet.size();
    }
    @Test
    public void test(){
        String[] words={"gin", "zen", "gig", "msg"};
        int r = uniqueMorseRepresentations(words);
        System.out.println(r);
    }
}
