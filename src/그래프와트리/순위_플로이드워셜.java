package src.그래프와트리;

/**
 * 정보가 N-1 개가 있으면 순위를 알 수 있음
 * A-> B 이기고 B-> C 이기면 A -> C를 이겼다고 할 수 있위
 */
public class 순위_플로이드워셜 {
    class Solution {

        public int solution(int n, int[][] results) {
            int answer = 0;
            int[][] floyd = new int[n+1][n+1];
            for(int i = 0; i < results.length; i++){
                int win = results[i][0];
                int lose = results[i][1];
                floyd[win][lose] = 1;
                floyd[lose][win] = -1;
            }

            for(int start = 1; start <= n; start++){
                for(int end = 1 ; end <= n; end++){
                    for(int middle = 1 ;middle <= n; middle++){
                        if(floyd[start][middle] ==1 && floyd[middle][end] == 1){
                            floyd[start][end] = 1;
                            floyd[end][start] = -1;
                        }
                        if(floyd[start][middle] == -1 && floyd[middle][end] == -1){
                            floyd[start][end] = -1;
                            floyd[end][start] = 1;
                        }
                    }
                }
            }
            for(int i = 1 ; i <=n ; i++){
                int count =0;
                for(int j = 1; j <= n; j++){
                    if(floyd[i][j] != 0) count++;

                }
                if(count == n-1){
                    answer++;
                }
            }
            return answer;
        }
    }
}
