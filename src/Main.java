import utils.MyCustomArrayList;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> strings = new ArrayList<>();
        strings.add("p");
        strings.add("2");
        strings.add("3");
        strings.add("p");
        strings.add("2");
        strings.add("3");
        strings.add("p");
        strings.add("2");
        strings.add("3");
        strings.add("p");
        strings.add("2");
        strings.add("3");

        MyCustomArrayList<String > arrayList = new MyCustomArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.addAll( 1,strings);
        for (String s : arrayList
             ) {
            System.out.println(s);

        }

    }


//    public static int[] quickSort(int[] array, int pivot) {
//        int wail = 0;
//        pivot = array.length - 1;
//        boolean isSorted = false;
//        while (!isSorted){
//            isSorted = true;
//        for (int current = 0; current < array.length; current++) {
//            if (array[pivot] > array[current]) {
//                var temp = array[current];
//                array[current] = array[wail];
//                array[wail] = temp;
//                wail++;
//            } else if (){
//                isSorted = false;
//                pivot--;
//            }
//        }
//        }
//        return array;
//    }
}