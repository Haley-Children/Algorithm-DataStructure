package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    테트로미노
    1. 모든 테트로미노마다 회전, 반전 시킨 8가지 경우의 최댓값을 기록
    구현해야하는 함수
    rotate
    inverse
    calcSum
 */
public class Main_14500 {

    static int[][][] blocks = {
            {{1,1},{1,1}},
            {{1,0},{1,0},{1,1}},
            {{1,0},{1,1},{0,1}},
            {{1,1,1},{0,1,0}}};
    static int[][] paper;
    static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        paper = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxSum = 0;

        // 일자 막대에 대해서 회전시키면서 계산
        for(int r = 0; r <= n - 4; r++) {
            for (int c = 0; c < m; c++) {
                int sum = 0;
                for(int i = 0; i < 4; i++) sum += paper[r + i][c];
                maxSum = Math.max(maxSum, sum);
            }
        }

        for(int r = 0; r < n; r++) {
            for (int c = 0; c <= m - 4; c++) {
                int sum = 0;
                for(int i = 0; i < 4; i++) sum += paper[r][c + i];
                maxSum = Math.max(maxSum, sum);
            }
        }

        // 나머지 블럭들을 회전, 반전 시키면서 계산
        for (int i = 0; i < 4; i++) {
            int sum = 0;
            int[][] block = blocks[i];

            // 회전시키면서 최댓값 찾기
            sum = Math.max(sum, calcMaxSum(block));
            sum = Math.max(sum, calcMaxSum(rotate(block)));
            sum = Math.max(sum, calcMaxSum(rotate(rotate(block))));
            sum = Math.max(sum, calcMaxSum(rotate(rotate(rotate(block)))));

            block = inverse(block);

            // 반전시킨 후 회전시키면서 최댓값 찾기
            sum = Math.max(sum, calcMaxSum(block));
            sum = Math.max(sum, calcMaxSum(rotate(block)));
            sum = Math.max(sum, calcMaxSum(rotate(rotate(block))));
            sum = Math.max(sum, calcMaxSum(rotate(rotate(rotate(block)))));

            maxSum = Math.max(maxSum, sum);
        }

        System.out.println(maxSum);
    }

    static int calcMaxSum(int[][] block) {
        int maxSum = 0;
        int h = block.length;
        int w = block[0].length;

        // 전체 종이를 돌면서
        for (int r = 0; r <= n - h; r++) {
            for (int c = 0; c <= m - w; c++) {
                // 테트로미노가 덮는 블럭의 합 계산 후 비교
                int sum = 0;
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        sum += paper[r + i][c + j] * block[i][j];
                    }
                }
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }

    // 블럭을 시계방향으로 90도 회전
    static int[][] rotate(int[][] block) {
        int h = block.length;
        int w = block[0].length;
        int[][] rotated = new int[w][h];

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                rotated[i][j] = block[h - j - 1][i];
            }
        }

        return rotated;
    }

    // 블럭을 상하로 반전
    static int[][] inverse(int[][] block) {
        int h = block.length;
        int w = block[0].length;

        int[][] inversed = new int[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                inversed[i][j] = block[h-i-1][j];
            }
        }

        return inversed;
    }

}

