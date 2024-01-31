package src.이분탐색;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class 순위검색 {
    /**
     * 단순히 완전 탐색으로 했을 경우 문의조건 M, 지원자수 N O(N*M)이고 최악의 경우를 따지면 50,000 * 100,000 이므로 50억이다.
     * 이를 1억으로 줄이기 위해 이진 탐색을 적용해보자
     */

    public static final char[] lang = {'-', 'j', 'p', 'c'};
    public static final char[] part = {'-', 'b', 'f'};
    public static final char[] food = {'-', 'c', 'p'};
    public static final char[] level = {'-','s', 'j'};

    // 점수를 두고 정렬
    public int[] solution(String[] info, String[] query){
        Map<String, List<Integer>> scoreInfo = buildScoresMap(info);
        int[] answer = new int[query.length];
        for(int i = 0; i < answer.length; i++){
            answer[i] = count(query[i], scoreInfo);
        }
        return answer;
    }

    public int count(String query, Map<String, List<Integer>> scoreMap){
        String[] tokens = query.split(" (and )?");

        String key = String.join("", Arrays.copyOf(tokens, tokens.length-1));
        if(!scoreMap.containsKey(key)) return 0;
        List<Integer> scores = scoreMap.get(key);

        int score = Integer.parseInt(tokens[tokens.length-1]);

        return scores.size() - binarySearch(score, scoreMap.get(key));
    }

    private int binarySearch(int score, List<Integer> scores){
        int start = 0;
        int end = scores.size();

        while(end > start){
            int mid = start + ((end-start)/2);
            if(scores.get(mid) >= score){
                end = mid;
            }else{
                start = mid+1;
            }
        }
        if(scores.get(start) < score){
            return scores.size();
        }
        return start;
    }

    /**
     * @param info 입력받은 정보
     * @return 전처리 한 Map 자료
     */
    private Map<String, List<Integer>> buildScoresMap(String[] info){
        Map<String, List<Integer>> scoreInfo = new HashMap<>();
        for(String s: info){
            String[] tokens = s.split(" ");
            int score = Integer.parseInt(tokens[tokens.length -1]); // 문자열 마지막 부분이 점수이므로 이를 받아 저장
            forEachKey(0, "", tokens, key -> {
                scoreInfo.putIfAbsent(key, new ArrayList<>());
                scoreInfo.get(key).add(score);
            });

            for(List<Integer> list : scoreInfo.values()){
                Collections.sort(list);
            }
        }
        return scoreInfo;
    }

    private void forEachKey(int index, String prefix, String[] tokens, Consumer<String> action){
        if(index == tokens.length -1){
            action.accept(prefix);
            return;
        }

        forEachKey(index +1, prefix+tokens[index], tokens, action);
        forEachKey(index +1, "-", tokens, action);
    }

}
