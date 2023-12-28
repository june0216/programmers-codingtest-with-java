package src.배열;

public class 삼각달팽이 {
    private static final int[] dx = {0, 1, -1}; // 변화량 , 특정 뱡항으로 이동할 때 해당 좌표값이 어떻게 변화하는지
    private static final int[] dy = {1, 0, -1}; // 값이 변하지 않으므로 상수값으로 설정

    public int[] solution(int n) {
        int[][] triangle = new int[n][n];// 삼각형을 표현할 2차원 배열
        int v = 1;
        int x = 0; // 현재 위치
        int y = 0;
        int d = 0;

        while (true) {
            triangle[y][x] = v++; // 숫자 채우기
            int nx = x + dx[d]; // 이동
            int ny = y + dy[d];
            if (nx == n || ny == n || nx == -1 || ny == -1 || triangle[ny][nx] != 0) { //현재 방향으로는 진행할 수 없다.
                d = (d + 1) % 3; //2를 넘어가면 다시 0으로
                nx = x + dx[d];
                ny = y + dy[d];
                if (nx == n || ny == n || nx == -1 || ny == -1 || triangle[ny][nx] != 0) {//전환된 방향으로도 진행할 수 없을 때 그만두기
                    break;
                }
                x = nx;
                y = ny;

            }
        }

            int[] result = new int[v - 1];
            int index = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    result[index++] = triangle[i][j];
                }

            }



        return result;
    }
}
