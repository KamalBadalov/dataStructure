package utils;


import javax.naming.spi.StateFactory;
import java.util.*;

public class MyCustomHashMap<K, V> implements Map<K, V> {
    Stack<Integer> capacityStack = new Stack<>();
    static final int defaultLoadFactory = 70;
    static final int defaultCapacity = 4;
    int currentCapacity;
    Node<K, V>[] nodes;
    int loadFactory;
    int size;

    public MyCustomHashMap(int capacity, int loadFactory) {
        fillingStack(capacityStack);
        this.currentCapacity = capacity;
        this.nodes = new Node[capacity];
        this.loadFactory = loadFactory;
    }

    public MyCustomHashMap() {
        fillingStack(capacityStack);
        this.currentCapacity = defaultCapacity;
        this.nodes = new Node[currentCapacity];
        this.loadFactory = defaultLoadFactory;
    }

    private void fillingStack(Stack<Integer> integerStack) {
        for (int i = 31; i >= 5; i--)
            integerStack.push((int) Math.pow(2, i));
    }

    private Node<K, V>[] increaseCapacity() {
        while (currentCapacity >= capacityStack.peek())
            capacityStack.pop();

        currentCapacity = capacityStack.pop();
        return new Node[currentCapacity];
    }


    /**
     * Получение количества элементов
     *
     * @return size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Проверкеа на пустоту
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Проверка есть ли такой ключ
     *
     * @param key
     */
    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    /**
     * Проверка есть ли такое значение
     *
     * @param value
     * @return
     */
    @Override
    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    /**
     * Получения значения по ключу
     *
     * @param key
     * @return
     */
    @Override
    public V get(Object key) {
        return getNodeByKey(key, getIndexByKey((K) key, nodes.length)).value;
    }

    /**
     * Получение ноды по ключу
     *
     * @param key
     * @param index
     * @return
     */
    private Node<K, V> getNodeByKey(Object key, int index) {
        if (nodes[index].key.equals(key)) {
            return nodes[index];
        }
        if (isPositionOccupied(index)) {
            Node<K, V> node = nodes[index];
            while (node != null) {
                if (node.key.equals(key)) {
                    return node;
                }
                node = node.next;
            }
        }
        throw new NullPointerException();
    }

    /**
     * Проверка занята ли позиция в массиве
     *
     * @param index
     */
    private boolean isPositionOccupied(int index) {
        return nodes[index] != null;
    }

    /**
     * Добавление
     */
    @Override
    public V put(K key, V value) {
        int indexAdd = getIndexByKey(key, nodes.length);

        if (isCountArrayElementsExceedsLoadArray()) {
            fillingArrayIncreasedCapacity();
        }

        Node<K, V> newNode = new Node<>(value, key, null);

        if (isPositionOccupied(indexAdd)) {
            Node<K, V> node = nodes[indexAdd];
            while (node.next != null) {
                if (key.equals(node.key)) {
                    return replaceValueByKey(node, value);
                }
                node = node.next;
            }
            node.next = newNode;
            return nodes[indexAdd].value;
        }
        nodes[indexAdd] = newNode;
        size++;
        return value;
    }

    /**
     * Замена значение по ключу
     *
     * @param node
     * @param value
     */
    private V replaceValueByKey(Node<K, V> node, V value) {
        node.value = value;
        return value;
    }

    /**
     * Удаление по ключу
     *
     * @param key
     */
    @Override
    public V remove(Object key) {
        int indexRemoved = getIndexByKey((K) key, nodes.length);
        if (!isPositionOccupied(indexRemoved)) {
            throw new NullPointerException();
        }
        size--;
        if (nodes[indexRemoved].key.equals(key)) {
            return removeWithPositionOccupiedFirstNode(indexRemoved);
        }
        return removeWithPositionOccupied(indexRemoved, key);

    }

    /**
     * Удаление следующей ноды если позиция занята
     *
     * @param indexRemoved
     * @return
     */
    private V removeWithPositionOccupiedFirstNode(int indexRemoved) {
        if (nodes[indexRemoved].next != null) {
            V removedValue = nodes[indexRemoved].value;
            nodes[indexRemoved] = nodes[indexRemoved].next;
            return removedValue;
        }
        V removedValue = nodes[indexRemoved].value;
        nodes[indexRemoved] = null;
        return removedValue;
    }

