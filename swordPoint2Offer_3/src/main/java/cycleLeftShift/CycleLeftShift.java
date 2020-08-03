package cycleLeftShift;

import java.util.HashMap;

/**
 * @Author 李振华
 * @Date 2020/7/27 14:11
 * 字符串循环左移K位
 *
 */
public class CycleLeftShift {

    /**
     * 字符串左移K位
     * @param str
     * @param k
     */
    public static void leftShiftK(String str,int k){
        if (k<0){
            System.out.println("移动位数不可以小于0");
        }
        if (k>str.length()){
            k=k%str.length();
        }
        char[] strChars = str.toCharArray();
        reverseChars(strChars,0,k-1);
        reverseChars(strChars,k,str.length()-1);
        reverseChars(strChars,0,str.length()-1);

        for (int i = 0; i < str.length(); i++) {
            System.out.println(strChars[i]);
        }


    }

    /**
     * 字符串反转
     * @param chars
     * @param left
     * @param right
     */
    private static void reverseChars(char[] chars,int left,int right){
        char tem;
        while (left<right){
            tem = chars[left];
            chars[left] = chars[right];
            chars[right] = tem;
            left++;
            right--;
        }


    }


    /**
     * 句子反转
     * @param str
     */
    public static void sentenceReverse(String str){
        StringBuilder sb = new StringBuilder();
        if (str.length()<=1||str==null){
            System.out.println(str);
        }else{

          String[] strings = str.split(" ");
            for (int i = strings.length-1; i >=0 ; i--) {
                String tem = strings[i];
                reverseChars(tem.toCharArray(),0,tem.length()-1);
                sb.append(tem).append(" ");
            }
        }

        System.out.println(sb.toString());

        }



    public static void main(String[] args) {
        String s="abcdefghijk";
        leftShiftK(s,3);

        sentenceReverse("I am a student");


    }




}
