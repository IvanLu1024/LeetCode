import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Ivan 19:42
 * @Description TODO
 */
public class InterviewTest {

    /**
     * 题目：给定一个已经排好序的数组，要求给出这个数组不同绝对值的个数
     *
     * 例如：
     * 输入：nums:[-3,-2,0,1,2,2,3,3]
     * 输出：4
     * @param nums
     * @return
     */
    public  int getAbsNum(int[] nums){
        if (nums==null||nums.length==0)
            return 0;
        int l=0,h=nums.length-1;
        int count=0;
        while (l<=h){
            //去重操作
            while (h>0&&nums[h]==nums[h-1])h--;
            while (l<nums.length-1&&nums[l]==nums[l+1])l++;
            //此时绝对值相同，移动两位
            if (nums[l]+nums[h]==0){
                l++;
                h--;
            }
            //值偏小，右移
            else if (nums[l]+nums[h]<0){
                l++;
            }else {
                //值偏大，左移
                h--;
            }
            count++;
        }
        return count;
    }

    //数组中存在n个元素，要求获得m个元素的所有组合
    public List<List<Integer>> combination(int[] nums,int m){
        List<List<Integer>> res=new ArrayList<>();
        int n = nums.length;
        if (n==0||m>n){
            return res;
        }
        generate(nums,m,0,new ArrayList<>(),res);
        return res;
    }

    private void generate(int[] nums,int m,int index,List<Integer> list,List<List<Integer>> res){
        if (list.size()==m){
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            generate(nums,m,i+1,list,res);
            list.remove(list.size()-1);
        }
    }

    @Test
    public void test(){
        int[] nums={1,2,3,4};
        int m=2;
        List<List<Integer>> combination = combination(nums, m);
        System.out.println(combination);

    }
}
