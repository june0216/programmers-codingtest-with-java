package src.해시.KMP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 찾기 {
    public static List<Integer> location = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String parent = br.readLine();
        String pattern = br.readLine();
        final int MOD = 100000007;
        int parentSize = parent.length();
        int patternSize = pattern.length();
        int count = 0;
        int[] table = makeTable(pattern);
        StringBuilder sb = new StringBuilder();
        sb.append(KMP( parent, pattern,table)).append("\n");
        for(int num : location){
            sb.append(num).append(" ");
        }
        System.out.println(sb);


    }

    public static int[] makeTable(String pattern){
        int patternSize = pattern.length();
        int[] pi = new int[patternSize];
        int compare = 0;
        for(int i = 1; i < patternSize; i++){
            while(compare > 0 && pattern.charAt(i) != pattern.charAt(compare)){
                compare = pi[compare-1];
            }
            if(pattern.charAt(i) == pattern.charAt(compare)){
                pi[i]= ++compare;
            }
        }
        return pi;
    }

    public static int KMP(String text, String pattern, int[] table){
        int compare = 0;
        int result =0;
        for(int i = 0; i < text.length(); i++){
            while(compare > 0 && text.charAt(i) != pattern.charAt(compare)){
                compare = table[compare-1]; //비교를 다시 시작할 위치를 결정
            }
            if(text.charAt(i) == pattern.charAt(compare)){
                if(compare == pattern.length()-1){ // 패턴 모두 탐색 완료했다면
                    result++; // 단어 1개 일치한다고 발견
                    location.add(i-pattern.length()+2); // 마지막값이니까 처음 위치를 반환하기 위해 처리함 + index는 0부터 시작하니 1을 더해주었음
                    compare = table[compare];
                }else{ // 패턴 모두 탐색하지 않았다면 이동
                    compare++;
                }
            }
        }
        return result;
    }

}
