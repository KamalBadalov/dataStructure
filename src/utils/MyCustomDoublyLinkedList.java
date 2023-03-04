package utils;

import java.util.*;
import java.util.List;

public class MyCustomDoublyLinkedList<E> implements List<E>, Deque<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * Добавление элемента в начало списка
     *
     * @param e добавляемый элемент
     */
    @Override
    public void addFirst(E e) {
        Node<E> h = new Node<>(null, e, head);
        if (size == 0) {
            head = h;
            tail = head;
            size++;
            return;
        }
        head.setPrevious(h);
        head = h;
        size++;
    }

    /**
     * Добавление элемента в конец списка
     *
     * @param e добавляемый элемент
     */
    @Override
    public void addLast(E e) {
        if (tail == null) {
            tail = new Node<>(head, e, null);
            head = tail;
        } else {
            tail = tail.next = new Node<>(tail, e, tail);
        }
        size++;
    }

    /**
     * Добавление элемента в начало списка без исключений
     *
     * @param e добавляемый элемент
     */
    @Override
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    /**
     * Добавление элемента в конец списка без исключений
     *
     * @param e добавляемый элемент
     */
    @Override
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    /**
     * Удаление элемента с начала списка
     *
     * @return удаляемый элемент
     */
    @Override
    public E removeFirst() {
        if (size < 0) {
            return null;
        }
        return remove(0);
    }

    /**
     * Удаление элемента с конца списка
     *
     * @return удалямый элемент
     */
    @Override
    public E removeLast() {
        if (size < 0) {
            return null;
        }
        return remove(size);
    }

    /**
     * Удаление элемента с начала списка
     *
     * @return удаляемый элемент
     */
    @Override
    public E pollFirst() {
        if (size < 0) {
            return null;
        }
        return remove(0);
    }

    /**
     * Удаление элемента с конца списка
     *
     * @return удаляемый элемент
     */
    @Override
    public E pollLast() {
        if (size < 0) {
            return null;
        }
        return remove(size);
    }

    /**
     * Получение первого элемента списка
     *
     * @return получаемый элемент
     */
    @Override
    public E getFirst() {
        return get(0);
    }

    /**
     * Получение последнего элемента списка
     *
     * @return получаемый элемент
     */
    @Override
    public E getLast() {
        return get(size);
    }

    /**
     * Получение первого элемента списка
     *
     * @return получаемый элемент
     */
    @Override
    public E peekFirst() {
        return get(0);
    }

    /**
     * Получение последнего элемента списка
     *
     * @return получаемый элемент
     */
    @Override
    public E peekLast() {
        return get(size);
    }

    /**
     * Удаление по обьекту первого совпадения
     *
     * @param o удаляемый элемент
     */
    @Override
    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    /**
     * Удаление по обьекту последнего совпадения
     *
     * @param o удаляемый элемент
     */
    @Override
    public boolean removeLastOccurrence(Object o) {
        int removedIndex = lastIndexOf(o);
        if (removedIndex < 0) {
            remove(removedIndex);
            return true;
        }
        return false;
    }

    /**
     * Добавление элемента в конец списка без иключений
     *
     * @param e добавляемый элемент
     */
    @Override
    public boolean offer(E e) {
        return add(e);
    }

    /**
     * Удаление с начала списка
     *
     * @return удаляемый элемент
     */
    @Override
    public E remove() {
        return remove(0);
    }

    /**
     * Удаление с начала списка без иключений
     *
     * @return удаляемй элемент
     */
    @Override
    public E poll() {
        if (size < 0) {
            return null;
        }
        return remove(0);
    }

    /**
     * Получение первого элемента списка
     *
     * @return получаемый элемент
     */
    @Override
    public E element() {
        return get(0);
    }

    /**
     * Получение первого элемента списка
     *
     * @return возвращаемый элемент
     */
    @Override
    public E peek() {
        return get(0);
    }

    /**
     * Добавление в начало списка
     *
     * @param e добавляемый элемент
     */
    @Override
    public void push(E e) {
        addFirst(e);
    }

    /**
     * Удаление с начала списка
     *
     * @return удаляемый элемент
     */
    @Override
    public E pop() {
        return remove(0);
    }

    /**
     * Обратный итератор
     */
    @Override
    public Iterator<E> descendingIterator() {
        return new DescendingItr<>(tail);
    }

    /**
     * Получение длинны списка
     *
     * @return длинну
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Проверка на наличие элементов в списке
     */
    @Override
    public boolean isEmpty() {
        return size >= 0;
    }

    /**
     * Проверка содержит ли список такой элемент
     *
     * @param o element whose presence in this list is to be tested
     * @return
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr<>(head);
    }

    /**
     * Конвертирует список в массив
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
     * Конвертирует список в массив типа передаваемого массива
     */
    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) toArray();
    }

    /**
     * Добавление в конец списка
     *
     * @param e добавляемы элемент
     */
    @Override
    public boolean add(E e) {
        add(size, e);
        return true;
    }

    /**
     * Удаление элемента по обьекту
     *
     * @param o удаляемый обьект
     */
    @Override
    public boolean remove(Object o) {
        int indexRemoved = indexOf(o);
        if (indexRemoved > 0)
            remove(indexRemoved);
        return false;
    }

    /**
     * Проверяет содержит ли список все элементы коллекции с
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o))
                return false;
        }
        return true;
    }

    /**
     * Добавление всех элементов коллекции в конец списка
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            addLast(e);
        }
        return true;
    }

    /**
     * Добавление всех элементов коллекции всписок по индексу
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    /**
     * Удаление всех совпадений элементов с коллекции
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object e : c) {
            if (contains(e))
                remove(e);
        }
        return true;
    }

    /**
     * Удаление всех не совпадений элементов с коллекции
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e))
                remove(e);
        }
        return false;
    }

    /**
     * Очистка списка
     */
    @Override
    public void clear() {
        tail = null;
        head = null;
        size = 0;
    }

    /**
     * Получение элемента по индексу
     *
     * @param index возвращаемого элемента
     * @return возвращаемый элемент
     */
    @Override
    public E get(int index) {
        return getNodeByIndex(index).getData();
    }

    /**
     * Измение элемента по индексу
     *
     * @param index   элемента который нужно поменять
     * @param element элемент который будет задан
     * @return новый элемент
     */
    @Override
    public E set(int index, E element) {
        getNodeByIndex(index);
        head.setData(element);
        return element;
    }

    /**
     * Добавление по индексу
     *
     * @param index   добавления
     * @param element добавляемый
     */
    @Override
    public void add(int index, E element) {
        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            Node<E> foundNode = getNodeByIndex(index - 1);
            foundNode.setNext(new Node<>(foundNode.getPrevious(), element, foundNode));
            size++;
        }
    }

    /**
     * Удаление элемента по индексу
     *
     * @param index индекс удаляемого элемента
     * @return удаленный элемент
     */
    @Override
    public E remove(int index) {
        checkIndex(index);

        E removedObject;
        if (index == 0) {
            removedObject = head.getData();
            head = head.getNext();
            head.setPrevious(null);
            size--;
            return removedObject;
        }
        if (index == size) {
            removedObject = head.data;
            tail = tail.getPrevious();
            tail.setNext(null);
            size--;
            return removedObject;
        }
        Node<E> foundNode = getNodeByIndex(index);
        removedObject = foundNode.data;
        foundNode = foundNode.getPrevious();
        foundNode.setNext(foundNode.getNext().getNext());
        size--;
        return removedObject;
    }

    /**
     * Получение индекса по обьекту  первого совпадения
     */
    @Override
    public int indexOf(Object o) {
        int foundIndex = 0;
        Node<E> h = head;
        if (o != null) {
            for (int i = 0; i < size; i++) {
                if (o.equals(h.getData())) {
                    return foundIndex;
                }
                h = h.getNext();
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (h.getData() == null) {
                    return foundIndex;
                }
            }
        }
        return -1;
    }

    /**
     * Получение индекса по обьекту  последнего совпадения
     */
    @Override
    public int lastIndexOf(Object o) {
        int foundIndex = 0;
        Node<E> t = tail;
        if (o != null) {
            for (int i = size; i > 0; i--) {
                if (o.equals(t.getData())) {
                    return foundIndex;
                }
                t = t.getNext();
            }
        } else {
            for (int i = size; i > 0; i--) {
                if (t.getData() == null) {
                    return foundIndex;
                }
                t = t.getPrevious();
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr<>();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListItr<>(getNodeByIndex(index));
    }

    /**
     * Конвертирование листа с заданным началом и концом
     *
     * @param fromIndex начало
     * @param toIndex   конец
     * @return новый список
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        Object[] objects = new Object[toIndex];
        Node<E> node = getNodeByIndex(fromIndex);
        for (int i = fromIndex; i < toIndex; i++) {
            objects[i] = node.getData();
            node = node.getNext();
        }
        return (List<E>) Arrays.asList(objects);
    }

    /**
     * Получение ноды по индексу
     *
     * @param index ноды которую ищем
     * @return ноду
     */
    private Node<E> getNodeByIndex(int index) {
        Node<E> node;
        if (isIndexMoreMiddle(index)) {
            node = tail;
            for (int i = size; i > index; i--) {
                node = node.getPrevious();
            }
            return node;
        }
        node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }

    /**
     * Проверка больше ли передаваемый индекс середины списка
     *
     * @param index
     */
    private boolean isIndexMoreMiddle(int index) {
        return index > size / 2;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size "
                    + size);
        }
    }


    private class Node<E> {
        private Node<E> previous;
        private E data;
        private Node<E> next;

        public Node(Node<E> previous, E data, Node<E> next) {
            this.previous = previous;
            this.data = data;
            this.next = next;
        }

        public Node<E> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<E> previous) {
            this.previous = previous;
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
            return current.getNext() != null;
        }

        @Override
        public E next() {
            return current.data;
        }
    }

    private class ListItr<E> extends Itr<E> implements ListIterator<E> {

        public ListItr(Node<E> indexNode) {
            super(indexNode);
        }

        public ListItr() {
        }

        @Override
        public boolean hasPrevious() {
            return current.getPrevious() != null;
        }

        @Override
        public E previous() {
            return current.getPrevious().getData();
        }

        @Override
        public int nextIndex() {
            return indexOf(current.data) + 1;
        }

        @Override
        public int previousIndex() {
            return indexOf(current.data) - 1;
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
            current = new Node<>(current, e, current);
            size++;
        }
    }

    private class DescendingItr<E> extends Itr<E> {
        Node<E> tail;

        public DescendingItr(Node<E> tail) {
            this.tail = tail;
        }

        @Override
        public boolean hasNext() {
            return tail.getPrevious() != null;
        }

        @Override
        public E next() {
            return tail.getPrevious().getData();
        }
    }

}
