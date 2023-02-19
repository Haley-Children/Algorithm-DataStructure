package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_7795 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        final int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int count = 0;
            int idx = n - 1;

            long[] A = new long[n];
            long[] B = new long[m];
            Stack<Long> s = new Stack<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) A[i] = Long.parseLong(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) B[i] = Long.parseLong(st.nextToken());

            Arrays.sort(A);
            Arrays.sort(B);

            for (long b : B) s.push(b);

            while(!s.isEmpty() && idx >= 0) {
                if(s.peek() < A[idx]) {
                    count += s.size();
                    idx--;
                }
                else {
                    s.pop();
                }
            }

            System.out.println(count);
        }
    }

}

