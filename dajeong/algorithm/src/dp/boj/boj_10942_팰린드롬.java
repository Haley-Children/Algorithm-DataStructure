package dp.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_10942_팰린드롬 {


    // 직접 표 그려보니까 풀림!
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 수열 크기
        int[] nums = new int[N + 1]; // 1-index
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine()); // 질문 갯수

        int[][] memo = new int[N + 1][N + 1]; // 팰린드롬 가능한지 불가능한지 기록
        // 행 = 시작 위치, 열 = 끝나는 위치
        // 가능한 경우 1, 불가능한 경우 0

        // 초기값
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                memo[i][j] = -1;
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    memo[i][j] = 1;
                    if (i + 1 <= N) {
                        if (nums[i] == nums[i + 1]) {
                            memo[i][i + 1] = 1;
                        } else {
                            memo[i][i + 1] = 0;
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = N; i >= 1; i--) { // start
            for (int j = N; j > i + 1; j--) { // end *** i+1보다 커야한다!! 초기값을 설정해줬으니까ㅜㅜ
                if (nums[i] == nums[j]) { // start == end이면 팰린드롬 확인
                    if (memo[i + 1][j - 1] == 1) {
                        memo[i][j] = 1;
                    } else {
                        memo[i][j] = 0;
                    }
                } else { // start != end면 팰린드롬 불가
                    memo[i][j] = 0;
                }
            }
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(memo[s][e] + "\n");
        }


        System.out.println(sb);

    }
}
