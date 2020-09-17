package classLibTest;

import common.util.SysOut;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author:LZH
 * @Date:2020/8/31 16:03
 * @Description
 */
@Getter
@Setter
public class Test1 {


    public static int t1(int a, int b){

        return t2(a,b);
    }
    public static int t2(int a, int b){
        return a*10+b;
    }


    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        t1(a,b);
    }

}
