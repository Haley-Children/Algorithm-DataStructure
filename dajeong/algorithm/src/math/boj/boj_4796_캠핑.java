package math.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_4796_캠핑 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tIdx = 1;
        while ((st = new StringTokenizer(br.readLine())).hasMoreTokens()) {
            int L = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            if (L == 0 && P == 0 && V == 0) {
                break;
            }

            int ans = V / P * L;
            int check = V % P;
            if (check > 0) {
                if (L >= check) {
                    ans += check;
                } else {
                    ans += L;
                }
            }
            sb.append(String.format("Case %d: %d\n", tIdx++, ans));
        }

        System.out.println(sb);

    }

}
