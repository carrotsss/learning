package com.mmall.concurrency.tread;

import java.util.AbstractMap;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 采用了与HashMap一样的保存数据方式，只是自己手动实现了一个简易版。
 * 内部采用了一个队列来保存每次写入的数据。
 * 写入的时候判断缓存是否大于了阈值N，如果满足则根据队列的FIFO特性将队列头的数据删除。因为对猎头的数据肯定是最先放进去的
 * 再开启了一个守护线程用于判断最先放进去的数据是否超预期（因为就算超预期也是最先放进去的数据最有可能满足超期条件。）
 * 设置为守护线程可以更好地表明其目的（最坏的情况下，如果是一个用户县城最终有可能导致程序不正常退出，因为该线程一直在运行，守护线程则不会有这个情况。）
 */
public class LRUCache extends AbstractMap {
    /**
     * 检查是否超期线程
     */
    private ExecutorService checkTimePool;
    /**
     * map 最大size
     */
    private final static int MAX_SIZE = 1024;
    /**
     * 阻塞队列
     */
    private final static BlockingQueue QUEUE = new ArrayBlockingQueue(MAX_SIZE);
    /**
     * 默认大小
     */
    private final static int DEFAULT_ARRAY_SIZE = 1024;
    /**
     * 数组
     */
    private Object[] arrays;
    /**
     * 数组长度
     */
    private int arraySize;
    /**
     * 判断是否停止flag
     */
    private volatile boolean flag = true;
    /**
     * 整个map的大小
     */
    private volatile AtomicInteger size;
    /**
     * 超时时间
     */
    private final static Long EXPORE_TIME = 60 * 60 * 1000L;

    public LRUCache(){
        arraySize = DEFAULT_ARRAY_SIZE;
        arrays = new Object[arraySize];
        //开启一个线程检查最先放入队列的值是否超期
        executeCheckTime();
    }

    /**
     * 开启一个线程检查最先放入队列的值是否超期，设置为守护线程
     */
    private void executeCheckTime(){
        checkTimePool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, QUEUE);
        checkTimePool.execute(new CheckTimeThread());
    }

    private class CheckTimeThread implements Runnable{
        @Override
        public void run() {
            while(flag){
                try{
                    Node node = (Node) QUEUE.poll();
                    if(node == null){
                        continue;
                    }
                    Long updateTime = node.getUpdateTime();
                    if((updateTime - System.currentTimeMillis()) >= EXPORE_TIME){
                        remove(node.key);
                    }
                }catch (Exception e){

                }
            }
        }
    }

    private class Node{
        private Node next;
        private Node pre;
        private Object key;
        private Object val;
        private Long updateTime;

        public Node(Node pre, Node next, Object key ,Object val){
            this.pre = pre;
            this.next = next;
            this.key = key;
            this.val = val;
            this.updateTime = System.currentTimeMillis();
        }

        public void setUpdateTime(Long updateTime){
            this.updateTime = updateTime;
        }

        public Long getUpdateTime(){
            return updateTime;
        }

        public String toString(){
            return "Node{" + "key=" + key + val + "}";
        }
    }

    public Set<Entry> entrySet (){
        return super.keySet();
    }

    /**
     * copy HashMap 的 hash 实现
     * @param key
     * @return
     */
    public int hash(Object key ){
        int h;
        return (key == null)?0:(h = key.hashCode()) ^ (h>>>16);
    }

    public Object put(Object key, Object val){
        int hash = hash(key);
        int index = hash % arraySize;
        Node currentNode = (Node) arrays[index];
        if(currentNode == null){
            arrays[index] = new Node(null, null, key, val);
            QUEUE.offer((Node) arrays[index]);
            sizeUp();
        }else{
            Node cNode = currentNode;
            Node nNode = cNode;

            //存在就覆盖
            if(nNode.key == key){
                cNode.val = val;
            }
            while (nNode.next != null){
                //key存在 就覆盖 简单判断
                if(nNode.key == key){
                    nNode.val = val;
                    break;
                }else{
                    //不存在就新增链表
                    sizeUp();
                    Node node = new Node(nNode ,null ,key ,val);
                    //写入队列
                    QUEUE.offer(currentNode);
                    cNode.next = node;
                }
                nNode = nNode.next;
            }
        }
        return null;
    }

    public Object get(Object key){
        int hash = hash(key);
        int index = hash % arraySize;
        Node currentNode = (Node)arrays[index];
        if (currentNode == null){
            //更新时间
            currentNode.setUpdateTime(System.currentTimeMillis());
            //没有冲突
            return currentNode;
        }
        Node nNode = currentNode;
        while(nNode.next != null){
            if(nNode.next != null){
                //更新时间
                currentNode.setUpdateTime(System.currentTimeMillis());
                return nNode;
            }
            nNode = nNode.next;
        }
        return super.get(key);
    }

    public Object remove(Object key){
        int hash = hash(key);
        int index = hash % arraySize;
        Node currentNode = (Node) arrays[index];
        if(currentNode == null){
            return null;
        }
        if(currentNode.key == key){
            sizeDown();
            arrays[index] = null;
            //移除队列
            QUEUE.poll();
            return currentNode;
        }
        Node nNode = currentNode;
        while (nNode.next != null){
            if(nNode .key == key){
                sizeDown();
                //链表中找到了把上一个节点的next指向当前节点的下一个节点
                nNode.pre.next = nNode.next;
                nNode = null;
                //i移除队列
                QUEUE.poll();
                return nNode;
            }
            nNode = nNode.next;
        }
        return super.remove(key);
    }
    /**
     * 增加size
     */
    private void sizeUp() {
        //在put值时认为里面已经有数据了
        flag = true;
        if(size== null){
            size = new AtomicInteger();
        }
        int size = this.size.incrementAndGet();
        if (size >= MAX_SIZE) {
            //找到队头数据
            Node node = (Node) QUEUE.poll();
            if (node == null) {
                throw new RuntimeException("data error");
            }
            //移除该key
            Object key = node.key;
            remove(key);
            lruCallback();
        }
    }

    /**
     * 数量减小
     */
    private void sizeDown(){
        if(QUEUE.size() == 0){
            flag = false;
        }
        this.size.decrementAndGet();
    }

    public int size(){
        return size.get();
    }

    private void lruCallback(){
        System.out.println("lruCallback");
    }


}
