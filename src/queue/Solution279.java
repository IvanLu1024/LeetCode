package queue;

import javafx.util.Pair;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。
 * 你需要让组成和的完全平方数的个数最少。

 示例 1:

 输入: n = 12
 输出: 3
 解释: 12 = 4 + 4 + 4.
 示例 2:

 输入: n = 13
 输出: 2
 解释: 13 = 4 + 9.
 * @author
 * @create 2018-11-12 20:30
 **/
public class Solution279 {

    //使用图论的解法
    public int numSquares(int n)  {
        //queue<<num,step>>,num:表示目标数字；step:表示达到目标数字的步数
        Queue<Pair<Integer,Integer>> queue=new LinkedList<>();
        //设置一个数组来标记目标数字是否被访问过
        boolean[] flagArray=new boolean[n+1];
        Arrays.fill(flagArray,false);
        queue.offer(new Pair<>(n,0));
        while (!queue.isEmpty()){
            Pair<Integer, Integer> poll = queue.poll();
            int num=poll.getKey();
            int step = poll.getValue();
            for (int i = 0; ; i++) {
                int a=num-i*i;  //每次移动都是完全平方数
                if (a<0) {
                    break;
                }
                if (a==0) {     //表示达到终点
                    return step + 1;
                }
                if (!flagArray[a]){     //若目标数字没有被访问过才将其入队操作
                    queue.offer(new Pair<>(a,step+1));
                    flagArray[num]=true;
                }

            }
        }
        return n;
    }
    @Test
    public void test(){
        int n=13;

        int i=numSquares(n);
        System.out.println(i);

    }
}
