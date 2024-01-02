package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1780 {

    static int n;
    static int[][] arr;
    static int zeroCnt, oneCnt, minusCnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(n);
        System.out.println(minusCnt);
        System.out.println(zeroCnt);
        System.out.println(oneCnt);

    }

    // 3 ^k 배열에서 0, 1, -1 갯수 구하기
    private static void recur(int k) {
        if (k == 0) {
            if (arr[0][0] == 0) zeroCnt = 1;
            if (arr[0][0] == 1) oneCnt = 1;
            if (arr[0][0] == -1) minusCnt = 1;
            return;
        }
        recur(k/3);
        int zCnt =0;
        int oCnt = 0;
        int mCnt = 0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (arr[i][j] == 0) zCnt++;
                if (arr[i][j] == 1) oCnt++;
                if (arr[i][j] == -1) mCnt++;
            }
        }
        if (zCnt == k) zeroCnt += k;
        if (mCnt == k) minusCnt += k;
        if (oCnt == k) oneCnt += k;

    }

}
