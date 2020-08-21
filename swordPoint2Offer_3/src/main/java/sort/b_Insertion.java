package sort;

import common.util.SwapUtil;
import common.util.SysOut;
import common.util.SysRandom;

/**
 * @Author:LZH
 * @Date:2020/8/21 18:24
 * @Description
 */
public class b_Insertion {

    public static void main(String[] args) {
        int[] a = new int[10];
        a = SysRandom.random(a);
        SysOut.printArray(a);

        a = Insertion_Sort(a);
        SysOut.printArray(a);
    }

    /**
     * 适用于元素基本（接近）有序的数组，
     */
    public static int[] Insertion_Sort(int[] a) {

        int length = a.length;

        for (int i = 1; i < length; i++) {
            int tem = a[i];
            int j = i;
            for ( ; j > 0 && a[j-1]>tem; j--) {
                a[j] = a[j-1];
            }
            a[j] = tem;
        }
        return a;
    }


}
