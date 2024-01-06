package src.해시;

import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class A로B만들기 {
    private static Map<Character, Integer> toMap(String word){
        Map<Character, Integer> map = new HashMap<>();
        for(char c : word.toCharArray()){
            map.putIfAbsent(c, 0);
            map.put(c, map.get(c) + 1);
        }
        return map;
        /*
        Map<String, Long> 타입으로 만들 수 있는 방법
        Arrays.stream(word.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

         */
    }
    public int solution(String before, String after){
        return toMap(before).equals(toMap(after)) ? 1 : 0;
    }
}
