package queue;

import org.junit.Test;

/**
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，
 *
 * 并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 *
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 *
 * 你需要计算完成所有任务所需要的最短时间。
 *
 * 示例 1：
 *
 * 输入: tasks = ["A","A","A","B","B","B"], n = 2
 * 输出: 8
 * 执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 * 注：
 *
 * 任务的总个数为 [1, 10000]。
 * n 的取值范围为 [0, 100]。
 *
 */
public class Solution621 {
    //将出现最多的任务首先排列好，然后的任务在这个基础上采取插空的办法
    public int leastInterval(char[] tasks, int n) {
        int[] tasksFreq=new int[26];
        for (char t:tasks){
            tasksFreq[t-'A']++;
        }
        //出现的最大次数
        int max=0;
        //最大次数出现的下标
        int maxIndex=0;
        for (int i = 0; i < tasksFreq.length; i++) {
            if (tasksFreq[i]>max){
                maxIndex=i;
                max=tasksFreq[i];
            }
        }
        //频率最高元素的个数
        int maxCount=0;
        for (int i:tasksFreq){
            if (i==max){
                maxCount++;
            }
        }
        //(max-1):空格数；(n+1)：空格宽度；
        return Math.max(tasks.length,(max-1)*(n+1)+maxCount);
    }
    @Test
    public void test(){
        char[] tasks={'A','B','A','B','A','B'};
        int n=2;
        int i = leastInterval(tasks, n);
        System.out.println(i);
    }
}
