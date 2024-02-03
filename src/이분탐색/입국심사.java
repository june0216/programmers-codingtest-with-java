package src.이분탐색;

import java.util.*;
import java.io.*;
/*
처음에는 왜 이분 탐색인지 감이 안왔다.
하지만 값이 매우 크므로 각 상황마다 계산하는 것은 불가능하다고 느껴 이분 탐색을 선택했다.
그리고 파라메트릭 서치를 통해 정답을 찾아가는데 이것이 가능한 정답인지 확인하는 과정이 어려웠다.
하지만 직접 예시를 보며 어떻게 이 값이 나오는지 계산하는 과정을 통해 정답 여부 확인하는 로직을 발견하여 작성할 수 있었다.
 */
class 입국심사  {
    /**
     * 입국 심사 최소 몇분 걸리는지
     * @param n 사람 수
     * @param times 입국심사관별 걸리는 시간
     * @return 다 검사하려면 최소 몇분 걸리는지
     */
    public long solution(int n, int[] times) {
        long answer = binarySearch(n, times);
        return answer;
    }
    public static long binarySearch(int n, int[] times){
        Arrays.sort(times);
        long start = 0; // 여기서 0으로 하지 않은 이유

        long end = (long) n * times[times.length-1];
        long answer = 0;
        while(start <= end){
            long mid= start + ((end - start)/2);

            long people = 0;

            for(int time : times){  // 모든 입국 심사 경우들을 돌아가며 정답 시간동안 계속 사람이 있었을 때 몇명 커버가 가능한지 확인
                people += (mid / time);
            }
            if(people >= n){ // 주어진 조건 인원보다 크면 소요 시간을 더 줄일 수 있음
                end = mid -1;
                answer = mid;
            }else{
                start = mid+1;
            }
        }
        return answer;
    }
}