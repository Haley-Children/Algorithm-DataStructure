package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_5648 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        String[] arr = new String[n];
        long[] sorted = new long[n];

        // initialize
        int idx = 0;
        while(st.hasMoreTokens()) {
            arr[idx++] = st.nextToken();
        }

        while(br.ready()) {
            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()) {
                arr[idx++] = st.nextToken();
            }
        }

        // 숫자 뒤집기
        for (int i = 0; i < n; i++) {
            String s = arr[i];
            for(int digit = s.length() - 1; digit >= 0; digit--) {
                sorted[i] *= 10;
                sorted[i] += s.charAt(digit) - '0';
            }
        }

        Arrays.sort(sorted);
        StringBuilder sb = new StringBuilder();
        for(long i : sorted) sb.append(i).append('\n');
        System.out.println(sb);
    }

}

