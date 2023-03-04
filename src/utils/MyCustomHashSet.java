package utils;

import java.util.*;

public class MyCustomHashSet<E> implements Set<E> {
    MyCustomHashMap<E, Object> myCustomHashMap = new MyCustomHashMap<>();
    MyCustomDoublyLinkedList<E> keyList = new MyCustomDoublyLinkedList<>();

    public MyCustomHashSet() {
        fillingKeyList(keyList);
    }

    @Override
    public int size() {
        return myCustomHashMap.size();
    }

    @Override
    public boolean isEmpty() {
        return myCustomHashMap.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return myCustomHashMap.containsKey(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr<>(0);
    }

    @Override
    public Object[] toArray() {
        return myCustomHashMap.keySet().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return myCustomHashMap.keySet().toArray(a);
    }

    @Override
    public boolean add(E e) {
        return (boolean) myCustomHashMap.put(e, new Object());
    }

    @Override
    public boolean remove(Object o) {
        return myCustomHashMap.remove(o, new Object());
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!myCustomHashMap.containsKey(o)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (Object o : c) {
            myCustomHashMap.put((E) o,new Object());
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (Object o : c) {
            if (!myCustomHashMap.containsKey(o)) {
                myCustomHashMap.remove(o);
            }
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            if (myCustomHashMap.containsKey(o)) {
                myCustomHashMap.remove(o);
            }
        }
        return false;
    }

    @Override
    public void clear() {
        myCustomHashMap.clear();
    }

    private void fillingKeyList(List<E> keyList){
        keyList.addAll(myCustomHashMap.keySet());
    }

    private class Itr<K> implements Iterator<K>{
        int current;

        public Itr(int current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return keyList.get(current + 1) != null;
        }


        @Override
        public K next() {
            return (K) keyList.get(current + 1);
        }
    }
}
