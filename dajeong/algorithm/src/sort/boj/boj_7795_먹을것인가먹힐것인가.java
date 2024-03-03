package sort.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj_7795_먹을것인가먹힐것인가 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            ArrayList<Integer> a = new ArrayList<>();
            ArrayList<Integer> b = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                a.add(Integer.parseInt(st.nextToken()));
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                b.add(Integer.parseInt(st.nextToken()));
            }

            // 둘다 오름차순 정렬 후 투포인터로 시간 좀 더 깎음
            Collections.sort(a);
            Collections.sort(b);

            // 가능한 b의 인덱스만큼 쌍이 가능하다.
            int bIdx = 0;
            int cnt = 0;
            for (int i = 0; i < a.size(); i++) {
                int A = a.get(i);
                while (bIdx < M && b.get(bIdx) < A) {
                    bIdx++;
                }
                cnt += (bIdx);
            }

            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }

}
