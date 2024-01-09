package array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1475_방번호 {

    public static void main(String[] args) throws IOException {
        int[] cntArr = new int[10];
        int max = 0;
        int cnt69 = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(br.readLine());
        String str = String.valueOf(target);
        for (int i = 0; i < str.length(); i++) {
            int t = str.charAt(i) - '0';
            cntArr[t]++;
            if (t == 6 || t == 9) {
                cnt69++;
            } else {
                if (max < cntArr[t]) {
                    max = cntArr[t];
                }
            }
        }

        if (cnt69%2==1) {
            cnt69 = cnt69 /2 + 1;
        } else {
            cnt69 = cnt69 / 2;
        }

        if (max > cnt69) {
            System.out.println(max);
        } else {
            System.out.println(cnt69);
        }
    }

}
