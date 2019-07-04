package find;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 编写一个算法来判断一个数是不是“快乐数”。
 *
 * 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，
 * 也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。
 *
 * 示例:
 *
 * 输入: 19
 * 输出: true
 * 解释:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 */
public class Solution202 {
    public boolean isHappy(int n) {
        if (n<1){
            return false;
        }
        Set<Integer> set=new HashSet<>();
        while (!set.contains(n)){
            if (n==1){
                return true;
            }
            set.add(n);
            n=getHappy(n);
        }
        return false;
    }

    //使用快慢指针
    public boolean isHappy1(int n) {
        if(n<1){
            return false;
        }
        int slow=n;
        int fast=n;
        do{
            slow=getHappy(slow);
            fast=getHappy(getHappy(fast));
        }while(fast!=slow);
        if(fast==1){
            return true;
        }
        return false;
    }

    private int getHappy(int n) {
        int t,sum=0;
        while (n>0){
            t=n%10;
            n/=10;
            sum+=t*t;
        }
        return sum;
    }
    @Test
    public void test(){
        int n=11;
        boolean r = isHappy(n);
        System.out.println(r);
    }
}
