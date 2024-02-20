package src.그래프와트리;
import java.util.*;
import java.io.*;
public class 가장먼노드 {

    class Solution {

        public int solution(int n, int[][] edge) {
            int answer = 0;
            List<List<Integer>> nodeInfo = new ArrayList<>();
            List<Integer> weight = new ArrayList<>();
            for(int i= 0 ; i < n+1; i++){
                nodeInfo.add(new ArrayList<>());
                weight.add(1);
            }
            for(int i = 0 ; i < edge.length; i++){
                nodeInfo.get(edge[i][0]).add(edge[i][1]);
                nodeInfo.get(edge[i][1]).add(edge[i][0]);

            }

            Queue<Integer> que = new LinkedList<Integer>();


            boolean[] visited = new boolean[n+1];
            que.add(1);
            weight.set(1, 1);
            visited[1] = true;
            while(!que.isEmpty()){
                int num = que.poll();

                for(int number : nodeInfo.get(num)){
                    if(!visited[number]){
                        weight.set(number, weight.get(num)+1);
                        visited[number] = true;
                        que.add(number);
                    }
                }
            }
            int max = Collections.max(weight);
            for(int i = 1 ; i < n+1; i++){
                if(max == weight.get(i)) answer++;
            }
            return answer;
        }
    }
}
