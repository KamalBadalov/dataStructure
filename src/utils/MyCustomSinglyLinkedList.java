package utils;

import java.util.*;

public class MyCustomSinglyLinkedList<E> implements List<E>, Queue<E> {

    int size = 0;
    Node<E> tail;
    Node<E> head;

    /**
     * Получения количества элементов в списке
     *
     * @return количество элементов
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Проверка пустой ли наш список
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Проверяет есть ли такой обьект в нашем списке
     *
     * @param o
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * Конвертирует список в массив типа Object[]
     *
     * @return массив типа Object[]
     */
    @Override
    public Object[] toArray() {
        Node<E> h = head;
        Object[] objects = new Object[size];
        for (int i = 0; i < size; i++) {
            objects[i] = h.getData();
            h = h.getNext();
        }
        return objects;
    }

    /**
     * Конвертирует список в массив типа Object[]
     *
     * @return каст массива Object[] в T[]
     */

    @Override
    public <T> T[] toArray(T[] a) {
        Node<E> h = head;
        Object[] objects = new Object[size];
        for (int i = 0; i < size; i++) {
            objects[i] = h.getData();
            h = h.getNext();
        }
        return (T[]) objects;
    }

    /***
     * Добавление элемента в конец
     *
     * @param e
     * */
    @Override
    public boolean add(E e) {
        add(size, e);
        return true;
    }

    /**
     * Добавление элемента в конец списка
     *
     * @param e the element to add
     * @return boolean
     */
    @Override
    public boolean offer(E e) {
        add(size, e);
        return true;
    }

    /**
     * Удаление первого элемента очереди  с исключением
     *
     * */
    @Override
    public E remove() {
        return remove(0);
    }

    /**
     * Удаление первого элемента без использования исключений
     *
     */
    @Override
    public E poll() {
        if (size < 0) {
            return null;
        }
        return remove(0);
    }

    /**
     * Получение первого элемента очереди
     *
     * @return E
     */
    @Override
    public E element() {
        return get(0);
    }

    /**
     * Получение первого элемента в очереди
     *
     * @return E
     */
    @Override
    public E peek() {
        return get(0);
    }

    /**
     * Удаление элемента списка по обьекту
     *
     */
    @Override
    public boolean remove(Object o) {
        int removedIndex = indexOf(o);
        if (removedIndex != -1) {
            remove(removedIndex);
            return true;
        }
        return false;
    }

