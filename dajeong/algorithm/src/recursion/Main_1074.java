package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1074 {

    static int n,r,c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        System.out.println(recur(n,r,c));
    }

    // 2^n * 2^n의 배열에서 r행 c열 값 구하기
    private static int recur(int n, int r, int c) {
        if (n==0) {
            return 0;
        }
        int half = (int) Math.pow(2, n-1); // ******
        // 1사분면
        if (r < half && c < half) return recur(n-1, r, c);
        // 2사분면
        if (r < half && c >= half) return half*half + recur(n-1, r, c-half);
        // 3사분면
        if (r >= half && c < half) return 2*half*half + recur(n-1, r-half, c);
        // 4사분면
        return 3*half*half + recur(n-1, r-half, c-half);

    }

}
