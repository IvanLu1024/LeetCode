package backTrack;

import org.junit.Test;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.ArrayList;
import java.util.List;

/**
 *二进制手表顶部有 4 个 LED 代表小时（0-11），底部的 6 个 LED 代表分钟（0-59）。

 每个 LED 代表一个 0 或 1，最低位在右侧。

 例如，上面的二进制手表读取 “3:25”。

 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。

 案例:

 输入: n = 1
 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 */
public class Solution401 {
    private List<String> res=new ArrayList<>();
    public List<String> readBinaryWatch(int num) {
        String time;
        for (int i = 0; i <= num; i++) {
            List<Integer> hours = binCount(11, i);
            List<Integer> mins = binCount(59, num - i);
            for (int j = 0; j < hours.size(); j++) {
                String hour = String.valueOf(hours.get(j));
                for (int k = 0; k < mins.size(); k++) {
                    String min= String.valueOf(mins.get(k));
                    if (mins.get(k)<10){
                        min="0"+min;
                    }
                    time=hour+":"+min;
                    res.add(time);
                }
            }
        }
        return res;

    }
    /**
     * 获得0-n范围内二进制中1的个数为count的所有数字
     * @param n
     * @param count
     * @return
     */
    private List<Integer> binCount(int n,int count){
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            if (Integer.bitCount(i)==count){
                list.add(i);
            }
        }
        return list;
    }

    public List<String> readBinaryWatch1(int num){
        int[] time={1, 2, 4, 8, 1, 2, 4, 8, 16, 32};
        generateTime(num,time,0,0,0,0);
        return res;
    }

    private void generateTime(int num,int[] time,int index,int start,int hour,int min){
        if (index==num){
            String hs = String.valueOf(hour);
            String ms = min <= 9 ? "0" + min : String.valueOf(min);
            String t=hs+":"+ms;
            res.add(t);
            return;
        }
        for (int i = start; i <time.length ; i++) {
            int newHour = i<4?hour+time[i]:hour;//前四位表示小时
            int newMin = i<4?min:min+time[i];//后六位表示分钟
            if (newHour<=11&&newMin<=59){
                generateTime(num,time,index+1,i+1,newHour,newMin);
            }
            
        }
    }
    @Test
    public void test(){
        List<String> strings = readBinaryWatch1(1);
        System.out.println(strings);
    }
}
