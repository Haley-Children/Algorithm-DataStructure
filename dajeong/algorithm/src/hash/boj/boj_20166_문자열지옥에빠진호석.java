package hash.boj;

import java.io.*;
import java.util.*;

public class boj_20166_문자열지옥에빠진호석 {
    static int N,M,K;
    static char[][] board;
    static HashMap<String, Integer> stringCntMap = new HashMap<>();
    static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1}; // 상우하좌대각선
    static int[] dy = {0, 1, 0, -1, -1, 1, -1, 1};
    static List<String> queries = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        // 신이 찾고자 하는 문자열
        for (int i = 0; i < K; i++) {
            queries.add(br.readLine());
        }

        // DFS를 사용하여 가능한 모든 문자열 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // StringBuilder 초기화 주의!!
                StringBuilder tmp = new StringBuilder();
                dfs(i, j, tmp.append(board[i][j])); 
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (String q : queries) {
            sb.append(stringCntMap.getOrDefault(q, 0)).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int r, int c, StringBuilder tmpSb) {
        String str = tmpSb.toString();
        stringCntMap.put(str, stringCntMap.getOrDefault(str, 0)+1);

        if (str.length() >= 5) {
            return;
        }

        for (int i = 0; i < 8; i++) {
            int nx = (r + dx[i]+N)%N;
            int ny = (c + dy[i]+M)%M;
            int len = tmpSb.length();
            dfs(nx,ny, tmpSb.append(board[nx][ny]));
            // StringBuilder를 이전 상태로 복원
            tmpSb.setLength(len);
        }
    }
}
