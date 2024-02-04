package dp.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class code_겹치지않게선분고르기2 {

    static Pair[] lines;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        lines = new Pair[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            lines[i] = new Pair(x, y);
        }
        // **** Initializing the first element
        lines[0] = new Pair(0, 0);
        Arrays.sort(lines);

        int[] dp = new int[N + 1];
        Arrays.fill(dp, -1);
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = 1;
            for (int j = 1; j < i; j++) {
                if (lines[i].x <= lines[j].y || dp[j] == -1) {
                    continue;
                }
                dp[i] = Math.max(dp[i], dp[j] + 1); // *** j!!
            }
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
        }

        System.out.println(max);
    }

    private static class Pair implements Comparable<Pair> {

        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair o) {
            if (x == o.x) {
                return this.y - o.y;
            } else {
                return x - o.x;
            }
        }
    }
}
