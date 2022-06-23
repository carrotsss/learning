package datastructure.hash.hashmap.implementA;

/**
 * Created by carrots on 2021/12/26.
 */
public interface MyMapInterface<k,v>{
    // 大小
    int size();
    // 是否为空
    boolean isEmpty();
    // 根据key获取元素
    Object get(Object key);
    // 添加元素
    Object put(Object key, Object value);

    // 内部接口
    interface Entry<k,v>{
        k getKey();
        v getValue();
    }
}