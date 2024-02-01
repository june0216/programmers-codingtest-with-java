package src.dp;

import java.util.Arrays;

public class 사칙연산 {
    /*
    연산자가 + 일경우 -> 최댓값을 갖기 위해서 양쪽 피연산자 모두 큰 값이어야함
    연산자가 - 인경우 -> 왼쪽 피연산자는 클수록, 오른쪽 피연산자는 작을수록 유리하다.

    부분 문제 -> 최종적으로 구해야 할 값은 마지막으로 계산된 값 중에서의 최댓값


     */
    private final int[][] maxMem = new int[202][202];  // min, max 각각 재귀함수이므로 메모이제이션도 각각 2개씩 만들어야한다.
    // 202인 이유 -> 범위를 나타내기때문

    // 범위 = 제일 큰범위부터
    private int max(int start, int end, String[] arr){
        /*
        1) 상태 =  max(start, end) -> [start, end)  범위에서  나올 수 있는 최대 연산 결과
        2) 종료 조건 = [start, end)  범위의 수가 1개
        3) 점화식 =
                3-1) 연산자가 +인 경우 max(start, i) + max(i+1, end)
                3-2) 연산자가 -인 경우 max(start, i) - min(i+1, end)
         */
        if(maxMem[start][end] != Integer.MIN_VALUE) return maxMem[start][end]; // 메모이제이션 활용
        if(end - start == 1) return Integer.parseInt(arr[start]); // 종료 조건 -> 범위에 하나의 숫자만 있을 때 해당 숫자 변환
        int max = Integer.MIN_VALUE;
        for(int i =  start +1; i < end; i+=2){
            int leftValue = max(start, i , arr); // start ~ i까지 중 제일 큰 값
            int calculateValue;
            if(arr[i].equals("+")){
                int rightValue = max(i+1, end, arr); // i+1 ~ end까지 중 제일 큰 값
                calculateValue = leftValue+rightValue;
            }else{
                int rightValue = min(i+1, end, arr);
                calculateValue = leftValue-rightValue;
            }
            if(calculateValue > max) max = calculateValue;
        }
        return maxMem[start][end] = max;

    }
    private final int[][] minMem = new int[202][202];
    private int min(int start, int end, String[] arr){
        if(minMem[start][end] != Integer.MIN_VALUE) return minMem[start][end];
        if(end-start == 1) return Integer.parseInt(arr[start]); // 재귀함수 종료 조건
        int min = Integer.MIN_VALUE;
        for(int i = start+1; i < end; i+=2){
            int leftValue = max(start, i, arr);
            int calculatedValue;
            if(arr[i].equals("-")){
                int rightValue = min(i+1, end, arr);
                calculatedValue = leftValue - rightValue;
            }else{
                int rightValue = max(i+1, end, arr);
                calculatedValue = leftValue + rightValue;
            }
            if(calculatedValue < min){
                min = calculatedValue;
            }
        }
        return minMem[start][end] = min;
    }


    public int solution(String[] arr){
        for(int[] row: maxMem){
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        for(int[] row:minMem){
            Arrays.fill(row, Integer.MIN_VALUE);
        }

        return max(0, arr.length, arr);

    }
}
