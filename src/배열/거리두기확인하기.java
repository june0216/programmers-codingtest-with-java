package src.배열;
import java.util.*;
import java.io.*;
//단순 이동이 아니라 맨해튼 거리 2인 모든 거리들을 추가적으로 검사해야함
//먼저 맨해튼 거리가 1인 것들을 둘러보고 파티션이 없다면 그 주변을 검사하는 식으로 하면 된다.
public class 거리두기확인하기 {
    public static final int dx[] = {0, -1, 1, 0}; // 상좌우하
    public static final int dy[] = {-1, 0, 0, 1};

    private boolean isNextToVolunteer(char[][] room, int x, int y, int exclude){
        for(int d = 0; d < 4; d++){
            if(d == exclude) continue;
            int nx = dx[d] + x;
            int ny = dy[d] + y;
            if(nx < 0 || nx > room.length || ny < 0 || ny > room.length){
                continue;
            }
            if(room[nx][ny] == 'P') return true;
        }
        return false;
    }
    private boolean isDistanced(char[][] room, int x, int y){
        for(int i = 0; i < 4; i++){
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            if(nx < 0 || nx > room.length || ny < 0 || ny > room[nx].length){
                continue;
            }
            switch (room[nx][ny]){
                case 'P': return false; // 맨해튼 거리 1에 응시자가 바로 옆자리면 조건 만족하지 않음
                case 'O' :
                    if (isNextToVolunteer(room, nx, ny, 3-i)) return false; // 재 확인하고 있는 방향과 반대 방향을 제외시키기 위함, 맨해튼 거리 2인 곳에서 응시자가 있는지 또 확인
                    break;
            }
        }
        return true;
    }

    private boolean isDistanced(char[][] room){ // 거리두기 검사
        for(int y = 0; y < room.length; y++){
            for(int x = 0; x < room.length; x++){
                if(room[x][y] != 'P') continue; // 응시자랑 가까이 있지 않음
                if(!isDistanced(room, x, y)) return false; // 그 위치에서 거리두기를 지키는지 검사
            }
        }
        return true;
    }

    public int[] solution(String[][] places){
        int[] answer = new int[places.length];
        for(int i = 0; i < answer.length; i++){
            String[] place = places[i];
            char[][] room = new char[place.length][];
            for(int j = 0; j < room.length; j++){
                room[j] = place[j].toCharArray();
            }
            if(isDistanced(room)){
                answer[i] = 1;
            }
            else{
                answer[i] = 0;
            }

        }
        return answer;
    }

}
