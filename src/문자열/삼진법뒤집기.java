package src.문자열;

public class 삼진법뒤집기 {
    public int solution(int n){
        String str = Integer.toString(n, 3); //3진법으로 변환
        String reversed = new StringBuilder(str).reverse().toString();//뒤집기
        //String reversed = new StringBuilder().append(str).reverse().toString();
        return Integer.valueOf(reversed, 3);// 3진법을 다시 정수로 변환
        //참고  기본 int 가 필요하면 parseInt() , Integer wrapper 객체가 필요하면 valueOf() 를 사용
    }
}
