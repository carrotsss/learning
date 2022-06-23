package datastructure.hash.hashmap;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapSource {
    private static HashMap<String, String> hashMap = new HashMap<>();
    private static ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();

    public static void test() {
        concurrentHashMap.put("李四key", "李四value");
        concurrentHashMap.get("李四key");
        hashMap.put("张三key", "张三value");
        hashMap.get("张三key");
        hashMap.put("张三key", "开心");
        hashMap.get("张三key");
    }

    public static void main(String[] args) {
        test();
    }
}
