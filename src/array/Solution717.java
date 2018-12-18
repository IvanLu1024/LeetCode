package array;

import org.junit.Test;

public class Solution717 {
    //使用贪心策略：如果遇到1就将其当做2比特，若最后一位也当做2比特的话，i=n
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        int i = 0;
        for (; i < n-1; ) {
            if (bits[i]==1){
                i+=2;
            }else {
                i++;
            }
        }
        return i==n-1;
    }

    //从倒数第二个位置开始看连续1的数量，如果为奇数个表明最后一位0要和倒数第二位组成2比特（10）
    //如果是偶数个，则前面的这些1可以组成若干个2比特，最后一位组成1比特
    public boolean isOneBitCharacter1(int[] bits){
        int n = bits.length;
        int count=0;
        for (int i = n-2; i >=0; i--) {
            if (bits[i]==1){
                count++;
            }else {
                break;
            }
        }
        return count%2==0;
    }
    @Test
    public void test(){
        int[] bits={1, 1, 1, 0};
        boolean res = isOneBitCharacter(bits);
        System.out.println(res);
    }
}
