package swordPoint2Offer;

/**
 * @Author 李振华
 * @Date 2020/8/2 12:12
 */
public class Solution {

    public static int movingCount(int threshold, int rows, int cols)
    {
        if (threshold < 0 || rows < 1 || cols < 1) {
            return 0;
        }
        int[] arr = new int[rows*cols];
        return doMovingCount(0, 0, rows, cols,threshold,arr);
    }

    public static int doMovingCount(int i,int j,int rows,int cols,int threshold,int[] arr){
        int result = 0;
        if(check(i,j,rows,cols,threshold,arr)) {
            arr[i*cols+j] = 1;
            result = 1 +
                    + doMovingCount(i-1, j, rows, cols,threshold,arr)
                    + doMovingCount(i, j-1, rows, cols,threshold,arr)
                    + doMovingCount(i+1, j, rows, cols,threshold,arr)
                    + doMovingCount(i, j+1, rows, cols,threshold,arr);
        }
        return result;
    }



    public static boolean check(int i,int j,int rows,int cols,int threshold,int[] arr){

        return i>=0 && j>=0 && i < rows && j < cols && arr[i*cols+j] == 0 && getSum(i)+getSum(j)<=threshold;
    }


    public static int getSum(int i){
        int sum = 0;
        while(i>0){
                sum += (i%10);
                i= i/10;
            }
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(movingCount(4,4,4));
    }
}