package utils;

import java.util.*;

public class MyCustomArrayList<E> implements List<E> {

    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] objects;

    public MyCustomArrayList() {
        this.objects = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Метод возвращает длинну массива
     *
     * @return size
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * Метод проверяет равна ли длинна массива нулю
     *
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Метод проверяет содержиться ли такой обьект в массиве
     *
     * @param o element whose presence in this list is to be tested
     * @return
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * Метод создает копию нашего массива
     *
     * @return Object[]
     */
    @Override
    public Object[] toArray() {
        Object[] objectsCopy = new Object[size];
        for (int i = 0; i < size; i++) {
            objectsCopy[i] = objects[i];
        }
        return objectsCopy;
    }

    /**
     * @param a   the array into which the elements of this list are to
     *            be stored, if it is big enough; otherwise, a new array of the
     *            same runtime type is allocated for this purpose.
     * @param <T>
     * @return
     */
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(objects, size, a.getClass());
        System.arraycopy(objects, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    /**
     * Метод добавления элемента в конец массива
     *
     * @param e element whose presence in this collection is to be ensured
     * @return true
     */
    @Override
    public boolean add(E e) {
        if (size == objects.length) {
            fillingArrayIncreasedCapacity();
        }
        objects[size] = e;
        size++;
        return true;
    }

    /**
     * Метод уадления элемента по обьекту
     *
     * @param o element to be removed from this list, if present
     * @return isRemove
     */
    //TODO Делаешь два прохода!
    @Override
    public boolean remove(Object o) {
        int indexRemoved = indexOf(o);
        if (indexRemoved != -1) {
            remove(indexRemoved);
            return true;
        }
        return false;
    }

    /**
     * Метод проверяет содержит ли наш масси все эелементы коллекции с
     *
     * @param c collection to be checked for containment in this list
     * @return isContains
     */
    //TODO не проходишь по коллекции*
    @Override
    public boolean containsAll(Collection<?> c) {
        if (c.isEmpty()) {
            throw new NullPointerException();
        }
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Добавление всех элементов с коллекции с
     *
     * @param c collection containing elements to be added to this collection
     * @return true
     * @throws NullPointerException
     */
    //TODO: не правильно проверяешь количество свободных ячеек массива
    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            throw new NullPointerException();
        }
        fillingArrayIncreasedCapacity(c.size());
        for (E e : c) {
            dangerousAdd(e);
        }
        return true;
    }

    /**
     * Метод добавления без проверок
     * @param e
     */
    private void dangerousAdd(E e) {
        objects[size] = e;
        size++;
    }

    /**
     * Метод увеличвает длинну массива
     * Используется при добавлении коллекции в массив
     * @param sizeCollection
     * @return
     */
    private Object[] increaseCapacity(int sizeCollection) {
        if (getCountFreeCells() < sizeCollection) {
            int newCapacity = objects.length + (sizeCollection - getCountFreeCells());
            return new Object[newCapacity];
        }
        return objects;

    }

    /**
     * Метод заполнения массива
     * @param sizeCollection
     */
    private void fillingArrayIncreasedCapacity(int sizeCollection) {
        Object[] objectsCopy = increaseCapacity(sizeCollection);
        if (!Arrays.equals(objectsCopy, objects)){
            for (int i = 0; i < size; i++) {
                objectsCopy[i] = objects[i];
            }
            objects = objectsCopy;
        }
    }

    /**
     * Метод добавления всех элементов коллекции c по индексу
     *
     * @param index index at which to insert the first element from the
     *              specified collection
     * @param c     collection containing elements to be added to this list
     * @return true
     * @throws IndexOutOfBoundsException
     * @throws NullPointerException
     */
    //TODO: оптимизация, при добавлении в середину или
    // начало увеличивать массив на нужное количество и переносить элементы.
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size "
                    + size);
        }
        if (c.isEmpty()) {
            throw new NullPointerException();
        }
        //TODO: тоже соме
        fillingArrayIncreasedCapacity(index, c.size());
        Object[] cCopy = c.toArray();
        for (int i = index, j = 0; j < c.size(); i++, j++) {
            objects[i] = cCopy[j];
            size++;
        }
        return true;
    }

    /**
     * Метод заполнения массива по индексу
     * @param index
     * @param sizeCollection
     */
    private void fillingArrayIncreasedCapacity
            (int index, int sizeCollection) {
        Object[] objectsCopy = increaseCapacity(sizeCollection);
        for (int i = 0; i < objectsCopy.length; i++) {
            if (i <= index) {
                objectsCopy[i] = objects[i];
            } else if (i < sizeCollection) {
                objectsCopy[i] = null;
            } else {
                objectsCopy[i] = objects[index++];
            }
        }
        objects = objectsCopy;
    }

    /**
     * Метод получения свободных ячеек массива
     * @return objects.length - size
     */
    private int getCountFreeCells() {
        return objects.length - size;
    }

    /**
     * Метод удаления всех совпадений с коллекции
     *
     * @param c collection containing elements to be removed from this list
     * @return isRemoveMatches
     * @throws NullPointerException
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) {

            throw new NullPointerException();
        }
        for (Object o : c) {
            remove(o);
        }
        return true;
    }

    /**
     * Метод удаления всех не совпадений с коллекции
     *
     * @param c collection containing elements to be removed from this list
     * @return isRemoveMatches
     * @throws NullPointerException
     */

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty()) {
            throw new NullPointerException();
        }
        boolean isRemoveNonMatches = false;
        for (Object o : objects) {
            if (!c.contains(o)) {
                remove(o);
                isRemoveNonMatches = true;
            }
        }
        return isRemoveNonMatches;
    }

    /**
     * Метод очистки массива
     */
    @Override
    public void clear() {
        if (!isEmpty()) {
            for (int i = 0; i < size; i++) {
                objects[i] = null;
            }
            size = 0;
        }

    }

    /**
     * Метод получения элемента по index
     *
     * @param index index of the element to return
     * @return E
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size "
                    + size);
        }
        return (E) objects[index];
    }

    /**
     * Метод добавления по индексу
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size "
                    + size);
        }
        if (size == objects.length) {
            fillingArrayIncreasedCapacity();
        }
        if (objects[index] != null) {
            for (int i = size; i > index; i--) {
                objects[i] = objects[i - 1];
            }
        }
        objects[index] = element;
        size++;
    }

    /**
     * Метод заменяет элемент под этим индексом на элемент который мы передали
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return oldValue
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E set(int index, Object element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size "
                    + size);
        }
        var oldValue = objects[index];
        objects[index] = element;
        return (E) oldValue;
    }

    /**
     * Метод уадления элемента по индексу
     *
     * @param index the index of the element to be removed
     * @return E
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size "
                    + size);
        }
        Object removedObject = objects[index];
        objects[index] = null;
        for (int i = index; i < size - 1; i++) {
            Object temp = objects[i + 1];
            objects[i + 1] = objects[i];
            objects[i] = temp;
        }
        size--;
        return (E) removedObject;
    }

    /**
     * Метод получения индекса первого совпадения
     *
     * @param o element to search for
     * @return int
     */
    @Override
    public int indexOf(Object o) {
        if (o != null) {
            for (int i = 0; i < size; i++) {
                if (o.equals(objects[i])) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (objects[i] == null) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Метод получения индекса последнего совпадения
     *
     * @param o element to search for
     * @return int
     */
    @Override
    public int lastIndexOf(Object o) {
        if (o != null) {
            for (int i = size; i > 0; i--) {
                if (o.equals(objects[i])) {
                    return i;
                }
            }
        } else {
            for (int i = size; i > 0; i--) {
                if (objects[i] == null) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Метод возвращает лист  от указанного индекса и  до указанного индекса
     *
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex   high endpoint (exclusive) of the subList
     * @return
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        Object[] objectCopy = new Object[toIndex];
        for (int i = fromIndex; i < toIndex; i++) {
            objectCopy[i] = objects[i];
        }
        objects = objectCopy;
        return (List<E>) Arrays.asList(objects);
    }

    /**
     * Метод создает копию массива с увеличенной вместимостью
     */
    private Object[] increaseCapacity() {
        int newCapacity = size + 5;
        return new Object[newCapacity];
    }

    /**
     * Метод заполнения массива
     */
    private void fillingArrayIncreasedCapacity() {
        Object[] objectsCopy = increaseCapacity();
        for (int i = 0; i < size; i++) {
            objectsCopy[i] = objects[i];
        }
        objects = objectsCopy;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr<>();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListItr<>(index);
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr<E> implements Iterator<E> {
        int current;

        public Itr() {
        }

        @Override
        public boolean hasNext() {
            return current != size;
        }

        @Override
        public E next() {
            if (current - 1 >= size) {
                throw new NoSuchElementException();
            }
            current = current + 1;
            return (E) objects[current - 1];
        }

        @Override
        public void remove() {
            MyCustomArrayList.this.remove(current - 1);
        }
    }

    private class ListItr<E> extends Itr<E> implements ListIterator<E> {
        public ListItr(int index) {
            super();
            current = index;
        }

        public ListItr() {
        }

        @Override
        public boolean hasNext() {
            return super.hasNext();
        }

        @Override
        public E next() {
            return super.next();
        }

        @Override
        public boolean hasPrevious() {
            return current != 0;
        }

        @Override
        public E previous() {
            if (current - 1 < 0) {
                throw new NoSuchElementException();
            }
            current = current - 1;
            return (E) objects[current];
        }

        @Override
        public int nextIndex() {
            return current;
        }

        @Override
        public int previousIndex() {
            return current - 1;
        }

        @Override
        public void remove() {
            super.remove();
        }

        @Override
        public void set(E element) {
            MyCustomArrayList.this.set(current - 1, element);
        }

        @Override
        public void add(E element) {
            current = current + 1;
            if (size == objects.length) {
                fillingArrayIncreasedCapacity();
            }
            if (objects[current - 1] != null) {
                for (int i = size; i > current - 1; i--) {
                    objects[i] = objects[i - 1];
                }
            }
            objects[current - 1] = element;
            size++;
        }
    }

}