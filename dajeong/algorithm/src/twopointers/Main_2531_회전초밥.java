package twopointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2531_회전초밥 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //접시의 수
        int d = Integer.parseInt(st.nextToken()); //초밥의 가짓수
        int k = Integer.parseInt(st.nextToken()); //연속해서 먹는 접시의 수
        int c = Integer.parseInt(st.nextToken()); //쿠폰번호

        int[] belt = new int[n];
        for (int i = 0; i < n; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }
        int[] count = new int[d+1]; // 초밥 종류별로 먹은 갯수 세는 배열
        int max = 0; // 최대로 먹을 수 있는 초밥 종류 수
        int ans = 0; // 정답

        // 초기값 설정
        for (int i = 0; i < k; i++) {
            if (count[belt[i]]++ == 0) max++;
        }
        ans = max;

        for (int i = k; i < n + k; i++) {
            int cur = i % n;
            int prev = (i-k)%n;

            if (--count[belt[prev]]==0) max--;

            if (count[belt[cur]]++ == 0) max++;

            if (count[c] == 0) {
                ans = Math.max(ans, max + 1);
            } else {
                ans = Math.max(ans, max);
            }
        }
        System.out.println(ans);

    }

}
