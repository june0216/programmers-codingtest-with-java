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
     * 지원자 수 N(최대 50,000) , 지원자가 검색되는 조건 개수 P(2^4 -> 조건이 필수가 아니므로), 전체 생성될  수 있는 조건의 개수 Q(최대 4*3*3*3) 라고 할 때 지원자 정보를 순회하며 검색 조건을 생성하고 리스트에 추가하는 데 O(NP)가 소요된다.
     *      * 순회 후 모든 검색 조건에 따른 리스트를 정렬하는 데는 리스트가 총 Q개고, 하나의 리스트는 최대 N개의 원소를 가지고 있으므로 Q(QN logN)이 소요된다.
     *      * 따라서 전처리에는 O(N(P+Q logN)) 소요된다.
     *
     * 단순히 완전 탐색으로 했을 경우 문의조건 M, 지원자수 N O(N*M)이고 최악의 경우를 따지면 50,000 * 100,000 이므로 50억이다.
     * 이를 1억으로 줄이기 위해 이진 탐색을 적용해보자
     * 전처리가 되었다면 문의 조건을 검사하는 것은 크게 문제가 되지 않는다. -> 문의 조건을 만족하는 지원자 수는 문의 조건을 만족하는 리스트를 찾아 해당 리스트에서 문의 조건에 따른 점수를 이진 탐색으로 구하면 된다.
     *       * 문의 조건 개수를 M이라고 했을 때 문즤 조건을 만족하는 리스트 크기는 최대 N이므로 이에 소요되는 시간 복잡도는 O(MlogN)이다. -> 최악의 경우 156만
     *       * 따라서 전체 시간 복잡도는 O(N(P+QlogN) + M logN) 이 되며, 전처리가 시간 복잡도에 지배적인 영향을 끼친다.
     *
     *
     *
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
        /*
        스트림을 이용한 버전
        return Stream.of(query).mapToInt(q -> count(q, scoreMpa)), toArray();
         */
        return answer;
    }

    public int count(String query, Map<String, List<Integer>> scoreMap){
        String[] tokens = query.split(" (and )?"); // 각 조건 사이가 공백이나 and로 구분되어 있기 때문에 정규표현식을 이용하여 담는다.

        String key = String.join("", Arrays.copyOf(tokens, tokens.length-1)); //점수를 제외한 조건들을 key로 만든다.
        if(!scoreMap.containsKey(key)) return 0;
        List<Integer> scores = scoreMap.get(key);

        int score = Integer.parseInt(tokens[tokens.length-1]);

        return scores.size() - binarySearch(score, scoreMap.get(key));
    }

    /**
     * 점수 리스트에서 기준 점수보다 크거나 같은 값 중 가장 작은 값의 인덱스를 이진 탐색을 이용하여 찾는다.
     * @param score
     * @param scores
     * @return
     */

    private int binarySearch(int score, List<Integer> scores){
        int start = 0;
        int end = scores.size();

        while(end > start){ // 이진 탐색을 범위 안의 원소가 하나만 남을 때까지 반복한다. [start, end] =>  (end - start + 1) > 1 -> end > start
            int mid = start + ((end-start)/2);
            if(scores.get(mid) >= score){ // 이 경우 중간값도 해당이 될 수 있지만 이보다 더 작은 값이 있는지 검사해야되므로 중간값을 포함하여 더 작은 값이 있는 범위로 좁혀나가야한다.
                end = mid;
            }else{
                start = mid+1;
            }
        }
        // 이진 탐색 종료 후 마지막 남은 원소가 조건을 만족한다고 할 수 없다. -> 배열 scores의 모든 원소가 score보다 작다면 이진 탘색은 scores의 마지막 원소를 결과로 내놓게 됩니다.
        // 따라서 이진 탐색의 마지막 남은 원소에 대해 조건 검사를 한 후 적절한 값을 반환해야한다.
        // 이진 탐색 이후 마지막 남은 원소는 start에 있으므로 이를 반환한다.
        if(scores.get(start) < score){
            return scores.size();
        }
        return start;
    }

    /**
     * 검색 조건을 key로 하고 점수를 value로 하는 map
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

            for(List<Integer> list : scoreInfo.values()){ // 전처리 후 정렬
                Collections.sort(list);
            }
        }
        return scoreInfo;
    }

    /**
     * 만들 수 있는 모든 조건을 재귀 함수로 완전 탐색한다.
     * @param index
     * @param prefix
     * @param tokens
     * @param action
     */
    private void forEachKey(int index, String prefix, String[] tokens, Consumer<String> action){
        if(index == tokens.length -1){
            action.accept(prefix);
            return;
        }

        forEachKey(index +1, prefix+tokens[index], tokens, action);
        forEachKey(index +1, "-", tokens, action);
    }

}
