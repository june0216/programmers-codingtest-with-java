package src.우선순위큐;

import java.util.PriorityQueue;

class 더맵게 {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int sco : scoville) priorityQueue.add(sco);

        while (priorityQueue.size() >= 2 && priorityQueue.peek() < K) {
            int new_sco = priorityQueue.poll() + priorityQueue.poll() * 2;

            priorityQueue.add(new_sco);
            answer++;
        }

        if (priorityQueue.peek() < K) return -1;
        return answer;
    }
}
