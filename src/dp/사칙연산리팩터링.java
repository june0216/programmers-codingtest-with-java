package src.dp;

import java.util.Arrays;
import java.util.Comparator;

public class 사칙연산리팩터링 {
    /*
    max재귀 함수와 min 재귀 함수를 합치고 type으로 최대, 최소 구분

     */

    private static final int[] INIT = { // 최대 최소 type에 따라 다른 값으로 초기화
            Integer.MIN_VALUE,
            Integer.MAX_VALUE,
    };

    //자바에서는 제네릭 클래스나 인터페이스의 배열을 선언하지 못한다. -> 인터페이스를 정의하여 배열을 선언
    private interface IntComparator extends Comparator<Integer>{ //IntComparator라는 인터페이스를 정의함으로써, Comparator<Integer>를 확장

    }

    private static final IntComparator[] COMP = { //IntComparator는 Comparator<Integer> 인터페이스를 확장
            (a, b) -> Integer.compare(a, b), // 오름차순 -> 최대일 경우 인덱스 0이므로 이 람다식이 실행
            (a, b) -> Integer.compare(b, a), // 내림차순  -> 최소일 경우 인덱스 1이므로 이 람다식이 확인
    };
    private final int[][][] mem = new int[202][202][2];  // min, max 각각 재귀함수이므로 메모이제이션도 각각 2개씩 만들어야한다.
    // 202인 이유 -> 범위를 나타내기때문

    private int compute(int start, int end, int type,String[] arr){ // type = 0일 떄 max 값 , 1일 때 min 값
        if(mem[start][end][type] != Integer.MIN_VALUE) return mem[start][end][type]; // 메모이제이션 활용
        if(end - start == 1) return Integer.parseInt(arr[start]); // 종료 조건 -> 범위에 하나의 숫자만 있을 때 해당 숫자 변환
        int result = INIT[type];
        for(int i =  start +1; i < end; i+=2){
            int leftValue = compute(start, i ,type, arr); // start ~ i까지 중 제일 큰 값
            int calculateValue;
            if(arr[i].equals("+")){
                int rightValue = compute(i+1, end, type,arr); // i+1 ~ end까지 중 제일 큰 값
                calculateValue = leftValue+rightValue;
            }else{
                int rightValue = compute(i+1, end, 1-type,arr); // min
                calculateValue = leftValue-rightValue;
            }
            if(COMP[type].compare(calculateValue, result) > 0) result = calculateValue;
        }
        return mem[start][end][type] = result;

    }


    public int solution(String[] arr){
        for(int[][] m: mem){
            for(int[] row:m){
                Arrays.fill(row, Integer.MIN_VALUE);
            }
        }


        return compute(0, arr.length, 0,arr);

    }
}
