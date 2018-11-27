package backTrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Solution131 {
    private List<List<String>> res=new ArrayList<>();
    public List<List<String>> partition(String s) {
        if (s==null||s.length()==0){
            return res;
        }
        generatePart(s,0,new ArrayList<>());
        return res;

    }
    private void generatePart(String s,int index,List<String> list){
        if (index==s.length()){
            System.out.println("completed is:"+list);
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (isHuiwen(s,index,i)){
                System.out.println("current Huiwen:"+s.substring(index,i+1)+"->"+list);
                list.add(s.substring(index,i+1));
                //下次递归从i+1开始搜索
                generatePart(s,i+1,list);
                list.remove(list.size()-1);
                System.out.println("backTracking...list:"+list);
            }

        }
    }

    private boolean isHuiwen(String s,int l,int h){
        while (l<h){
            if (l<s.length()&&h>=0&&s.charAt(l++)!=s.charAt(h--)){
                return false;
            }
        }
        return true;
    }
    @Test
    public void test(){
        String s="aab";
        List<List<String>> r = partition(s);
        System.out.println(r);
    }

}
