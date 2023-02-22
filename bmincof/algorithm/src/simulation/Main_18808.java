package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    1. 스티커 하나 씩 뽑아내서 맨 왼쪽 위부터 차례대로 붙일 수 있는지 확인하기
    2. 붙일 수 있다면 붙이기
    3. 아무 곳에도 붙일 수 없다면 90도 시계방향 회전 시키고 다시 반복
    4. 270도 회전까지 못 붙인다면 스티커 버리기

    paste, isPastable, rotate
 */

public class Main_18808 {

    static int n, m, k;
    static int[][] notebook;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        notebook = new int[n][m];
        Queue<int[][]> stickers = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[r][c];
            for (int sr = 0; sr < r; sr++) {
                st = new StringTokenizer(br.readLine());
                for (int sc = 0; sc < c; sc++) {
                    sticker[sr][sc] = Integer.parseInt(st.nextToken());
                }
            }
            stickers.add(sticker);
        }

        while(!stickers.isEmpty()) {
            int[][] sticker = stickers.poll();

            pasting:
            for (int turn = 0; turn < 4; turn++) {

                for (int i = 0; i <= n - sticker.length; i++) {
                    for (int j = 0; j <= m - sticker[0].length; j++) {
                        if(isPastable(sticker, i, j)) {
                            paste(sticker, i, j);
                            break pasting;
                        }
                    }
                }

                sticker = rotate(sticker);
            }

        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(notebook[i][j] == 1) count++;
            }
        }
        System.out.println(count);
    }

    static boolean isPastable(int[][] sticker, int i, int j) {
        for (int r = 0; r < sticker.length; r++) {
            for (int c = 0; c < sticker[0].length; c++) {
                if(notebook[i + r][j + c] == 1 && sticker[r][c] == 1) return false;
            }
        }
        return true;
    }

    static void paste(int[][] sticker, int i, int j) {
        for (int r = 0; r < sticker.length; r++) {
            for (int c = 0; c < sticker[0].length; c++) {
                if(sticker[r][c] == 1) notebook[i + r][j + c] = 1;
            }
        }
    }

    static int[][] rotate(int[][] sticker) {
        int n = sticker[0].length;
        int m = sticker.length;
        int[][] rotated = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rotated[i][j] = sticker[m - 1 - j][i];
            }
        }

        return rotated;
    }

}

