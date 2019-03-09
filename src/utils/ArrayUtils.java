package utils;

import java.util.Random;

public class ArrayUtils {
    /**
     * 打印数组的工具类
     * @param nums
     */
    public static void printArray(Comparable[] nums){
        if (nums==null||nums.length==0){
            throw new IllegalArgumentException("array is null");
        }
        System.out.print("[");
        for (int i = 0; i < nums.length; i++) {
            if (i!=nums.length-1){
                System.out.print(nums[i]+",");
            }else {
                System.out.print(nums[i]+"]");
            }
        }
    }

    /**
     * 生成一个整型的随机数组
     * @param len
     * @param start 随机数范围起始位置
     * @param end   随机数范围终止位置
     * @return
     */
    public static Integer[] createRandomArray(int len,int start,int end){
        Integer[] arr=new Integer[len];
        for (int i = 0; i < len; i++) {
            Random random = new Random();
            int num = random.nextInt((end - start) + 1) + start;
            arr[i]=num;
        }
        return arr;
    }



    /**
     * 打印二维数组的工具类
     * @param martix
     */
    public static void print2DimenArray(Comparable[][] martix){
        int m = martix.length;
        if (m==0){
            throw new IllegalArgumentException("martix error");
        }
        int n=martix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j==n-1){
                    System.out.print(martix[i][j]);
                }else {
                    System.out.print(martix[i][j]+",");
                }
            }
            System.out.println();
        }
    }
}
