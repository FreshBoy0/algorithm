package redis.redisLock;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 李振华
 * @Date 2020/7/25 7:36
 */
public class RedisWithReentrantKock {

    private ThreadLocal<Map<String,Integer>>  lockers = new ThreadLocal<>();
    private Jedis jedis;

    public RedisWithReentrantKock(Jedis jedis){
        this.jedis = jedis;
    }
    private Boolean _lock(String key){
        //return jedis.set(key,"","nx","ex",5L)!=null;
        return null;
    }
    private void  _unlock(String key){
        jedis.del(key);
    }
    private Map<String,Integer> currentLockers(){
        Map<String,Integer>  refs = lockers.get();
        if (refs!=null){
            return refs;
        }
        lockers.set(new HashMap<>());
        return lockers.get();
    }

    public Boolean lock(String key){
        Map<String, Integer> refs = currentLockers();
        Integer refCnt = refs.get(key);
        if (refCnt!=null){
            refs.put(key,refCnt+1);
            return true;
        }
        boolean ok = this._lock(key);
        if (!ok){
            return false;
        }
        return true;
    }

    public boolean unlock(String key){
        Map<String ,Integer> refs = currentLockers();
        Integer refCnt = refs.get(key);
        if (refCnt==null){
            return false;
        }else{
            refCnt-=1;
            if (refCnt>0){
                refs.put(key,refCnt);
            }else{
                refs.remove(key);
                this._unlock(key);
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        RedisWithReentrantKock redis = new RedisWithReentrantKock(jedis);
        redis.lock("key1");
    }

}
