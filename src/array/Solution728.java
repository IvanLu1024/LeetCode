package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 自除数 是指可以被它包含的每一位数除尽的数。
 *
 * 例如，128 是一个自除数，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
 *
 * 还有，自除数不允许包含 0 。
 *
 * 给定上边界和下边界数字，输出一个列表，列表的元素是边界（含边界）内所有的自除数。
 *
 * 示例 1：
 *
 * 输入：
 * 上边界left = 1, 下边界right = 22
 * 输出： [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 * 注意：
 *
 * 每个输入参数的边界满足 1 <= left <= right <= 10000。
 *
 */
public class Solution728 {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res=new ArrayList<>();
        for (int i = left; i <=right ; i++) {
            if (isSelfDividing(i)){
                res.add(i);
            }
        }
        return res;
    }
    //判断是否为自除数
    private boolean isSelfDividing(int n){
        if (n<10){
            return true;
        }
        int nexN=n;
        while (nexN>0){
            //取出各位的值
            int i=nexN%10;
            //包含0
            if (i==0){
                return false;
            }
            //不满足条件
            if (n%i!=0){
                return false;
            }
            nexN=nexN/10;
        }
        return true;
    }
    @Test
    public void test(){
        boolean flag = isSelfDividing(10000);
        System.out.println(flag);

        int left=1;
        int right=10000;
        List<Integer> list = selfDividingNumbers(left, right);
        System.out.println(list);
    }
}
