package src.유니온파인드;
import java.io.*;
import java.util.*;
public class 섬연결하기 {
    // 출발 지점이 정해져 있지 않음
    static int[] parent;
    static class Node implements Comparable<Node> {
        int from;
        int to;
        int weight;

        public Node(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node){
            return this.weight - node.weight;
        }
    }
    public static int find(int edge){
        if(parent[edge] == edge) return edge;
        return find(parent[edge]);
    }

    public static boolean merge(int from, int to) {
        int toParent = find(to);
        int fromParent = find(from);

        if (toParent == fromParent) {
            return true; // 이미 연결되어 있음
        }

        // 두 트리를 하나로 합침
        if (toParent > fromParent) {
            parent[toParent] = fromParent;
        } else {
            parent[fromParent] = toParent;
        }
        return false;
    }
    public int solution(int n, int[][] costs) {
        int answer = 0;

        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i; // 처음에는 자기 자신이 parent
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i < costs.length; i++){
            pq.add(new Node(costs[i][0], costs[i][1], costs[i][2]));

        }


        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(!merge(node.from, node.to)){
                answer += node.weight;
            }

        }


        return answer;
    }
}
