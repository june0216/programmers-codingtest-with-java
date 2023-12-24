package 배열;

import java.io.BufferedReader;
import java.util.*;
import java.io.*;

public class 교점에별만들기{

    // 좌표를 나타내는 클래스
    private static class Point{ // inner class이기 때문에 "교점에별만들기" 클래스 내에서만 접근 가능
        public final long x, y; // 데이터를 나타내는 클래스이므로 final로 불변성을 갖게 함
        //좌표 범위가 주어지지 않았기 때문에 Long 타입으로 선언
        private Point(long x, long y){ // 생성자로 초기화
            this.x = x;
            this.y = y;
        }
    }

    private Point intersection(long a1, long b1, long c1, long a2, long b2, long c2){
        double x = (double) (b1*c2 - b2*c1)/(a1*b2 - a2*b1);
        double y = (double) (a2*c1 - a1*c2)/(a1*b2-a2*b1);
        return new Point((long) x, (long) y);
    }

    private Point getMinimumPoint(List<Point> points){
        long x = Long.MAX_VALUE;
        long y = Long.MAX_VALUE;
        for(Point p : points){
            if (p.x < x) x = p.x;
            if(p.y < y) y = p.y;
        }
        return new Point(x, y);
    }

    private Point getMaximumPoint(List<Point> points){
        long x = Long.MIN_VALUE;
        long y = Long.MIN_VALUE;
        for(Point p : points){ // 모든 객체 탐색 후 가장 큰 것 반환
            if (p.x > x) x = p.x;
            if(p.y > y) y = p.y;
        }
        return new Point(x, y);
    }


    public static void main(String[] args) throws Exception {
        int[][] input = {{1, -1, 0},{2, -1, 0}};
        new 배열.교점에별만들기().solution(input);
    }

    public String[] solution(int[][] line) {
        String[] answer = {};
        List<Point> points = new ArrayList<>();
        int lineNum = line.length;


        for(int i= 0; i < lineNum; i++){ // 교점 정보 저장
            for(int j = i+1; j < lineNum; j++){
                Point intersection = intersection(line[i][0], line[i][1], line[i][2],
                        line[j][0], line[j][1], line[j][2]);
                if(intersection != null){
                    points.add(intersection);
                }
            }
        }

        Point minimum = getMinimumPoint(points); // 좌표 표현할 때 최댓값 최솟값 차이만큼 좌표를 설정하고 표현해야 하므로 이를 계산
        Point maximum = getMaximumPoint(points); //가장 큰 x와 y 값을 갖는 포인트

        int width = (int) (maximum.x - minimum.x+1);
        int height = (int) (maximum.y - minimum.y+1);

        char[][] arr = new char[height][width];
        for(char[] row : arr){
            Arrays.fill(row, '.');
        }

        for(Point p: points){ // 별찍기 -> 교점들을 다 저장하는 것은 음수영역까지 있는 좌표였고 이를 모두 최대, 최소값으로 만든 양수 2차원 배열에 넣어야 하므로 위치를 옮기는 연산 추가
            int x = (int) (p.x - minimum.x);
            int y = (int) (maximum.y-p.y); //y 좌표는 뒤집히는데(최대값이 0이 된다), 이는 2차원 배열의 행 인덱스가 위에서 아래로 증가하기 때문
            arr[y][x] = '*';
        }

        String[] result = new String[arr.length]; // 점과 별을 배열로 만들어야하므로 char로 연산했었고 반환은 String배열로 반환해야하기 때문에 형변환 과정
        for(int i = 0; i < result.length; i++){
            result[i] = new String(arr[i]);
        }
        return result;
    }
}
