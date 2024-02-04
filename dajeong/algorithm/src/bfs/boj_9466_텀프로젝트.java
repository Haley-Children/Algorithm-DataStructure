package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 풀이 참고. 좋은 테크닉, 아이디어 얻어가기
// 방문배열을 매번 초기화 하지 않고, 값을 다르게 설정하여 O(N)으로 만들 수 있다.
public class boj_9466_텀프로젝트 {

    public static final int NOT_VISITED = 0;
    public static final int CYCLE = -1;
    public static int[] arr;
    public static int[] state;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder ans = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine()); // 학생 수
            st = new StringTokenizer(br.readLine());
            arr = new int[n+1]; // 1-index
            state = new int[n+1]; // 1-index
            for (int i = 1; i <= n; i++) {
                int v = Integer.parseInt(st.nextToken());
                arr[i] = v;
            }

            // 미방문 학생 (그래프) 탐색
            for (int i = 1; i <= n; i++) {
                if (state[i] == NOT_VISITED) {
                    run(i);
                }
            }

            // 사이클에 해당되지 않는 학생 세기
            int cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (state[i] != CYCLE) {
                    cnt++;
                }
            }
            ans.append(cnt).append("\n");
        }
        System.out.println(ans);


    }

    private static void run(int x) {
        int cur = x;
        while (true) {
            state[cur] = x; // state 배열에 시작 노드 값 입력하여 상태 제한
            cur = arr[cur];

            // 1. 현재 run 실행 중 사이클 or 비사이클을 만난 경우
            if (state[cur] == x) {
                while (state[cur] != CYCLE) {
                    state[cur] = CYCLE;
                    cur = arr[cur];
                }
                return;
            }

            // 2. 이전 run 실행의 사이클 or 비사이클을 만난 경우
            if (state[cur] != NOT_VISITED) {
                return;
            }
        }
    }

}
