package src.문자열;

import java.util.ArrayList;
import java.util.List;

public class 문자열압축 {
    private List<String> split(String source, int length){ // 설정된 길이만큼 문자열을 잘라낸 token의 배열 생성
        List<String> tokens = new ArrayList<>();
        for(int startIndex = 0; startIndex< source.length(); startIndex+= length ){ // 제일 앞에서부터 차례차례 잘라야 한다.
            // 문자열 startIndex부터 끝까지 length크기만큼 잘라 tokens 리스트에 추가
            int endIndex = startIndex+ source.length();
            if(endIndex > source.length()) endIndex = source.length(); // 문자열 밖이라면 그냥 맨끝까지 범위로 삼기
            tokens.add(source.substring(startIndex, endIndex));
        }
        return tokens;
    }
    private int compress(String source, int length){ // 압축된 문자열의 길이 반환
        StringBuilder sb = new StringBuilder();
        String last = "";// 연속으로 중복된 문자열을 검사해야하므로 직전에 등장한 문자열을 담는 last 변수
        int count = 0; // 등장 횟수
        for(String token : split(source, length)){
            // 압축 문자열 구성
            if(token.equals(last)) count++;
            else{
                if(count>1){ // 이전과 다른데 누적된 같은 값이 있다면 숫자로 표시
                    sb.append(count);
                }
                sb.append(last); // 알파벳 출력
                last = token; // 이전 값 업데이트
                count = 1; // 그 다른 값으로 시작하므로 1로 세팅하여 다시 시작
            }

        }
        return sb.length();
    }
    public int solution(String s){
        int min = Integer.MAX_VALUE; // 최솟값을 구해야하므로 가장 큰 값으로 설정
        for(int length = 1; length <= s.length(); length++){ // 각 토큰 길이별로 압축 문자열의 길이를 구한다.
            int compressed = compress(s, length);
            if(compressed < min){
                min = compressed;
            }
        }
        return min;
    }
}
