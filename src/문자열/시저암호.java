package src.문자열;

public class 시저암호 {
    private char push(char c, int n){
        if(!Character.isAlphabetic(c)){ // 알파벳이 아니라면 그대로 반환
            return c;
        }
        int offset = Character.isUpperCase(c)? 'A' : 'a'; // 대문자인지 소문자인지 구분
        int position = c-offset; // 알파벳에 해당하는 위치
        position = (position+n) %('Z' - 'A' +1); //n 만큼 이동 ( 범위를 넘어가면 다시 처음 알파벳부터 시작하도록)
        return (char) (position + offset); // 숫자 -> 알파벳으로 변환
    }
    public String solution(String s, int n){
        StringBuilder sb = new StringBuilder();
        for(char c: s.toCharArray()){
            sb.append(push(c, n));
        }
        return sb.toString();
    }
}
