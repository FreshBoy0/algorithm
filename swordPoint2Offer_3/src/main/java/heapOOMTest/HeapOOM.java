package heapOOMTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 李振华
 * @Date 2020/7/29 19:07
 */
public class HeapOOM {

        static class OOMObject {
        }

        public static void main(String[] args) {
            List<OOMObject> list = new ArrayList<OOMObject>();
            while (true) {
                list.add(new OOMObject());
            }

    }


}
