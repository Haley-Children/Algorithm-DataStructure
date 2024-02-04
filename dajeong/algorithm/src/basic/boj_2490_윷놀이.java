package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2490_윷놀이 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt0 = 0;
            for (int j = 0; j < 4; j++) {
                int t = Integer.parseInt(st.nextToken());
                if (t == 0) {
                    cnt0++;
                }
            }

            char score = checkScore(cnt0);
            sb.append(score).append("\n");
        }
        System.out.println(sb);

    }

    private static char checkScore(int cnt0) {
        if (cnt0 == 1) {
            return 'A';
        } else if (cnt0 == 2) {
            return 'B';
        } else if (cnt0 == 3) {
            return 'C';
        } else if (cnt0 == 4) {
            return 'D';
        } else {
            return 'E';
        }
    }

}
