package clone;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author:LZH
 * @Date:2020/9/10 10:25
 * @Description
 */
public class Clone implements Cloneable {

    int anInt = 1;
    List<cloneB> list ;

    @Override
    public Clone clone ()throws CloneNotSupportedException {
        return (Clone) super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Clone c= new Clone();
        ArrayList<cloneB> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            cloneB x = new cloneB();
            x.b = i;
            list.add(x);
        }

        c.anInt = 100;
        c.list = list;
        Clone r = c.clone();
        for (int i = 0; i < r.list.size() ; i++) {
            System.out.println(r.anInt);
            System.out.println(c.anInt);
        }

    }

}