    /**
     * Проверить содержит ли наш список все элементы коллекции c
     *
     */
    @Override
    public boolean containsAll(Collection<?> c) {

        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Добавление в конец всех элементов в наш список с коллекции с
     *
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            add(e);
        }
        return true;
    }

    /**
     * Добавление всех элементов с коллекции с в наш список по индексу
     *
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndex(index);

        if (index == size - 1) {
            for (E e : c) {
                add(e);
            }
        } else {
            Node<E> h = getNodeByIndex(index - 1);
            for (E e : c) {
                Node<E> node = new Node<>(e, h.getNext());
                h.setNext(node);
                h = h.getNext();
                size++;
            }
        }
        return true;
    }

    /**
     * Удаление всех совпадающих элементов с коллекции с
     *
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemoveNonMatches = false;
        for (Object o : c) {
            if (contains(o)) {
                remove(o);
                isRemoveNonMatches = true;
            }
        }
        return isRemoveNonMatches;
    }

    /**
     * Удаление всех элементов которые не совпадают с элементами коллекции с
     *

     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isRemoveNonMatches = false;
        Node<E> h = head;
        for (int i = 0; i < c.size(); i++) {
            if (!c.contains(h.data)) {
                remove(h.data);
            }
            h = h.getNext();
        }

        return isRemoveNonMatches;
    }

    /**
     * Очистка листа
     */
    @Override
    public void clear() {
        tail = null;
        head = null;
        size = 0;
    }

    /**
     * Получение элемента списка по индексу
     *
     * */
    @Override
    public E get(int index) {
        Node<E> h = getNodeByIndex(index);
        return h.data;
    }

    /**
     * Задание элемента по индексу
     *
     * @return E
     */
    @Override
    public E set(int index, E element) {
        checkIndex(index);

        if (index == 0) {
            head.setData(element);
            return element;
        }
        if (index == size - 1) {
            tail.setData(element);
            return element;
        }
        Node<E> h = getNodeByIndex(index);
        h.setData(element);
        return element;
    }

    /**
     * Добавление элемента в список по индексу
     *
     */
    @Override
    public void add(int index, E element) {

        if (index == 0) {
            head = new Node<>(element, head);
        } else {
            Node<E> h = getNodeByIndex(index - 1);
            Node<E> node = new Node<>(element, h.getNext());
            h.setNext(node);
        }
        size++;
    }

    /**
     * Удаление элемента по индексу
     *
     */
    @Override
    public E remove(int index) {
        checkIndex(index);

        E removedObject;
        if (index == 0) {
            removedObject = head.getData();
            head = head.getNext();
            size--;
            return removedObject;
        }

        //wtf
        if (index == size - 1) {
            removedObject = tail.getData();
            tail = getNodeByIndex(index - 1);
            size--;
            return removedObject;
        }

        Node<E> h = getNodeByIndex(index - 1);
        removedObject = h.data;
        h.next = h.getNext().getNext();
        size--;
        return removedObject;
    }

    /**
     * @param index
     * @return Node<E>
     */
    private Node<E> getNodeByIndex(int index) {
        Node<E> h = head;
        for (int i = 0; i < index; i++) {
            h = h.getNext();
        }
        return h;
    }

    /**
     * Get index by object first matches
     *
     * @param o element to search for
     * @return int
     */
    @Override
    public int indexOf(Object o) {
        int index = 0;
        Node<E> h = head;
        if (o != null) {
            for (int i = 0; i < size; i++) {
                if (o.equals(h.data)) {
                    return index;
                }
                h = h.getNext();
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (h.data == null) {
                    return index;
                }
                index++;
                h = h.getNext();
            }
        }
        return -1;
    }

    /**
     * Получить обьект по индексу последнего совпадния
     *
     */
    @Override
    public int lastIndexOf(Object o) {
        int index = 0;
        int foundIndex = -1;
        Node<E> h = head;
        if (o != null) {
            for (int i = 0; i < size; i++) {
                if (o.equals(h.data)) {
                    foundIndex = index;
                }
                h = h.next;
            }
            return foundIndex;
        } else {
            for (int i = 0; i < size; i++) {
                if (h.data == null) {
                    foundIndex = index;
                }
                index++;
                h = h.next;
            }
        }
        return foundIndex;
    }


    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size "
                    + size);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr<>(head);
    }


    @Override
    public ListIterator<E> listIterator() {
        return new ListItr<>();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        int start = 0;
        Node<E> h = head;
        while (start != index) {
            h = h.getNext();
            start++;
        }
        return new ListItr<>(h);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }

        Object[] objectCopy = new Object[toIndex];
        int start = 0;
        Node<E> h = head;
        while (start != fromIndex) {
            h = h.getNext();
            start++;
        }

        for (int i = 0; i < toIndex; i++) {
            objectCopy[i] = h.data;
            h = h.getNext();
        }
        return (List<E>) Arrays.asList(objectCopy);
    }

    private class Node<E> {
        private E data;
        private Node<E> next;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    private class Itr<E> implements Iterator<E> {
        Node<E> current;

        public Itr(Node<E> current) {
            this.current = current;
        }

        public Itr() {
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E temp = current.getData();
            current = current.getNext();
            return temp;
        }
    }

    private class ListItr<E> extends Itr<E> implements ListIterator<E> {

        public ListItr(Node<E> indexNode) {
            super(indexNode);
        }

        public ListItr() {
            super();
        }

        @Override
        public boolean hasPrevious() {
            return current != head;
        }

        @Override
        public E previous() {
            int indexCurrent = indexOf(current.data);
            int start = 0;
            MyCustomSinglyLinkedList<E>.Node<E> h = (MyCustomSinglyLinkedList<E>.Node<E>) head;
            while (start < indexCurrent - 1) {
                h = h.getNext();
                start++;
            }
            return h.data;
        }

        @Override
        public int nextIndex() {
            return indexOf(current.data) + 1;
        }

        @Override
        public int previousIndex() {
            if (current != head) {
                return indexOf(current.data) - 1;
            }
            return 0;
        }

        @Override
        public void remove() {
            super.remove();
        }

        @Override
        public void set(E e) {
            current.setData(e);
        }

        @Override
        public void add(E e) {
            current = new Node<>(e, current);
            size++;
        }
    }
}
