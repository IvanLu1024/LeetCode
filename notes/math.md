* [7.整数反转](#7)
* [12.整数转罗马数字](#12)
* [13.罗马数字转整数](#13)
* [29.两数相除](#29)
* [50.Pow(x,y)](#50)
* [60.第k个排列](#60)
* [231.2的幂](#231)
* [371.两整数之和](#371)
* [754.到达终点数字](#754)
## 7

整数反转

### 描述

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

### 分析

需要注意的是，**整型溢出的处理**

### 实现

解法1：

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
```
解法2：

```java
//使用局部变量的解法
    public int reverse(int x){
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

## 12

整数转罗马数字

### 描述

罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。

```
字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
```

例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。

示例 1:

输入: 3
输出: "III"
示例 2:

输入: 4
输出: "IV"
示例 3:

输入: 9
输出: "IX"
示例 4:

输入: 58
输出: "LVIII"
解释: L = 50, V = 5, III = 3.
示例 5:

输入: 1994
输出: "MCMXCIV"
解释: M = 1000, CM = 900, XC = 90, IV = 4.

### 分析

10以内的罗马数字都由V(5)、IV(4)、I(1)组成，所以同理拓展到10-100和100-1000，可构造出1-3999之间的整数。

### 实现

```java
public String intToRoman(int num) {
        //设置两个数组 使得数字和符号一一对应
        String[] dict={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int[] nums={1000,900,500,400,100,90,50,40,10,9,5,4,1};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<nums.length; i++) {
            //从大到小搜索可以匹配的数字，每次使用尽量较大的数字
            while (num>=nums[i]){
                num-=nums[i];
                sb.append(dict[i]);
            }
        }
        //从左向右添加符号
        return sb.toString();
    }
```

## 13

罗马数字转整数

### 描述

罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。

```
字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
```

例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。

示例 1:

输入: "III"
输出: 3
示例 2:

输入: "IV"
输出: 4
示例 3:

输入: "IX"
输出: 9
示例 4:

输入: "LVIII"
输出: 58
解释: L = 50, V= 5, III = 3.
示例 5:

输入: "MCMXCIV"
输出: 1994
解释: M = 1000, CM = 900, XC = 90, IV = 4.

### 实现

```java
public int romanToInt(String s) {
        if (s==null||s.length()==0){
            return 0;
        }
        char[] dict={'M','D','C','L','X','V','I'};
        int[] nums={1000,500,100,50,10,5,1};
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < dict.length; i++) {
            map.put(dict[i],nums[i]);
        }
        int res=0;
        //从左向右遍历，当前的符号的值不小于右边符号的值的时候加上当前值
        //反之，小于的时候，则减去当前值
        for (int i = 0; i < s.length()-1; i++) {
            char cur = s.charAt(i);
            char right = s.charAt(i + 1);
            if (map.get(cur)>=map.get(right)){
                res+=map.get(cur);
            }else {
                res-=map.get(cur);
            }
        }
        res+=map.get(s.charAt(s.length()-1));
        return res;
    }
```

## 29*

### 描述

给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。

返回被除数 dividend 除以除数 divisor 得到的商。

示例 1:

输入: dividend = 10, divisor = 3
输出: 3
示例 2:

输入: dividend = 7, divisor = -3
输出: -2
说明:

被除数和除数均为 32 位有符号整数。
除数不为 0。
假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，**如果除法结果溢出**，则返回 2^31 − 1。

### 实现

```java
//时间复杂度：O(log(N))
    public int divide(int dividend, int divisor) {
        if (dividend==0){
            return 0;
        }
        long ldiv = dividend;
        long ldsor = divisor;
        boolean flag=false;
        if ((ldiv<0&&ldsor>0)||(dividend>0&&ldsor<0)){
            flag=true;      //-1
        }
        ldiv=Math.abs(ldiv);
        ldsor=Math.abs(ldsor);
        long res = div(ldiv, ldsor);
        if (flag){
            res=-res;
        }
        if (res>Integer.MAX_VALUE||res<Integer.MIN_VALUE){
            return Integer.MAX_VALUE;
        }
        return (int) res;
    }

    //除法的辅助函数
    //利用加法去求解 除法
    private long div(long dividend,long divisor){
        //递归终止条件
        if (dividend<divisor){
            return 0;
        }
        long sum = divisor;
        //倍数
        long count = 1;
        //二分搜索
        while (sum+sum<=dividend){
            sum+=sum;
            count+=count;
        }
        return count+div(dividend-sum,divisor);
    }
```

## 60

### 描述

给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。

按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：

"123"
"132"
"213"
"231"
"312"
"321"
给定 n 和 k，返回第 k 个排列。

说明：

给定 n 的范围是 [1, 9]。
给定 k 的范围是[1,  n!]。

示例 1:

输入: n = 3, k = 3
输出: "213"

示例 2:

输入: n = 4, k = 9
输出: "2314"

### 分析

方法一：

可以使用回溯法，如同求全排列一样，但是效率很低。

方法二：

通过数学推导法，利用数学推导, 对于n=4, k=15 找到k=15排列的过程:

```
         1 + 对2,3,4的全排列 (3!个)
         2 + 对1,3,4的全排列 (3!个)         3, 1 + 对2,4的全排列(2!个)
         3 + 对1,2,4的全排列 (3!个)-------> 3, 2 + 对1,4的全排列(2!个)-------> 3, 2, 1 + 对4的全排列(1!个)-------> 3214
         4 + 对1,2,3的全排列 (3!个)         3, 4 + 对1,2的全排列(2!个)         3, 2, 4 + 对1的全排列(1!个)
    
         确定第一位:
         k = 14(从0开始计数)
         index = k / (n-1)! = 2, 是候选数字（1,2,3,4）中的第3个，说明第15个数的第一位是3
         更新k
         k = k - index*(n-1)! = 2
         确定第二位:
         k = 2
         index = k / (n-2)! = 1, 是候选数字（1,2,4）中的第2个，说明第15个数的第二位是2
         更新k
         k = k - index*(n-2)! = 0
         确定第三位:
         k = 0
         index = k / (n-3)! = 0, 是候选数字（1,4）中的第1个，说明第15个数的第三位是1
         更新k
         k = k - index*(n-3)! = 0
         确定第四位:
         k = 0
         index = k / (n-4)! = 0, 是候选数字（4）中的第1个，说明第15个数的第四位是4
         最终确定n=4时第15个数为3214
```

### 实现

回溯法+剪枝：

```java
	private boolean[] used;
    private int[] memo;
    //回溯法+剪枝
    public String getPermutation(int n, int k) {
        memo=new int[n+1];
        used=new boolean[n+1];
        factorial(n,memo);
        int[] nums=new int[n];
        for (int i = 0; i < n; i++) {
            nums[i]=i+1;
        }
        return generate(nums,0,n,k,"");
    }
    private String generate(int[] nums,int index,int n,int k,String s){
        if (index==n){
            return s;
        }
        //获取当前节点中叶子结点的个数
        int ps=memo[n-index-1];
        for (int i = 0; i < n; i++) {
            if (used[i]) continue;
            //若当前叶子总数小于k直接跳过
            if (ps<k){
                k-=ps;
                continue;
            }
            s=s+nums[i];
            used[i]=true;
            return generate(nums,index+1,n,k,s);
        }
        return "";

    }

    private void factorial(int n,int[] memo){
        int f=1;
        memo[0]=1;
        for (int i = 1; i <=n; i++) {
            f*=i;
            memo[i]=f;
        }
    }
```

数学推导法：

```java
public String getPermutation(int n, int k){
        //用来记录阶乘
        int[] memo=new int[n+1];
        //候选数字集合
        List<Integer> cands=new ArrayList<>();
        k-=1;
        StringBuilder sb = new StringBuilder();
        factorial(n,memo,cands);
        //
        for (int i = n-1; i >=0; i--) {
            //待加入的候选数字
            int index=k/memo[i];
            sb.append(cands.remove(index));
            k-=index*memo[i];
        }
        return sb.toString();
    }
    //预处理，先计算出阶乘
    private void factorial(int n,int[] memo,List<Integer> cands){
        int f=1;
        memo[0]=1;
        for (int i = 1; i <=n ; i++) {
            cands.add(i);
            f*=i;
            memo[i]=f;
        }
    }
```

## 50

Pow(x,y)

### 描述

实现 pow(x, n) ，即计算 x 的 n 次幂函数。

示例 1:

输入: 2.00000, 10
输出: 1024.00000
示例 2:

输入: 2.10000, 3
输出: 9.26100
示例 3:

输入: 2.00000, -2
输出: 0.25000
解释: 2-2 = 1/22 = 1/4 = 0.25
说明:

-100.0 < x < 100.0
n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。

### 分析

使用二分查找

### 实现

```java
//时间复杂度：O（logN）
    public double myPow(double x, int n) {
        if (n==0){
            return 1.0;
        }
        if(n==1)
            return x;
        if(n==-1)
            return 1/x;
        //先折半
        double half = myPow(x,n/2);
        //若n为偶数则将折半值直接相乘即可
        if (n%2==0) return half*half;
        //n%2==1:若n为奇数的情况
        //要分成n为正数和负数的情况来讨论
        if (n<0) return half*half/x;
        return half*half*x;
    }
```

## 69

X的平方根

### 描述

实现 int sqrt(int x) 函数。

计算并返回 x 的平方根，其中 x 是非负整数。

由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

示例 1:

输入: 4
输出: 2

示例 2:

输入: 8
输出: 2

说明: 8 的平方根是 2.82842..., 
     由于返回类型是整数，小数部分将被舍去。

### 实现

```java
//使用二分查找的方法
    public int mySqrt(int x) {
        int l=0,h=x;
        while (l<=h){
            int mid=l+(h-l)/2;
            //为了避免整型的溢出，
            if (mid<x/mid){
                l=mid+1;
            }else if (mid==x/mid){
                return mid;
            }else {
                h=mid-1;
            }
        }
        return h;
    }
```

## 754**

### 描述

在一根无限长的数轴上，你站在0的位置。终点在target的位置。

每次你可以选择向左或向右移动。第 n 次移动（从 1 开始），可以走 n 步。

返回到达终点需要的最小移动次数。

示例 1:

输入: target = 3
输出: 2
解释:
第一次移动，从 0 到 1 。
第二次移动，从 1 到 3 。
示例 2:

输入: target = 2
输出: 3
解释:
第一次移动，从 0 到 1 。
第二次移动，从 1 到 -1 。
第三次移动，从 -1 到 2 。
注意:

target是在[-10^9, 10^9]范围中的非零整数。

### 分析

分析 首先考虑一种比较极端的情况 即一直向正方向移动n步 ，刚好达到target，那么target的值就等于前n步的和 ，也就是1+2+.....+n = n*(n+1)/2

如果n(n+1)/2>target ,那么所需要的步数肯定要比n多，而且肯定有向左走的步子，也就是求和的时候肯定是有负数的，至于哪个或者哪些个为负，下面开始讨论：

- n(n+1)/2 - target 为偶数时，所以要想到达 target 需要向左走 n(n+1)/2 - target 偶数步 ，就是把前n项中第( n(n+1)/2 - target)/2 步变为负号就行了，总步数不用增加
- 当n(n+1)/2 - target 为奇数时，就要分类讨论：
  - 若n为奇数，那n+1就是偶数 无论向左还是向右 都不会产生一个奇数的差来因此需要再走一步，故要n+2步；
  - 若n为偶数，n+1则为奇数，可以产生一个奇数的差，故要n+1步

### 实现

```java
public int reachNumber(int target) {
        int t=Math.abs(target);
        if(t==0){
            return 0;
        }
        int i=0;
        while(i*(i+1)<2*t){
            i++;
        }
        int sum=i*(i+1)/2;
        if(sum==t){
            return i;
        }
        //若差值为偶数，则令1,2,...,i之间是一个为负数即可
        if((sum-t)%2==0){
            return i;
        }else{
            //若差值为奇数，需要向左移动奇数位
            //若i为偶数，那么i+1为奇数，可以产生一个奇数差；
            if(i%2==0){
                return i+1;
            }else{
                //若i为奇数，那么i+1为偶数，i+2为奇数，产生奇数差，必须需要i+2步
                return i+2;
            }
        }
    }
```





# 位运算

位运算即是在位级别进行操作的技术，合适的位运算能够帮助我们得到更快地运算速度与更小的内存使用。下面列举一些常见使用方法：

- 测试第 k 位: `s & (1 << k)`
- 设置第 k 位: `s |= (1 << k)`
- 第 k 位置零: `s &= ~(1 << k)`
- 切换第 k 位值: `s ^= ~(1 << k)`
- 乘以 2n: `s << n`
- 除以 2n: `s >> n`
- 交集: `s & t`
- 并集: `s | t`
- 减法: `s & ~t`
- 交换 `x = x ^ y ^ (y = x)`
- 取出最小非 0 位（Extract lowest set bit）: `s & (-s)`
- 取出最小 0 位（Extract lowest unset bit）: `~s & (s + 1)`
- 交换值: `x ^= y; y ^= x; x ^= y;`

## 231

2的幂

### 描述

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

### 分析

使用位运算，因为2的次幂的二进制表示中，最高位为1，其余位均为0；例如：n=4（100），n-1=3(011),
将n和n-1做与运算结果为0，通过这个性质来判断一个数是否为2的次幂，即可。

### 实现

```java
	//位运算
    public boolean isPowerOfTwo(int n){
        if (n<=0){
            return false;
        }
        //例如：1000 & 0111 = 0
        return (n&(n-1))==0;
    }
```

## 371

两整数之和

### 描述

不使用运算符 + 和 - ，计算两整数 a 、b 之和。

示例 1:

输入: a = 1, b = 2
输出: 3
示例 2:

输入: a = -2, b = 3
输出: 1

### 分析

使用二进制的加法

### 实现

```java
	//两个整数a, b; a ^ b是无进位的相加； a&b得到每一位的进位；
    // 让无进位相加的结果与进位不断的异或， 直到进位为0；
    public int getSum(int a, int b) {
        int sum,carry;
        do{
            sum=a^b;
            carry=(a&b)<<1;
            a=sum;
            b=carry;
        }while(carry!=0);
        return sum;
    }
```



# 只出现一次的数字

相关题目：

- [136.只出现一次的数字](#136)
- [137.只出现一次的数字（2）](#137)
- [260.只出现一次的数字（3）](#260)

## 136

### 描述

给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:

输入: [2,2,1]
输出: 1

示例 2:

输入: [4,1,2,1,2]
输出: 4

### 实现

```java
//利用异或运算的性质
    // ^:为异或运算（性质：对于任何数x，都有x^x=0，x^0=x）
    public int singleNumber(int[] nums){
        int res=0;
        for (int i : nums) {
            res^=i;
        }
        return res;
    }
```

## 137

### 描述

给定一个非空整数数组，**除了某个元素只出现一次以外，其余每个元素均出现了三次**。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:

输入: [2,2,3,2]
输出: 3
示例 2:

输入: [0,1,0,1,0,1,99]
输出: 99

### 分析

这个题其实就是求，在其他数都出现k次的数组中有一个数只出现一次，求出这个数。

而上面那个k次的是有通用解法的。

使用一个32维的数组，用这个32维的数组存储所有数里面第0位1的总数，第1位1的总数。。。第31位1的总数。

**假如第0位1的个数是k的倍数，那么要求的这个数在该位一定是0，若不是k的倍数，那么要求的这个数在该位一定是1**，第1位的1一直到第31位的1的个数同理。

为什么呢？因为假如说数组中的某些数在该位置是1，那么因为这个数要么出现k次，那么出现1次。

因此，该位置一定可以表示成km或者km+1，m代表该位是1的数的种类。

当表示成km的时候代表该位为1的数都是出现k次的，而当表示为km+1的时候代表该位为1的数还有只出现一次的。

我甚至觉得这个和“n瓶药有1瓶有毒，求最少的老鼠数来试毒”是一个原理。

> 来源：https://leetcode-cn.com/problems/two-sum/solution/tong-yong-jie-fa-gai-wen-ti-shi-shu-zu-zhong-mou-s/

### 实现

```java
public int singleNumber(int[] nums){
        int res=0;
        for(int i=0;i<32;i++){
            int bit=1<<i;
            int bitCount=0;
            for(int num:nums){
                if((num&bit)!=0){
                    bitCount++;
                }
            }
            if(bitCount%3!=0){
                res|=bit;
            }
        }
        return res;
    }
```

## 260

### 描述

给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的**那两个元素**。

示例 :

输入: [1,2,1,3,2,5]
输出: [3,5]

注意：

结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？

### 分析

第一步： 把所有的元素进行异或操作，最终得到一个异或值。因为是不同的两个数字，所以这个值必定不为0；

```
   int xor = 0;
    for (int num : nums) {
        xor ^= num;
    } 
```

第二步： mask使用取异或值最后一个二进制位为1的数字，如果是1则表示两个数字在这一位上不同。

```java
		int mask=1;
        //取异或值最后一个二进制位为1的数字作为mask，如果是1则表示两个数字在这一位上不同
        while ((diff&1)==0){
            mask=mask<<1;
            diff=diff>>1;

        }
```

第三步： 通过与这个mask进行与操作，如果为0的分为一个数组，为1的分为另一个数组。这样就把问题降低成了：“有一个数组每个数字都出现两次，有一个数字只出现了一次，求出该数字”。对这两个子问题分别进行全异或就可以得到两个解。也就是最终的数组了。

```
    int[] ans = new int[2];
    for (int num : nums) {
        if ( (num & mask) == 0) {
            ans[0] ^= num;
        } else {
            ans[1] ^= num;
        }
    }
```

复杂度分析： 时间复杂度O(N),空间复杂度O(1)

> 来源于：https://leetcode-cn.com/problems/two-sum/solution/cai-yong-fen-zhi-de-si-xiang-jiang-wen-ti-jiang-w

### 实现

```java
public int[] singleNumber(int[] nums){
        int diff=0;
        for (int i:nums){
            diff^=i;
        }

        int mask=1;
        //取异或值最后一个二进制位为1的数字作为mask，如果是1则表示两个数字在这一位上不同
        while ((diff&1)==0){
            mask=mask<<1;
            diff=diff>>1;

        }
        int[] res=new int[2];
        //利用mask将原数组分成两个只有一个数字是出现一次其余都是出现两次的数组
        for(int i:nums){
            if ((i&mask)==0){
                res[0]^=i;
            }else {
                res[1]^=i;
            }
        }
        return res;
    }
```

> 补充资料：https://mp.weixin.qq.com/s/Eh4G86Jk3RMIAQ6YS3QmBw

