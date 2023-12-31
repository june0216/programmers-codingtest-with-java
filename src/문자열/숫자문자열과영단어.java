package src.문자열;

public class 숫자문자열과영단어 {
    //시간복잡도 = 문자열의 길이 N * 반복 M -> 일거라고 생각하지만 KMP 알고리즘으로 O(N+M)이다.

    private static final String[] words = {
            "zero", "one", "two", "three", "four", "five",
            "six", "seven", "eight", "nine",
    };
    public int solution(String s){
        for(int i = 0; i < words.length; i++){
            s.replace(words[i], Integer.toString(i));
        }
        return Integer.parseInt(s);
    }
}
