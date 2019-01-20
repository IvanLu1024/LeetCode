package backTrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。

 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。

 示例 1:

 输入: 2
 输出: [0,1,3,2]
 解释:
 00 - 0
 01 - 1
 11 - 3
 10 - 2

 对于给定的 n，其格雷编码序列并不唯一。
 例如，[0,2,3,1] 也是一个有效的格雷编码序列。

 00 - 0
 10 - 2
 11 - 3
 01 - 1
 示例 2:

 输入: 0
 输出: [0]
 解释: 我们定义格雷编码序列必须以 0 开头。
 给定编码总位数为 n 的格雷编码序列，其长度为 2^n。当 n = 0 时，长度为 2^0 = 1。
 因此，当 n = 0 时，其格雷编码序列为 [0]。


 */
public class Solution89 {
    //公式法：
    //整数 n 的格雷码是 n ⊕ ( n/2)
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        if (n==0){
            res.add(0);
            return res;
        }
        for (int i = 0; i < 1<<n; i++) {
            int grayCode = i^(i/2);
            res.add(grayCode);
        }
        return res;
    }

    //回溯法
    public List<Integer> grayCode1(int n){
        List<Integer> res = new ArrayList<>();
        if (n==0){
            res.add(0);
            return res;
        }
        if (n==1){
            res.add(0);
            res.add(1);
            return res;
        }
        List<Integer> grayM = grayCode1(n - 1);
        for (int i = 0; i < 1 << n; i++) {
            if (i<1<<(n-1)){//前面一半的数字不变
                res.add(grayM.get(i));
            }else {//后面一半的数字反向排列，再在前面添加一个1
                int code = grayM.get((1<<n)-i-1)+(1<<(n-1));
                res.add(code);
            }
        }
        return res;
    }

    @Test
    public void test(){
        int n = 2;
        List<Integer> list = grayCode1(n);
        System.out.println(list);

    }


}
