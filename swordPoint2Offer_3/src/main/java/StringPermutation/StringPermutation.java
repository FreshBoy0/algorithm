package StringPermutation;

import java.util.*;

/**
 * @Author 李振华
 * @Date 2020/7/27 17:31
 */
public class StringPermutation {


    public static HashMap<Integer, String> result = new HashMap<>();

    public static void StringPermutation(String str) throws Exception {

        char[] chars = str.toCharArray();
        doStringPermutation(chars,0);



    }

    public static void doStringPermutation(char[] chars,int start) throws Exception {
        if (chars.length==start+1){
            System.out.println(chars);
            return;
        }
        for (int i = start; i <chars.length ; i++) {
            if (!canSwap(chars,start,i)) {
                continue;
            }
            swap(chars,start,i);
            doStringPermutation(chars,start+1);
            swap(chars,start,i);
        }

    }


    private static void swap(char[] chars,int left,int right) throws Exception {

        if (left>right||right>chars.length-1){
            throw new Exception("参数有误");
        }
        char tem = chars[left];
        chars[left] = chars[right];
        chars[right] = tem;
    }

    private static boolean canSwap(char[] chars,int left,int right){
        boolean flag = true;
        for (int i = left; i < right ; i++) {
            if (chars[i]==chars[right]){
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) throws Exception {

        String str="1223";
        StringPermutation(str);
        System.out.println("   ");

    }




}
