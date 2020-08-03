package linkedHahsMap;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author 李振华
 * @Date 2020/7/28 7:35
 */
public class MyLinkedHashMap {

    public static void main(String[] args) {
        HashMap<String,String> hm = new HashMap<>();
        hm.put("key1","value1");
        hm.put("key2","value2");
        hm.put("key3","value3");
        hm.put("key4","value1");
        hm.put("key5","value2");
        hm.put("key6","value3");
       Iterator<Map.Entry<String,String>> hmItera = hm.entrySet().iterator();
       Set<String> keySet = hm.keySet();
       hm.values();
       while (hmItera.hasNext()){
           System.out.println(hmItera.next());
       }
//       while (keySet.iterator().hasNext()){
//           System.out.println(keySet.iterator().next());
//       }

        ConcurrentHashMap concurrentHashMap;

        LinkedHashMap<String, String> linkedHashMap = new java.util.LinkedHashMap<>(16, 0.75f, true);
        linkedHashMap.put("key1","value1");
        linkedHashMap.put("key2","value2");
        linkedHashMap.put("key3","value3");
        linkedHashMap.put("key4","value4");
        linkedHashMap.put("key5","value5");
        linkedHashMap.put("key6","value6");
        linkedHashMap.get("key3");
        Set<Map.Entry<String, String>> set = linkedHashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            System.out.println("key:" + key + ",value:" + value);
        }


    }












}
