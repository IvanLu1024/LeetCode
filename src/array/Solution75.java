package array;

import org.junit.Test;

/**
 *
 * 输入: [2,0,2,1,1,0]
   输出: [0,0,1,1,2,2]
 */
public class Solution75 {

    //时间复杂度：O(n)
    //空间复杂度：O(1)
    public void sortColors(int[] nums) {
        int[] colors=new int[3];
        for (int i=0;i<nums.length;i++){
            if (nums[i]==0)
                colors[0]++;
            else if (nums[i]==1)
                colors[1]++;
            else if (nums[i]==2)
                colors[2]++;

        }
        for (int i=0;i<nums.length;i++){
            if (i<colors[0])
                nums[i]=0;
            else if (i>=colors[0]&&i<colors[0]+colors[1])
                nums[i]=1;
            else nums[i]=2;
        }



    }

    //使用三向快排的思想来实现
    public void sortColors1(int[] nums) {
        int zero=-1,n=nums.length;            //nums[0,zero]=0,由于不能将初始值nums[0]设为0
        int two=n;                            //nums[two,n-1]=2,与上同理
        int i=0;
        while (i<two){
            if (nums[i]==0&&zero<n-1){
                zero++;
                swap(nums,i++,zero);//**num[zero+1]=0,i从下一个位置开始
            }
            else if (nums[i]==1){
                i++;
            }else {
                two--;
                swap(nums,two,i);//由于num[two-1]元素未知
            }

        }

    }

    private void swap(int[] a,int i,int j){
        int t=a[i];
        a[i]=a[j];
        a[j]=t;

    }
    @Test
    public void test(){
        int[] nums = {2,0,2,1,1,0};
        sortColors1(nums);
        for (int n:nums){
            System.out.print(n+",");
        }

    }
}
