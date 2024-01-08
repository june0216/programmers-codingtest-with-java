package src.dp;

import java.util.Arrays;

/*
상태 - (x, y) = 삼각형의 x, y에 위치한 꼭지점에서 출발했ㅇ르 때 거쳐가는 숫자의 최대값
종료 조건 - (x, H) = 0 => 삼각형 끝에 도달
중복된 하위 문제 = 각형의 중앙에 있는 특정 지점으로 연결되는 여러 경로가 있다. -> max(x, y, Triangle)는 중첩되는 하위 문제
 */
public class 정수삼각형 {
    private final int[][] mem = new int[501][501]; // 메모리제이션 하지 않고 재귀으로 돌린다면 시간 제한
    private int max(int x, int y, int[][] triangle){
        if(y == triangle.length) return 0; // 끝에 도달할 경우
        if(mem[x][y] != -1) return mem[x][y];
        return mem[x][y] = triangle[y][x] + Math.max(max(x, y+1, triangle), max(x+1, y+1, triangle));
        //모든 단계에서 두 가지 경로를 고려한다. 갈 수 있는 경로를 모두 max 함수에 넣기
    }

    public int solution(int[][] triangle){
        for(int[] row : mem){
            Arrays.fill(row, -1);
        }
        return max(0, 0, triangle);
    }
}