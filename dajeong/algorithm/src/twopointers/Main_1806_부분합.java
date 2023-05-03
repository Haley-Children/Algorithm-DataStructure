package twopointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1806_부분합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수열의 수 갯수
        int S = Integer.parseInt(st.nextToken()); // 최소 합
        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int e = 0;
        int tot = nums[0];
        int minLen = Integer.MAX_VALUE;

        for (int s = 0; s < N; s++) {
            while(e < N && tot < S) {
                e++;
                if (e != N) tot += nums[e];
            }
            if (e == N) break; // e가 범위를 벗어날 시 종료
            if (minLen > e-s+1) minLen = e-s+1;
            tot -= nums[s];

        }
        if (minLen == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(minLen);
    }
}
