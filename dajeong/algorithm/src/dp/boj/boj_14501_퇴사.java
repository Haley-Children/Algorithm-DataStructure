package dp.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// O(N^2) 풀이
public class boj_14501_퇴사 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Work[] works = new Work[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            works[i] = new Work(i+1, i+Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.parallelSort(works);

        long[] dp = new long[N];
        // *** 중요!! 모든 상담이 불가능할 수 있다.
        dp[0] = works[0].e <= N ? works[0].p : 0;

        for (int i = 1; i < N; i++) {
            if (works[i].e > N) continue; // ***
            dp[i] = works[i].p;
            for (int j = 0; j < i; j++) {
                if (works[i].s <= works[j].e) {
                    continue;
                }
                dp[i] = Math.max(dp[i], dp[j] + works[i].p);
            }
        }

        long max = 0;
        for (int i = 0; i < N; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
        }

        System.out.println(max);
    }

    private static class Work implements Comparable<Work> {
        int s;
        int e;
        int p;

        public Work(int s, int e, int p) {
            this.s = s;
            this.e = e;
            this.p = p;
        }


        @Override
        public int compareTo(Work o) {
            if (this.s == o.s) {
                return this.e - o.e;
            } else {
                return this.s - o.s;
            }
        }
    }
}
