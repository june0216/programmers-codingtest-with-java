package src.재귀;

import java.util.ArrayList;
import java.util.List;

public class 모음사전 {
    //단어를 생성하려면 알파벳을 하나씩 붙여가야 한다.
    /*
    1) 상태 = 현재까지 이어 붙인 단어
    2) 종료 조건 = 길이가 5에 도달할 때 (참고 - 처음에는 종료 조건을 UUUUU로 생각했다. 부분 문제의 종료 조건을 생각해야 하는데 전체 문제의 종료 조건을 생각해서 그랬던 것 같다
    3) 점화식 = 단어 + ( 단어 + "A") + (단어 + "E") + ... + (단어 + "U")
     */
    private static final char[] CHARS = "AEIOU".toCharArray();

    //재귀는 하나의 상태가 여러 상태로 전이될 떄 종료 조건이 도달될 때까지 첫번째 전이를 계속한다.
    // 그리고 마지막 AAAAU까지 종료 조건을 만나면 반환하고 가장 마지막 재귀부터 다음 전이를 선택한다.
    private List<String> generate(String word, List<String> words){ // (단어)로 시작하는 모든 단어를 구해야하므로 List를 반환
        words.add(word);
        if(word.length() == 5) return words;
        for(char c : CHARS){
            words.addAll(generate(word + c, words));
        }
        return words;
    }

    public int solution(String word) {
        List<String> words = new ArrayList<>();
        generate("", words);
        return words.indexOf(word); // 여기서 list는 ["", "A", "AA", ..]로 구성된다.
    }
}
