package kmp;

import javax.print.DocFlavor;
import java.util.LinkedHashMap;

/**
 * @Author 李振华
 * @Date 2020/7/27 10:53
 * 串查找问题：给定原始串S和模式串P，查找从字符串S中第一次出现P的位置。
 */
public class Kmp {


    private static int[] getNextArray(char[] chars){
        int[] next = new int[chars.length];
        next[0] = -1;
        next[1] = 0;
        int k;
        for (int j = 2; j < chars.length; j++) {
            k=next[j-1];
            while (k!=-1) {
                if (chars[j - 1] == chars[k]) {
                    next[j] = k + 1;
                    break;
                }
                else {
                    k = next[k];
                }
                next[j] = 0;  //当k==-1而跳出循环时，next[j] = 0，否则next[j]会在break之前被赋值
            }
        }
        return next;

    }


    public static int kmpMatch(String s, String t){
        char[] s_arr = s.toCharArray();
        char[] t_arr = t.toCharArray();
        int[] next = getNextArray(t_arr);
        int i = 0, j = 0;
        while (i<s_arr.length && j<t_arr.length){
            if(j == -1 || s_arr[i]==t_arr[j]){
                i++;
                j++;
            }
            else
                j = next[j];
        }
        if(j == t_arr.length)
            return i-j;
        else
            return -1;
    }


    public static void main(String[] args) {

        String s="BBC ABCDAB ABCDABCDABDE";
        String p = "ABCDABD";

        System.out.println(kmpMatch(s,p));
        searchFirstStr(s,p);

    }



    public static void searchFirstStr(String s, String p){

        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        int i=0;
        int j=0;

        while (i<sChars.length&&j<pChars.length){
            if (sChars[i]==pChars[j]){
                i++;
                j++;
                continue;
            }
            else{
                i=i-j+1;
                j=0;
            }
        }
        if (j==pChars.length){
            System.out.println(i-j);
        }else{
            System.out.println(-1);
        }

    }



}
