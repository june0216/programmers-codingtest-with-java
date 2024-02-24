package src.해시.라빈카프;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 찾기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String parent = br.readLine();
        String pattern = br.readLine();
        final int MOD = 100000007;
        int parentSize = parent.length();
        int patternSize = pattern.length();
        int count = 0;
        List<Integer> location = new ArrayList<>();
        long parentHash = 0, patternHash = 0, power = 1;

        for(int i=0; i<=parentSize-patternSize; i++) {
            if (i == 0) {
                for (int j = 0; j < patternSize; j++) {
                    parentHash = (parentHash + (parent.charAt(patternSize - 1 - j)) * power) % MOD;
                    patternHash = (patternHash + (pattern.charAt(patternSize - 1 - j)) * power) % MOD;

                    if (j < patternSize - 1) {
                        power = (power % MOD * 31) % MOD;
                    }
                }

            }else {
                parentHash = 31 * parentHash % MOD - 31 * parent.charAt(i - 1) * power % MOD + parent.charAt(i + patternSize - 1);
                parentHash %= MOD;
            } /*else {
                parentHash = 31 * parentHash % MOD - 31 * parent.charAt(i - 1) * power % MOD + parent.charAt(
                        i + patternSize - 1);
                parentHash %= MOD;
            }*/
            boolean found = true;

            if(parentHash == patternHash){
                for(int j = 0; j < patternSize; j++){
                    if(pattern.charAt(j) != parent.charAt(i+j)){
                        found = false;
                    }
                }
                if(found){
                    count+=1;
                    location.add(i+1);
                }
            }


        }

        StringBuilder sb = new StringBuilder();
        sb.append(count).append("\n");
        for (int num : location) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }

}
