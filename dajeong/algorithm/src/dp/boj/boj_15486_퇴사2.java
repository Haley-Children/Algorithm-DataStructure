package dp.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 two pointer + dp로 시간복잡도 O(NlogN)으로 줄이기
 ** 기존 풀이는 각 알바 i에 대해 그 전 알바들 중 e[j] < s[i]를 만족하는 j를 전부 탐색해보는데 O(N)이 소요되어 시간복잡도가 O(N^2)이었다.
 1. 비효율적인 부분: i가 달라졌을 때 이전에 봤던 j를 다시 봄
   -> 이전에 e[j] < s[i-1] 조건을 만족하는 j 중 dp[j]의 최대가 되도록 하는 j를 미리 구해두었다면 좀 더 효율적인 풀이 가능
 2. 풀이
   -> 시작하는 순 말고도, 끝나는 시간 순서대로 배열 정렬 & 최댓값 j 비교 / 갱신하기

 */
public class boj_15486_퇴사2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        Work[] workByEn = new Work[N + 1];
        workByEn[0] = new Work(0, 0);

        int[] s = new int[N + 1];
        int[] e = new int[N + 1];
        int[] p = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int tx = Integer.parseInt(st.nextToken());
            int px = Integer.parseInt(st.nextToken());
            workByEn[i] = new Work(i + tx - 1, i);
            s[i] = i;
            e[i] = i + tx - 1;
            p[i] = px;
        }

        // 끝나는 날짜 기준 오름차순
        Arrays.parallelSort(workByEn, (a, b) -> {
            if (a.e != b.e) {
                return a.e - b.e;
            } else {
                return a.idx - b.idx;
            }
        });


        int[] dp = new int[N + 1];
        int maxJ = 0;
        int pt = 1;
        for (int i = 1; i <= N; i++) {
            if (e[i] > N) continue; //*
            while (workByEn[pt].e < s[i]) {
                int j = workByEn[pt].idx;
                if (dp[j] > dp[maxJ]) {
                    maxJ = j;
                }
                pt++;
            }
            dp[i] = Math.max(dp[i], dp[maxJ] + p[i]);
        }

        int max = 0;
        for (int i = 0; i <= N; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
        }

        System.out.println(max);


    }

    private static class Work {

        int e;
        int idx;

        public Work(int e, int idx) {
            this.e = e;
            this.idx = idx;
        }
    }


}
