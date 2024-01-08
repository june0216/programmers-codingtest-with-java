package src.dp;

import java.util.Arrays;

public class 등굣길 {
    private final int[][] mem = new int[101][101];

    private int count(int x, int y, int w, int h, boolean[][] isPuddle){
        if(x > w || y > h) return 0; // 종료조건 1 : 범위를 벗어날 때 0을 반환
        if(x == w && y == h) return 1; // 종료 조건 2 : 학교를 만났을 때 경로의 수 1 반환
        if(isPuddle[y][x]) return 0; // 종료 조건 3 : 허들이 있을 때
        if(mem[y][x] != -1) return mem[y][x]; // 메모이제이션 검사

        //점화식 = (x, y) = (x+1, y) + (x, y+1)
        int total = count(x +1, y, w, h, isPuddle) + count(x, y+1 , w, h, isPuddle);
        return mem[x][y] = total % 1000000007;

    }
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        for(int[] row : mem){
            Arrays.fill(row, -1);
        }
        boolean[][] isPuddle = new boolean[n+1][m+1]; // 웅덩이 여부 확인
        for(int[] p : puddles){
            isPuddle[p[1]][p[0]] = true;
        }
        return count(1, 1, m, n, isPuddle);
    }
}
