package StringPermutation;

/**
 * @Author 李振华
 * @Date 2020/7/27 18:19
 */
public class stringCombination {


    public static void combination(String str){

        int len = str.length();
        int num = 1 << len;
        char[] chars = str.toCharArray();
        for (int i = 1; i <num ; i++) {
            for (int j = 0; j < len; j++) {
                if ((i&(1<<j))!=0){
                    System.out.print(chars[j]);
                }
            }
            System.out.println(" ");
            }
        }


    public static void main(String[] args) {
        String str="abc";
        combination(str);
    }


    }







