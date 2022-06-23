package datastructure.hash.hashmap.implementB;

public class HashMap<K, V> implements Map<K, V> {
    Node<K, V>[] table = null;
    int size = 0;

    public HashMap() {
        table = new Node[16];
    }

    public int hash(K k) {
        int a = k.hashCode() % 15;
        return a > 0 ? a : Math.abs(a);
    }

    @Override
    public V put(K k, V v) {
        int index = hash(k);
        Node<K, V> node = table[index];
        if (null == node) {
            table[index] = new Node<>(k, v, index, null);
            size++;
        } else {
            table[index] = new Node<>(k, v, index, node);
        }
        return table[index].getValue();
    }

    @Override
    public V get(K k) {
        int index = hash(k);
        if (0 == size) {
            return null;
        }
        Entry<K, V> node = findValue(table[index], k);
        return node == null ? null : node.getValue();
    }

    private Entry<K, V> findValue(Node node, K k) {
        if (k.equals(node.getKey()) || k == node.getKey()) {
            return node;
        } else {
            if (null != node.next) {
                return findValue(node.next, k);
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    class Node<K, V> implements Entry {
        K k;
        V v;
        int hash;
        Node<K, V> next;

        public Node(K k, V v, int hash, Node<K, V> next) {
            this.k = k;
            this.v = v;
            this.hash = hash;
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap();
        for (int i = 0; i < 100; i++) {
            map.put("bcw", "sss");
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(map.get("bcw"));
            ;
        }

    }
}
