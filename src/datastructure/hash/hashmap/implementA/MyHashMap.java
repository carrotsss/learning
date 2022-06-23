package datastructure.hash.hashmap.implementA;

/**
 * Created by carrots on 2021/12/26.
 */
public class MyHashMap<K,V> implements MyMapInterface {
    // 初始容量大小 --- 源码写法 1 << 4
    private final int DEFAULT_INITIAL_CAPACITY = 16;
    // 加载因子
    private final float DEFAULT_LOAD_FACTOR = 0.75f;
    // 根据定义的静态内部类，初始化链表，长度为默认长度
    Node[] table = new Node[DEFAULT_INITIAL_CAPACITY];
    // 长度
    private  int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object put(Object key, Object value) {
        // 计算key的hash值
        int hashValue = hash(key);
        // 计算出应该存放的位置
        int i = indexFor(hashValue,table.length);
        // 如果i处有数据且key一样,进行覆盖
        for(Node node = table[i];node != null; node = node.next){
            Object k;
            if(node.hash == hashValue && ((k = node.key)==key||key.equals(k))){
                Object oldValue = node.value;
                node.value = value;
                return  oldValue;
            }
        }
        // 如果i位置没有数据，或i位置有数据，但key是新的key,新增节点
        addEntry(key,value,hashValue,i);
        return null;
    }

    @Override
    public Object get(Object key) {
        // 根据对象的hashcode计算hash值
        int hashValue = hash(key);
        // 根据hash值和链表长度，获取插入位置的索引
        int i = indexFor(hashValue,table.length);
        for(Node node = table[i];node != null;node = node.next){
            if(node.key.equals(key) && hashValue == node.hash){
                return node.value;
            }
        }
        return null;
    }

    // 向Entry添加元素
    // hashvalue --- hash值
    // i --- 索引位置
    public void addEntry(Object key,Object value,int hashValue,int i){
        // 如果超过了数组约定长度，就扩容
        if(++size >= table.length * DEFAULT_LOAD_FACTOR){
            Node[] newTable = new Node[table.length << 1];
            // 复制数组
            //System.arraycopy(table,0,newTable,0,table.length);
            transfer(table,newTable);
            table = newTable;
        }
        // 得到i处的数据
        Node eNode = table[i];
        // 新增节点，将该节点的next指向前一个节点
        table[i] = new Node(hashValue,key,value,eNode);
    }

    // 引用JDK1.7的复制代码
    public void transfer(Node[] src,Node[] newTable) {         //src引用了旧的Entry数组
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) { //遍历旧的Entry数组
            Node e = src[j];             //取得旧Entry数组的每个元素
            if (e != null) {
                src[j] = null;//释放旧Entry数组的对象引用（for循环后，旧的Entry数组不再引用任何对象）
                do {
                    Node next = e.next;
                    int i = indexFor(e.hash, newCapacity); //！！重新计算每个元素在数组中的位置
                    e.next = newTable[i]; //标记[1]
                    newTable[i] = e;      //将元素放在数组上
                    e = next;             //访问下一个Entry链上的元素
                } while (e != null);
            }
        }
    }

    // 获取插入的位置(取模运算 有瑕疵)
    public int indexFor(int hashValue,int length){
        return hashValue % length;
    }

    // 获取插入的位置,根据Obeject对象的hashcode 获取hash值
    public int hash(Object key){
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    static class Node implements MyMapInterface.Entry{
        // hash值
        int hash;
        Object key;
        Object value;
        //指向下个节点（单链表）
        Node next;
        Node(int hash,Object key,Object value,Node next){
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public Object getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return value;
        }
    }
}