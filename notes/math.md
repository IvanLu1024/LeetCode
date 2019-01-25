# 数学部分的笔记总结
## 相关题目：
* [7.整数反转](#7)
* [231.2的幂](#231)
### 7
给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

 示例 1:

 输入: 123
 输出: 321
 
 示例 2:

 输入: -123
 输出: -321
 
 示例 3:

 输入: 120
 输出: 21
 注意:

 假设我们的环境只能存储得下 32 位的有符号整数，

 则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 
- 分析：

需要注意的是，**整型溢出的处理**

- 实现：
```java
public int reverse(int x) {
        long reX = 0;
        long num = x;
        while (num!=0){
            reX = reX*10+num%10;
            num/=10;
        }
        if (reX>Integer.MAX_VALUE||reX<Integer.MIN_VALUE){
            return 0;
        }
        return (int) reX;
    }

    //转为为绝对值的解法
    public int reverse1(int x){
        if (x==Integer.MIN_VALUE){
            return 0;
        }
        int reverseNum = 0;
        //num：为x的绝对值
        int num = Math.abs(x);
        int sign = 1;
        if (x<0){
            sign=-1;
        }
        while (num!=0) {
            //当翻转数溢出整型范围的时候，直接返回0；Integer.MAX_VALUE：2147483647
            if (reverseNum > Integer.MAX_VALUE / 10 || (reverseNum == Integer.MAX_VALUE / 10 && x % 10 > 7))
                return 0;
            reverseNum=reverseNum*10+num%10;
            num/=10;
        }
        return sign*reverseNum;
    }
    //使用局部变量的解法
    public int reverse2(int x){
        int num = x;
        int reverseNum = 0;
        while (num!=0){
            //确保newReverseNum不会整型溢出
            if (reverseNum>Integer.MAX_VALUE/10||reverseNum<Integer.MIN_VALUE/10){
                return 0;
            }
            int newReverseNum = 10*reverseNum+num%10;
            reverseNum = newReverseNum;
            num/=10;
        }
        return reverseNum;
    }

```
### 231
给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

示例 1:

输入: 1
输出: true
解释: 20 = 1

示例 2:

输入: 16
输出: true
解释: 24 = 16

示例 3:

输入: 218
输出: false

- 分析：

解法1：

使用位运算，因为2的次幂的二进制表示中，最高位为1，其余位均为0；例如：n=4（100），n-1=3(011),
将n和n-1做与运算结果为0，通过这个性质来判断一个数是否为2的次幂，即可。

解法2：

将该数字转化为二进制表示，因为2的次幂的二进制表示中，最高位为1，其余位均为0，利用这个性质，
如果二进制表示中只有一个1的话，那么则说明这个数为2的次幂。

- 实现：
```java
//位运算
    public boolean isPowerOfTwo1(int n){
        if (n<=0){
            return false;
        }
        //例如：1000 & 0111 = 0
        return (n&(n-1))==0;
    }

    //2的n次幂满足二进制表示中只有一位为1，其余位置均为0
    public boolean isPowerOfTwo2(int n){
        if (n<=0){
            return false;
        }
        String binStr = Integer.toBinaryString(n);
        int count=0;
        for (int i = 0; i < binStr.length(); i++) {
            if (binStr.charAt(i)=='1'){
                count++;
            }
            if (count>1){
                break;
            }
        }
        return count==1;
    }
```