    /**
     * Удаление  если позиция занята
     *
     * @param indexRemoved
     * @param key
     * @return
     */
    private V removeWithPositionOccupied(int indexRemoved, Object key) {
        Node<K, V> node = nodes[indexRemoved];
        while (node.next != null) {
            if (node.next.key.equals(key)) {
                V removedValue = node.next.value;
                node.next = node.next.next;
                size--;
                return removedValue;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * Добавление пары ключ значение с другого мапа
     *
     * @param m
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        if (getCountFreeCells() < m.size()) {
            fillingArrayIncreasedCapacity(m.size());
        }
        for (Entry entry : m.entrySet()) {
            put((K) entry.getKey(), (V) entry.getValue());
        }
    }

    /**
     * Заполняет увеличенный массив
     *
     * @param size
     */
    private void fillingArrayIncreasedCapacity
    (int size) {
        Node<K, V>[] newArray = increaseCapacity(size);
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                newArray[getIndexByKey(nodes[i].key, newArray.length)] = nodes[i];
            }
        }
        nodes = newArray;
    }

    /**
     * Проверяет превышает ли количество элементов процента загруженности
     */
    private boolean isCountArrayElementsExceedsLoadArray() {
        int fullnessArray = (int) getFullnessArray();
        return fullnessArray >= loadFactory;
    }

    /**
     * Получение сколько процентов занято в массиве
     */
    private double getFullnessArray() {
        return (getCountEmployedCells() / nodes.length) * 100;
    }

    /**
     * Получение всех количества занятых ячеек
     */
    private double getCountEmployedCells() {
        int countElement = 0;
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                countElement++;
            }
        }
        return countElement;
    }

    /**
     * Получение количества свободных ячеек
     */
    private double getCountFreeCells() {
        return getCountEmployedCells() - nodes.length;
    }

    /**
     * Увеличевание вместимости массива
     *
     * @param size
     */
    private Node<K, V>[] increaseCapacity(int size) {
        while (size < currentCapacity)
            while (getCountFreeCells() >= size)
                capacityStack.pop();

        currentCapacity = capacityStack.pop();
        return new Node[currentCapacity];
    }
    /**
     * Увеличевание вместимости массива
     */

    /**
     * Заполнение массива с увеличенной вместимостью
     */
    private void fillingArrayIncreasedCapacity() {
        Node<K, V>[] newArray = increaseCapacity();
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                newArray[getIndexByKey(nodes[i].key, newArray.length)] = nodes[i];
            }
        }
        nodes = newArray;
    }

    /**
     * Очистка массива
     */
    @Override
    public void clear() {
        for (int i = 0; i < nodes.length - 1; i++) {
            nodes[i] = null;
        }
        size = 0;
    }

    /**
     * Получение сета ключей
     */
    @Override
    public Set<K> keySet() {
        if (size == 0) {
            throw new NullPointerException();
        }
        Set<K> kSet = new HashSet<>();
        for (int i = 0; i < nodes.length; i++) {
            Node<K, V> node = nodes[i];
            while (node != null) {
                kSet.add(node.key);
                node = node.next;
            }
        }
        return kSet;
    }


    /**
     * Получение коллекции со всеми значениями
     */
    @Override
    public Collection<V> values() {
        Collection<V> collection = new ArrayList<>(size);
        for (Node<K, V> kvNode : nodes) {
            if (kvNode != null) {
                collection.add(kvNode.value);
                Node<K, V> node = kvNode;
                while (node.next != null) {
                    collection.add(node.next.value);
                    node = node.next;
                }
            }
        }
        return collection;
    }

    /**
     * Получение индекса по ключу
     */
    private int getIndexByKey(K key, int length) {
        if (key == null) {
            return 0;
        }
        return key.hashCode() & (length - 1);
    }

    /**
     * Получение сета пары ключ значений
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        if (size == 0) {
            throw new NullPointerException();
        }
        Set<Map.Entry<K, V>> entrySet = new HashSet<>();
        for (Node<K, V> kvNode : nodes) {
            Node<K, V> node = kvNode;
            while (node != null) {
                entrySet.add((new EntryCustom(node)));
                node = node.next;
            }
        }
        return entrySet;
    }


    protected static class Node<K, V> {
        V value;
        K key;
        Node<K, V> next;


        public Node(V value, K key, Node<K, V> next) {
            this.value = value;
            this.key = key;
            this.next = next;
        }
    }

    private class EntryCustom implements Entry<K, V> {
        K key;
        V value;

        public EntryCustom(Node<K, V> node) {
            this.key = node.key;
            this.value = node.value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return value;
        }
    }

}
