package HashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @Author 李振华
 * @Date 2020/7/27 15:50
 */
public class MyHashMap<K,V> {

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    static class Node<K,V>{
        final int hash;
        final K key;
        V value;
        MyHashMap.Node<K,V> next;

        Node(int hash, K key, V value, MyHashMap.Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    //数组容器
    MyHashMap.Node<K,V>[] table;
    //键值容器
    Set<Map.Entry<K,V>> entrySet;
    //kv对的个数
    int size;
    //扩容次数
    int modCount;
    //扩容的阈值：数组长度*加载因子
    int threshold;
    //加载因子
    float loadFactor = 0.75f;
    public MyHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                    loadFactor);
        this.loadFactor = loadFactor;
        this.threshold = initialCapacity;
    }


    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }
    MyHashMap.Node<K,V> newNode(int hash, K key, V value, MyHashMap.Node<K,V> next) {
        return new MyHashMap.Node<>(hash, key, value, next);
    }

    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        MyHashMap.Node<K,V>[] tab;
        MyHashMap.Node<K,V> p;
        int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            MyHashMap.Node<K,V> e; K k;
            if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        break;
                    }
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        return null;
    }

    final MyHashMap.Node<K,V>[] resize() {
        MyHashMap.Node<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap=0, newThr = 0;
        if (oldCap > 0) {
            newCap = oldCap << 1;
            newThr = oldThr << 1; // double threshold
        }
        else{
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        threshold = newThr;
        MyHashMap.Node<K,V>[] newTab = (MyHashMap.Node<K,V>[])new MyHashMap.Node[newCap];
        table = newTab;
        if (oldTab != null) {
            for (int j = 0; j < oldCap; ++j) {
                MyHashMap.Node<K,V> e;
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null)
                        newTab[e.hash & (newCap - 1)] = e;
                    else {
                        MyHashMap.Node<K,V> loHead = null, loTail = null;
                        MyHashMap.Node<K,V> hiHead = null, hiTail = null;
                        MyHashMap.Node<K,V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }


}
