package LRUCache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author 李振华
 * @Date 2020/7/28 8:48
 */
public class LRUCache extends LinkedHashMap {

    private final int MAX_CACHE_SIZE;

    public LRUCache(int cacheSize){
        super((int)Math.ceil(cacheSize/0.75),0.75f,true);
        MAX_CACHE_SIZE = cacheSize;
    }

    @Override
    public boolean removeEldestEntry(Map.Entry entry){
        return this.size()>MAX_CACHE_SIZE;
    }

}
