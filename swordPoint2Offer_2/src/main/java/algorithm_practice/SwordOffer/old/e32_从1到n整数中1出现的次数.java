package algorithm_practice.SwordOffer.old;

/**
 * 题目：输入一个整数 n 求从 1 到 n 这 n 个整数的十进制表示中 1 出现的次数。
 * 例如输入 12 ，从 1 到 12 这些整数中包含 1 的数字有 1、10、11 和 12，1 一共出现了 5 次。
 *
 * 【解】：从数字的规律着手
 *  假设N = 54687
 *  1. 我们可以把N分成两部分：0-4687；4688-54687
 *      0-4687：继续分成两部分...（递归）
 *  2. 对于4688-54687：
 *     我们同样可以分成两部分来求：
 *      - 10000-19999  【只求最高位上的1的数目共(10^4)个】
 *      - 4688-9999，10000-19999(不看最高位上的这个1了)，20000-54687 ==> 5 个 0-9999
 *                     【1个次数：5* 4*10^3】
 *
 *
 *
 * Created by nibnait on 2016/10/1.
 */
public class e32_从1到n整数中1出现的次数 {


//    public static void main(String[] args) {
//        System.out.println(numberOf1Between1AndN(1)); // 1
//        System.out.println(numberOf1Between1AndN(5)); // 1
//        System.out.println(numberOf1Between1AndN(10)); // 2
//        System.out.println(numberOf1Between1AndN(55)); // 16
//        System.out.println(numberOf1Between1AndN(99)); // 20
//        System.out.println(numberOf1Between1AndN(10000)); // 4001
//        System.out.println(numberOf1Between1AndN(21345)); // 18821
 //       System.out.println(numberOf1Between1AndN(12)); //32439
//        System.out.println(numberOf1Between1AndN(0)); // 0
//        System.out.println(numberOf1Between1AndN(-1));
//    }

    private static int numberOf1Between1AndN(int n) {
        if (n<=0){
            return 0;
        }
        String strN = n + "";
        char[] charsN = strN.toCharArray();
        int[] numbers = new int[charsN.length];
        for (int i = 0; i < charsN.length; i++) {
            numbers[i] = charsN[i] - '0';
        }
        return numberOf1(numbers, 0);
    }

    /**
     *  1-N中，各个数位1出现的次数
     * @param numbers   N
     * @param begin    开始处理的位置
     * @return
     */
    private static int numberOf1(int[] numbers, int begin) {

        if (numbers==null || numbers.length<=0 || begin<0 || begin>=numbers.length){
            return 0;
        }
        int first = numbers[begin]; //当前要处理的最高位上的数字
        int length = numbers.length-begin;  //要处理的数字的位数
        if (length==1 && first==0){ //N为0时
            return 0;
        }
        if (length==1 && first>0){  //N为1位数，且N>0
            return 1;
        }

        int numFirstDigit = 0;  //二-1部分的1的个数
        if (first > 1){     //如果当前处理的数字N的第一位数 > 1（54687）
            numFirstDigit = PowerBase10(length-1);  //1的个数 10^4
        } else if (first == 1){     //如果当前处理的数字N的第一位数 == 1 （14587）
            numFirstDigit = atoi(numbers, begin+1) + 1;   //则1的数目： 4587+1
        }
        int numOtherDigits = first* (length-1)*PowerBase10(length-2);   //二-2部分的1的个数
        int numRecursive = numberOf1(numbers, begin+1); //递归求下一部分

        return numFirstDigit + numOtherDigits + numRecursive;
    }

    /**
     * 将数字数组转化为一个数字
     *
     * @param numbers
     * @param begin
     * @return
     */
    private static int atoi(int[] numbers, int begin) {
        int result = 0;
        for (int i = begin; i < numbers.length; i++) {
            result = result*10 + numbers[i];
        }
        return result;
    }

    private static int PowerBase10(int n) {
        int result = 1;
        for (int i = 0; i < n; i++) {
            result *= 10;
        }
        return result;
    }



    public int countDigitOne(int n) {
        int digit = 1, res = 0;
        int high = n / 10, cur = n % 10, low = 0;
        while(high != 0 || cur != 0) {
            if(cur == 0) {
                res += high * digit;
            } else if(cur == 1) {
                res += high * digit + low + 1;
            } else {
                res += (high + 1) * digit;
            }
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }


    public String PrintMinNumber(int [] numbers) {
        int len = numbers.length;
        if(len < 1) {
            return "";
        }
        for(int i = 0; i < len; i++){
            for(int j = 0; j < len-i-1; j++){
                String s1 = String.valueOf(numbers[j]) + numbers[j + 1];
                String s2 = String.valueOf(numbers[j+1]) + numbers[j];
                if(s1.compareTo(s2) > 0){
                    int temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                }
            }
        }
        String string = String.valueOf(numbers[0]);
        for(int i = 1; i < len; i++) {
            string += String.valueOf(numbers[i]);
        }
        return string;
    }


    public static void main(String[] args) {

        System.out.println(GetUglyNumber_Solution(14));
    }

    public static int GetUglyNumber_Solution(int index) {
        if(index <= 0) return 0;
        int p2 = 0, p3 = 0, p5 = 0; //初始化三个指向三个潜在成为最小丑数的位置
        int[] result = new int[index];
        result[0] = 1;
        for(int i = 1; i < index; i++){
            result[i] = Math.min(result[p2]*2, Math.min(result[p3]*3, result[p5]*5));
            if(result[i] == result[p2]*2) p2++;//为了防止重复需要三个if都能够走到
            if(result[i] == result[p3]*3) p3++;//为了防止重复需要三个if都能够走到
            if(result[i] == result[p5]*5) p5++;//为了防止重复需要三个if都能够走到
        }
        return result[index-1];
    }
}
