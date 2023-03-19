package mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9372 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            sb.append(n-1).append("\n");
            if(tc == t-1) break;

            int m = Integer.parseInt(st.nextToken());

            for (int i = 0; i < m; i++) {
                br.readLine();
            }
        }

        System.out.println(sb);

    }
}
