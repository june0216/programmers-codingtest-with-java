package src.그래프와트리;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 길찾기게임 { // 이진 트리트리를 구성하는 것
    private static class Node{
        public final int value;
        public final int x;//노드가 위치하는 좌표
        public final int y;
        public Node left; // 왼쪽 자식
        public Node right; // 오른쪽 자식

        private Node(int value, int x, int y){
            this.value = value;
            this.x = x;
            this.y = y;
        }
    }

    private void insert(Node root, Node node){
        //x 좌표에 따라 root 노드가 나타내는 트리에 node 삽입
        if(node.x < root.x){
            //왼쪽 서브 트리에 삽입
            if(root.left == null){ // 왼쪽 서브 트리가 비어있다면 -> node의 root의 서브 트리의 루트 노드가 된다.
                root.left = node;
            }
            else{
                insert(root.left, node); // 비어있지 않다면 왼쪽 서브 트리에서 다시 자신의 위치를 찾아야 한다. 왼쪽 서브 트리에 node가 삽입될 수 있도록 재귀 호출
            }
        }else{
            // 오른쪽 서브 트리에 삽입
            if(root.right == null){ // 오른쪽 서브 트리가 비어있다면 -> 오른쪽 서브 트리의 루트 노드가 된다.
                root.right = node;
            }else{
                insert(root.right, node); // 안 비어있다면 삽입한다.
            }
        }
    }
    private Node constructTree(Node[] nodes){ // 첫 노드부터 순회하며 트리를 구성할 수 있다.
        //노드의 배열을 전달받아 트리를 구성하여 루트노드 반환
        Node root = nodes[0];
        for(int i = 0; i < nodes.length; i++){
            insert(root, nodes[i]); // 모든 노드를 삽입할 수 있다. (이진 트리로 구성 완료)
        }
        return root;
    }

    private void pre(Node node, List<Integer> visits){
        if(node == null) return;
        visits.add(node.value); // 방문하면 해당 노드 값을 리스트에 기록한다.
        pre(node.left, visits);
        pre(node.right, visits);
    }

    private void post(Node node, List<Integer> visits){
        if(node == null) return;
        post(node.left, visits);
        post(node.right, visits);
        visits.add(node.value);
    }

    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = {}; // 전위 순회, 후위 순회
        Node[] nodes = new Node[nodeinfo.length];
        for(int i = 0; i < nodes.length; i++){ // nodeinfo를 Node 객체로 변환(아직 이진트리 아님, 그냥 Node 객체임)
            nodes[i] = new Node(i+1, nodeinfo[i][0], nodeinfo[i][0]);
        }
        Arrays.sort(nodes, (a, b) -> b.y - a.y); // y좌표를 내림차순으로 정렬 -> Node 객체가 있는 배열을 윗부분부터 순회하기 위해
        Node root = constructTree(nodes); // 이진 트리로 구성

        List<Integer> preorder = new ArrayList<>();
        pre(root, preorder);

        List<Integer> postorder = new ArrayList<>();
        post(root, postorder);
        return new int[][]{ // 배열로 반환
                preorder.stream().mapToInt(Integer::intValue).toArray(),
                postorder.stream().mapToInt(Integer::intValue).toArray(),
        };
    }
}
