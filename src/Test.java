import utils.MyCustomArrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Test {
    private final MyCustomArrayList<String> myCustomArrayList;
    private final List<String> testList;

    public Test() {
        this.myCustomArrayList = new MyCustomArrayList<>();
        this.testList = new ArrayList<>();
        fillList();
        menu();
    }


    private void fillList() {
        int i = 25;
        while (i != 75) {
            String v = Integer.toString(i);
            testList.add(v);
            i++;
        }

    }

    public void menu() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("""
                    1) Добавить запись
                    2) Получить запись по индексу
                    3) Заменить запись
                    4) Удалить запись
                    5) Показать все запись
                    6) Вставить записи  с другого блокнота в конец списка
                    7) Проверить есть ли такой студент
                    8) Получить индекс первого совпадения
                    9) Получить индекс полсденего записи
                    10) Получить колисчество записей
                    11) Проверить заполнен ли список
                    12) Добавить запись по индексу
                    13) Итератор
                    14) Проверить содержит
                    15) Удалить несколько элементов коллекции
                    16) Удалить все совпадения коллекций
                    17) Вставить записи по индексу 3
                    18) Вставить все записи в конец
                    19) Удалить все несовпадения с другого блокнота
                    20) Очистить записи
                    21) удалить по индексу""");
            System.out.println();
            int number = scanner.nextInt();
            if (number == 1) {
                addToNotepad();
            } else if (number == 2) {
                System.out.println(getEntryByIndex());
            } else if (number == 3) {
                setEntryByIndex();
            } else if (number == 4) {
                removeByEntryToNotepad();
            } else if (number == 5) {
                getAll();
            } else if (number == 6) {
                addAll();
            } else if (number == 7) {
                System.out.println(isContainsNotepad()
                );
            } else if (number == 8) {
                System.out.println(getIndexByEntry()
                );
            } else if (number == 9) {
                System.out.println(getLastIndexByEntry()
                );
            } else if (number == 10) {
                System.out.println(sizeEntryNotepad()
                );
            } else if (number == 11) {
                System.out.println(isEmptyNotepad());
            } else if (number == 12) {
                addByIndexToNotepad();
            } else if (number == 13) {
                Iterator<String> itr = myCustomArrayList.iterator();
                while (itr.hasNext())
                    System.out.println(itr.next());
            } else if (number == 14) {
                checkContainsAllElementsCollection();
            } else if (number == 15) {
                entryRemoveAll();
            } else if (number == 16) {
                entryRemoveAll();
            } else if (number == 17) {
                addAllByIndex();
            } else if (number == 18) {
                addAll();
            } else if (number == 19) {
                retailAll();
            } else if (number == 20) {
                clearEntry();
            } else if (number == 21) {
                removeByIndexEntryToNotepad();
            } else {
                System.out.println("Такого варианта нет");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            menu();
        }
    }

    /**
     * Метод получения всех элементов массива
     */
    public void getAll() {
        int i = 0;
        for (Object o : myCustomArrayList) {
            System.out.print("index :" + i);
            System.out.println(" element :" + o);
            i++;
        }
    }

    private void addToNotepad() {
        Scanner input = new Scanner(System.in);
        System.out.println("Добавьте запись");
        String entry = input.next();
        myCustomArrayList.add(entry);
        getAll();
    }


    private void addByIndexToNotepad() {
        Scanner input = new Scanner(System.in);
        System.out.println("Добавьте запись");
        String entry = input.next();
        System.out.println("Введите индекс куда будет добавлен");
        int index = input.nextInt();

        myCustomArrayList.add(index, entry);
        getAll();
    }

    private int sizeEntryNotepad() {
        getAll();
        return myCustomArrayList.size();
    }

    private boolean isEmptyNotepad() {
        return myCustomArrayList.isEmpty();
    }

    private boolean isContainsNotepad() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите запись которую хотите проверить");
        String entry = input.next();
        return myCustomArrayList.contains(entry);
    }

    private void removeByIndexEntryToNotepad() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите индекс записи которой хотите удалить");
        int index = input.nextInt();
        myCustomArrayList.remove(index);
        getAll();
    }

    private void removeByEntryToNotepad() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите запись которойй хотите удалить");
        String str = input.next();
        myCustomArrayList.remove(str);
        getAll();
    }

    private void clearEntry() {
        myCustomArrayList.clear();
        getAll();
    }

    private int getIndexByEntry() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите  запись индекс  которой вы хотите получить");
        String entry = input.next();
        System.out.println("Первое совпадения");
        return myCustomArrayList.indexOf(entry);
    }

    private int getLastIndexByEntry() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите  запись индекс  которой вы хотите получить");
        String entry = input.next();
        System.out.println("Последнее совпадения");
        return myCustomArrayList.indexOf(entry);
    }

    private List<String> getEntryFromIndexToIndex() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите от какой записи");
        int fromIndex = input.nextInt();
        System.out.println("Введите до какой записи");
        int toIndex = input.nextInt();
        return myCustomArrayList.subList(fromIndex, toIndex);
    }

    private void setEntryByIndex() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите индекс");
        int index = input.nextInt();
        System.out.println("Введите запись");
        String entry = input.next();
        myCustomArrayList.set(index, entry);
        getAll();
    }

    private String getEntryByIndex() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите индекс");
        int index = input.nextInt();
        return myCustomArrayList.get(index);
    }

    private void checkContainsAllElementsCollection() {
        System.out.println(myCustomArrayList.containsAll(testList));
    }

    private void entryRemoveAll() {
        getAll();
        System.out.println(myCustomArrayList.removeAll(testList));
        getAll();
    }

    private void retailAll() {
        getAll();
        System.out.println(myCustomArrayList.retainAll(testList));
        getAll();
    }

    private void addAllByIndex() {
        getAll();
        System.out.println(myCustomArrayList.addAll(3, testList));
        getAll();
    }

    private void addAll() {
        getAll();
        System.out.println(myCustomArrayList.addAll(testList));
        getAll();
    }
}
