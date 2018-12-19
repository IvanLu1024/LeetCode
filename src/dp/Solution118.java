package dp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res=new ArrayList<>();
        if (numRows<=0){
            return res;
        }
        res.add(Arrays.asList(new Integer[]{1}));
        for (int i = 1; i < numRows; i++) {
            List<Integer> row=new ArrayList<>();
            row.add(1);
            for (int j = 1; j <i ; j++) {
                row.add(res.get(i-1).get(j-1)+res.get(i-1).get(j));
            }
            row.add(1);
            res.add(row);
        }
        return res;
    }
    @Test
    public void test(){
        int rows=5;
        List<List<Integer>> res = generate(rows);
        System.out.println(res);
    }
}
