
import utils.MyCustomHashMap;

import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
//        Map<String, String> stringStringMap = new HashMap<>();
//        stringStringMap.put("10", "1");
//        stringStringMap.put("30", "1");
//        stringStringMap.put("20", "1");
//        stringStringMap.put("40", "1");
//        stringStringMap.put("40", "5");
//        stringStringMap.put("60", "1");
//        stringStringMap.put("70", "1");
//        stringStringMap.put("80", "1");
        MyCustomHashMap<String, String> myCustomHashMap = new MyCustomHashMap<>(64,5);
        myCustomHashMap.put("1", "1");
        myCustomHashMap.put("2", "2");
        myCustomHashMap.put("4", "3");
        myCustomHashMap.put("5", "4");
        myCustomHashMap.put("6", "5");
        myCustomHashMap.put("7", "6");
        myCustomHashMap.put("8", "7");

        System.out.println(myCustomHashMap.values());
        System.out.println(        myCustomHashMap.remove("8"));
        System.out.println(myCustomHashMap.values());

//        myCustomHashMap.put("2", "1");
//        myCustomHashMap.put("8", "1");
//        myCustomHashMap.putAll(stringStringMap);


        //        myCustomHashMap.put("3","3");
//        myCustomHashMap.put("4","4");
//        myCustomHashMap.put("5","5");
    }
}