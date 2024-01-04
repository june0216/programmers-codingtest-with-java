package src.그래프와트리;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//10만번 이동가능하다. 음의 방향으로도 10만번 가능하므로 모두 표현하려면 인접 행렬 그래프로는 x축 20만, y축 20만의 크기를 할당해야한다. -> 너무 크다 -> 인접 리스트로 표현하자
//사방이 막혀있는 방이 생기는 경우 -> 기존에 방문했던 정점으로 다시 이동하는 것? -> 그렇게 만났다고 하더라도 기존에 형성된 간선 그대로 이동한다면 방이 생기지 않는다.
//따라서 "기존에 방문했던 정점을 새로운 간선을 통해 방문할 때 방이 생성된다
// 예외 -> 대각선으로 한 번 이동했는데 삼각형 2개가 나와 2개의 방이 생성되는 경우 -> 교차점을 정점으로 세분화하면 된다.
public class 방의개수 {
    private final static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    private final static int[] dy = {-1, 1, 0, -1, -1, -1, 0, 1};

    //인접 리스트로 만들기 위한 정점 클래스
    private static class Vertex{
        public final int x;
        public final int y;
        public final Set<String> connectVertices; // 한 정점과 연결된 정점들을 순회하기보다는 두 정점이 연결되어 있는지 검사하므로 Set, 클래스 필드를 인터페이스 타입으로 선언

        public final String id; // 해당 좌표에 정점이 있는지 확인하기 위한 문자열 id
        public Vertex(int x, int y){
            this.x = x;
            this.y = y;
            this.id = id(x,y);
            this.connectVertices = new HashSet<>();
        }
        public static String id(int x, int y){ //좌표를 생성하는 메서드 + 해당 좌표에 정점이 있는지 검사할 때 재사용하기 위해 public static 메서드로 구성(static 메서드는 인스턴스 없이 생성 가능)
            return String.format("(%d, %d)", x, y);
        }

    }
    public int solution(int[] arrows){
        int count = 0;
        Map<String, Vertex> vertices = new HashMap<>(); //id를 key로 하고 Vertex 클래스의 객체를 value로 하는 Map을 사용하여 좌표별로 생성된 정점 객체를 관리할 수 있다.
        Vertex v = new Vertex(0, 0);//원점에서 출발
        vertices.put(v.id, v);
        for(int d:arrows){
            for(int i = 0; i< 2; i++){ // 이동 로직을 2번 반복처리 -> 한 번의 간선으로 방이 2개 생길 경우
                /*
                이 문제에서 간선은 한 점에서 다른 점으로의 이동을 의미합니다. 일반적으로, 두 간선이 교차하면 새로운 방이 생성됩니다. 하지만, 이 문제에서는 각 간선이 매우 짧기 때문에, 일반적인 방식으로는 두 간선이 교차하는 것을 정확히 감지하기 어렵습니다.
                 각 이동을 2번 반복하는 것은 사실상 간선의 길이를 "늘리는" 효과를 낳습니다. 이는 간선이 충분히 길어져서 서로 교차할 수 있는 경우를 정확히 포착하기 위함입니다. 각 이동 단위를 두 배로 늘림으로써, 두 간선이 교차하는 상황을 더 쉽게 감지할 수 있게 됩니다.
                 "이동을 두 번 반복한다"는 것은, 각 명령(화살표 방향)에 따라 한 번 이동하는 대신 두 번 이동한다는 뜻입니다. 예를 들어, 만약 명령이 "북쪽으로 이동"이라면, 실제로는 북쪽으로 두 번 이동하게 됩니다. 이렇게 하는 이유는 간선(이동 경로)이 실제로 다른 간선과 교차하는지를 더 정확하게 감지하기 위함입니다.
                 */
                int x = v.x + dx[d];
                int y = v.y + dy[d];
                String id = Vertex.id(x, y);
                if(!vertices.containsKey(id)){ // 기존에 해당 좌표를 방문한 적이 없다면 새로운 정점 객체를 생성한 후 관리를 위해 넣어주기
                    vertices.put(id, new Vertex(x, y));
                }
                else if(!v.connectVertices.contains(id)){ // 방문했었는데 간선이 없었다면
                    count++;

                }
                Vertex u = vertices.get(id);
                v.connectVertices.add(u.id);
                u.connectVertices.add(v.id); // 간선을 이어준다.
                v = vertices.get(id); //새로운 좌표로 이동
            }

        }
        return count;



    }
}
