package src.재귀;
import java.util.*;
import java.io.*;
// 어려웠던 점 => 하나의 정사각형을 4개의 정사각형으로 나누어 각 영역마다 0,1 확인을 할 때 탐색 과정을 4개로 해야하나 고민이 있었다.

/*
상태 = 1) 정사각형이 시작하는 위치와 2) 한 변의 크기
종료 조건 = 모든 원소가 1이거나 0일 때 => 여기서 전체적으로 생각하는 것이 아니라 부분 문제의 종료 조건으로 생각하기
점화식 = 0
*/
public class 쿼드트리 {
    // 재귀함수의 상태를 어떻게 정의했는지에 따라 재귀 함수의 반환형을 알 수 있다.
    // 하나의 재귀에서는 해당 상태가 나타내는 정사각형을 압축했을 떄 남아있는 0과 1의 개수를 반환해야한다.
    private static class Count{ //정적 중첩 클래스는 외부 클래스의 인스턴스와 독립적으로 존재합니다. 이는 정적 중첩 클래스의 인스턴스가 외부 클래스의 인스턴스 변수나 메서드에 직접 접근할 수 없다는 것을 의미합니다 (정적 변수나 메서드는 접근 가능). 이로 인해, 정적 중첩 클래스의 인스턴스는 외부 클래스 인스턴스에 대한 참조를 유지할 필요가 없습니다. 따라서, 외부 클래스 인스턴스의 참조를 저장하기 위한 메모리가 필요하지 않으므로, 메모리 사용량이 감소
        public final int one;
        public final int zero;

        private Count(int zero, int one){
            this.zero = zero;
            this.one = one;
        }

        public Count add(Count other){// 2개의 객체를 합치는 작업
            return new Count(this.zero+ other.zero, this.one+other.one);
            //one과 zero가 final로 선언되어 있기 때문에, 이들 값을 변경하는 대신 새로운 Count 객체를 생성하여 필요한 값을 전달
        }

    }

    // 부분 문제를 해결하는 재귀 메서드 -> 상태는 offsetX, offsetY이다.
    private Count count(int offsetX, int offsetY, int size, int[][] arr){
        //종료 조건 -> 정사각형 영역 안의 모든 원소가 같은 값을 가질 때
        // 여기서 처음에 4개의 영역을 나누어 탐색하려고 했다 하지만 전체 정사각형을 탐색하고 나눠야할 일이 있으면 그때 나누는 것이다. -> 부분 문제를 잘 설정하자
        for(int x = offsetX; x < offsetX+size; x++){
            for(int y = offsetY; y < offsetY+size; y++){
                if(arr[y][x] != arr[offsetX][offsetY]){ // 원소가 섞여있는 경우
                    int dividedSize = size / 2;
                    return count(offsetX, offsetY, dividedSize, arr)
                            .add(count(offsetX+dividedSize, offsetY, dividedSize, arr))
                            .add(count(offsetX, offsetY+dividedSize, dividedSize, arr))
                            .add(count(offsetX+dividedSize, offsetY+dividedSize, dividedSize, arr));
                }
            }
        }
        // 이중 반복문이 종료된다면 모든 같은 원소를 가지고 있다.
        if(arr[offsetY][offsetX] == 1){
            return new Count(0, 1);
        }else{
            return new Count(1, 0);
        }

    }
    public int[] solution(int[][] arr) {
        int[] answer = {};
        Count count = count(0, 0, arr.length, arr);

        return new int[] {count.zero, count.one};
    }
}
