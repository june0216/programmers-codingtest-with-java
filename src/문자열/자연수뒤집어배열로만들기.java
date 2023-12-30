package src.문자열;

public class 자연수뒤집어배열로만들기 {
    public int[] solution(long n){
        String str = Long.toString(n);
        String reversed = new StringBuilder(str).reverse().toString(); //문자열 뒤집기
        char[] arr = reversed.toCharArray();
        int[] result = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            result[i] = arr[i] - '0'; //각 문자를 정수로
        }
        return result;
    }
}
