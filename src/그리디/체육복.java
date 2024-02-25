package src.그리디;
import java.io.*;
import java.util.*;
public class 체육복 {

    class Solution {
        public int solution(int n, int[] lost, int[] reserve) {
            int answer = n- lost.length;
            Arrays.sort(lost);
            Arrays.sort(reserve);
            List<Integer> reverseList = new ArrayList<>();
            boolean flag = false;
            for(int num : reserve){
                flag = false;
                for(int i = 0 ; i < lost.length; i++){
                    if(lost[i] == num){
                        answer+=1;
                        flag = true;
                        lost[i] = -1;
                        break;
                    }
                }
                if(!flag){
                    reverseList.add(num);
                }

            }

            for(int lostNum : lost){
                if(lostNum == -1) continue;
                if(reverseList.contains(lostNum-1)){
                    answer+=1;
                    reverseList.remove(Integer.valueOf(lostNum-1));
                    continue;
                }else if(reverseList.contains(lostNum+1)){
                    answer+=1;
                    reverseList.remove(Integer.valueOf(lostNum + 1));
                    continue;
                }
            }
            return answer;
        }
    }
}
