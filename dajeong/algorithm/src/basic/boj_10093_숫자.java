package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_10093_숫자 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        if (b < a) {
            long tmp = a;
            a = b;
            b = tmp;
        }

        StringBuilder sb = new StringBuilder();
        if (a == b) {
            sb.append(0).append("\n");
        } else {
            sb.append(b - a - 1).append("\n");
        }
        for (long i = a + 1; i < b; i++) {
            sb.append(i + " ");
        }
        System.out.println(sb);
    }

}
