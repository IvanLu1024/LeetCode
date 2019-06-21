package greedy;

import org.junit.Test;

/**
 * @Author Ivan 14:33
 * @Description TODO
 */
public class Solution134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int[] run = new int[n];
        for (int i = 0; i < n; i++) {
            run[i]=gas[i]-cost[i];
        }
        int resIndex=-1;
        for (int i = 0; i < n; i++) {
            if (run[i]>=0){
                int sum=0;
                for (int j = 0; j < n; j++) {
                    sum+=run[(i+j)%n];
                    if (sum<0){
                        break;
                    }
                }
                if (sum>=0){
                    resIndex=i;
                    return resIndex;
                }
            }
        }
        return resIndex;
    }

    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int n = gas.length;
        //总的油量
        int acc=0;
        //目标起点
        int resIndex=0;
        //起点为resIndex的方案的当前油量
        int sum=0;
        for (int i = 0; i < n; i++) {
            sum+=gas[i]-cost[i];
            acc+=gas[i]-cost[i];
            if (sum<0){
                //当前起点无法行驶完成，
                // 从当前位置的下一个位置继续搜索
                resIndex=i+1;
                sum=0;
            }
        }
        if (acc<0){
            resIndex=-1;
        }
        return resIndex;
    }
    @Test
    public void test(){
        //[1,2,3,4,5,5,70]
        // [2,3,4,3,9,6,2]
        int[] gas={8,2,3,4,2};
        int[] costs={3,4,5,1,6};
        int i = canCompleteCircuit1(gas, costs);
        System.out.println(i);
    }
}
