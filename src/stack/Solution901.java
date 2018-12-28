package stack;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Solution901 {
    class StockSpanner {
        //Pair<K,V>,K:price,V:next
        private List<Pair<Integer,Integer>> nums;

        public StockSpanner() {
            nums=new ArrayList<>();
        }

        public int next(int price) {
            //初始化，
            if (nums.size()==0||price<nums.get(nums.size()-1).getKey()){
                nums.add(new Pair<>(price,1));
                return 1;
            }
            int count=1;
            while (nums.size()!=0&&nums.get(nums.size()-1).getKey()<=price){
                count+=nums.get(nums.size()-1).getValue();
                nums.remove(nums.size()-1);
            }
            nums.add(new Pair<>(price,count));
            return count;
        }
    }
}
