package src.그래프와트리;
// "항상 A가 B를 이겼다." -> 방향이 있는 정보 -> 방향 그래프로 나타내기
//선수 1명을 기준으로 (그 선수에게 진 선수 + 그 선수에게 이긴 선수 + 자신) == 전체 선수의 수
public class 순위 {
    public int solution(int n, int[][] results) {
        boolean[][] graph = new boolean[n][n]; // 100명 이하이기 때문에 인접 행렬 방식으로 표현
        for(int[] edge:results){
            int u = edge[0] - 1; // 0부터 시작하는 인덱스에 맞게 변환
            int v = edge[1] - 1;
            graph[u][v] = true;
        }
        int count = 0;
        for(int u = 0; u < n; u++){
            int wins = countForward(u, graph, new boolean[n]) -1; //이긴 선수들의 수를 세기, 여기서 -1을 하는 이유는 자기 자신도 들어갔기 때문이다.
            //isVisited는 검사를 진행할 때마다 개수를 새로 세야 하므로 새로 배열을 할당하여 넘기기
            int loses = countBackward(u, graph, new boolean[n]) -1; // 진 선수들의 수를 세기
            if(wins + loses + 1== n){
                count++;
            }
        }
        return count;
    }

    private int countBackward(int u, boolean[][] graph, boolean[] isVisited) {
        int count = 1;
        for(int v = 0; v < graph.length; v++){
            if(!graph[v][u] || isVisited[v]) continue; // u가 이기고  v가 지면 graph[v][u]는 false, graph[u][v] 는 true
            isVisited[v] = true; // 중복 세기 방지
            count += countBackward(v, graph, isVisited);
        }
        return count;
    }

    private int countForward(int u, boolean[][] graph, boolean[] isVisited) {
        int count =1;
        for(int v = 0; v < graph[u].length; v++){
            if(!graph[u][v] || isVisited[v]) continue; // 진행할 수 없음
            isVisited[v] = true; // 진행할 수 있는 경우
            count += countForward(v, graph, isVisited); //u에서 진행할 수 있는 모든 정점의 개수를 탐색
        }
        return count;
    }


}
