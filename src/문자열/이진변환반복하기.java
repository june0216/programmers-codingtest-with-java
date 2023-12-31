package src.문자열;

public class 이진변환반복하기 {
    private int countZeros(String s){ // 0인 개수 세기
        int zeroCount = 0;
        for(char c : s.toCharArray()){
            if (c == '0') zeroCount +=1;
        }
        return zeroCount;
    }
    public int[] solution(String s){
        int removed = 0;
        int count = 0;
        while(!s.equals("1")){ //1이 될때까지 반복
            count+=1; // 반복 횟수 카운트
            int zeroCount = countZeros(s); // 0의 개수
            removed += zeroCount; // 지워진 개수 누적
            int oneCount = s.length() - zeroCount; // 1인 개수 구하기
            s = Integer.toString(oneCount, 2); // 2진법으로 바꿈
        }
        return new int[] {count, removed};
    }
}
