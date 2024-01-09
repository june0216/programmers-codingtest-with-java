package src.재귀;

import java.util.List;

public class 하노이의탑 {
    /* 처음으로 생각한 것
      1) 상태 - 배열의 상태 , 움직인 원판 번호+기둥 번호  -> 옮기는 것을 함수 내에서 해야한다고 생각
      2) 종료 조건 - 3번 기둥에 모든 원판이 있을 때
      3) 점화식 - 제일 큰 원판을 옮긴다
    */
    /*
    책에서 생각한 것
    1) 상태 -
        옮기려는 원판의 개수 n
        원판이 현재 위치한 기둥 from
        원판이 이동해야하는 기둥 to
    2) 종료 조건 - 원판을 1개만 옮길 때
        상태 (n, from, to)에서 n의 수는 1로 고정되어 있고 from -> to만 고려하면 된다.
        1개를 from에서 to로 옮기는 문제 == 바로 from에서 to로 원판을 옮기면 된다.

    3) 점화식 -> 원판 n개를 이동시키는 부분 문제는 원반 N-1개를 이동시키는 부분 문제로 해결할 수 있다.
    먼저 n-1개의 원판(목표가 아닌 원판)을 보조 원판에 옮기고
    목표 원판을 목적지로 옮기고
    다시 n-1개의 원판을 원래 위치로 두기
    빈기둥 = 6-from-to
    (n, from, to) = (n-1, from , empty) + (1, from , to) + (n-1, empty, to)
     */

    // 상태를 이용하여 재귀 메서드 -> 부분 문제를 해결하는 메서드 -> 원판의 이동 과정을 반환
    private void hanoi(int n, int from, int to, List<int[]> process){ // 이때 process는 하노이의 과정을 다 이어붙이는 것이다. -> 만약 과정마다 새로운 List를 만들어 반환한다면 이미 구현한 과정을 다시 순회하는 비효율적인 코드가 될 것이므로 과정을 기록하는 변수 추가
        //if(n == 1) return List.of(new int[] {from, to});
        if(n==1) {
            process.add(new int[] {from, to});
            return;
        }
        // 더 작은 부분 문제를 해결하고 그 결과로 나온 과정들을 이어 붙여야한다.
        int empty = 6 - from -to;
        hanoi(n-1, from, empty, process);
        hanoi(1, from, to, process);
        hanoi(n-1, empty, to, process);

    }

}
