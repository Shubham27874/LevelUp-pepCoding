import java.util.*;

public class basicHM {
    public static void test1() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("USA", 900);
        map.put("IND", 100);
        map.put("NEP", 800);
        map.put("usa", 700);

        map.put("USA", 200);
        System.out.println(map.get("Usa"));
        System.out.println(map);
        for(String s : map.keySet())
            System.out.println(s + "->" + map.get(s));
    }

    //String to frequency Map
    public static void test2(String str) {
        HashMap<Character, Integer> map = new HashMap<>();

        // for(int i = 0; i < str.length(); i++){
        //     char ch = str.charAt(i);
        //     if(!map.containsKey(ch))
        //         map.put(ch, 1);
        //     else 
        //         map.put(ch, map.get(ch) + 1);
        // }

        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        System.out.println(map);
    } 

    public static void test3(String str){
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();

        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            map.putIfAbsent(ch, new ArrayList<>());
            map.get(ch).add(i);
        }

        System.out.println(map);
    }

    public static void main(String[] args){
        test2("ababcacbabdcbda");
    }
}
