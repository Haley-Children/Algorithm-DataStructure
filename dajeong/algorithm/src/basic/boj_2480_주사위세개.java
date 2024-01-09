package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2480_주사위세개 {

    public static void main(String[] args) throws IOException {
        int[] cntArr = new int[7];
        int dupVal = 0;
        int maxVal = 0;
        int duplicatedCnt = 1;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 3; i++) {
            int t = Integer.parseInt(st.nextToken());
            if (cntArr[t] > 0) {
                duplicatedCnt++;
                dupVal = t;
            } else if (cntArr[t] == 0) {
                if (maxVal < t) {
                    maxVal = t;
                }
            }
            cntArr[t]++;
        }

        int ans = 100 * maxVal;
        if (duplicatedCnt == 3) {
            ans = 10000 + dupVal * 1000;
        } else if (duplicatedCnt == 2) {
            ans = 1000 + dupVal * 100;
        }

        System.out.println(ans);
    }
}
