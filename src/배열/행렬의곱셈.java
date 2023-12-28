package src.배열;

public class 행렬의곱셈 {
    public int[][] solution(int[][] arr1, int[][] arr2){
        int[][] arr = new int[arr1[0].length][arr2.length];
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){ // 반환 arr 하나씩 접근
                arr[i][j] = 0;
                for(int k = 0; k < arr1[0].length; k++){ // 각 원소 돌기
                    arr[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }
        return arr;
    }
}
