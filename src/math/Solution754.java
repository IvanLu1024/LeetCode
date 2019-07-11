package math;

/**
 * @Author Ivan 9:06
 * @Description TODO
 */
public class Solution754 {
    // 理解这题的意思 这题就好做了
    // 分析 首先考虑一种比较极端的情况 即一直向正方向移动n步 ，刚好达到target
    // 那么target的值就等于前n步的和 ，也就是1+2+.....+n = n*(n+1)/2
    // 如果n(n+1)/2>target ,那么所需要的步数肯定要比n多，而且肯定有向左走的步子，也就是求和的时候肯定是有负数的，至于哪个或者哪些个为负，下面开始讨论
    //1，n(n+1)/2 - target 为偶数时，所以要想到达 target 需要向左走 n(n+1)/2 - target 偶数步 ，
    // 就是把前n项中第( n(n+1)/2 - target)/2 步变为负号就行了
    //当n(n+1)/2 - target 为奇数时，就要分类讨论了，若n为奇数n+1就是偶数 无论向左还是向右 都不会产生一个奇数的差来因此需要再走一步故要n+2步
    //若n为偶数，n+1则为奇数，可以产生一个奇数的差，故要n+1步
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
}
