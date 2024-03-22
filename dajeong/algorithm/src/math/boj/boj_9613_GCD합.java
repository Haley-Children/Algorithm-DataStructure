package math.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_9613_GCD합 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            long sum = 0; // long 주의
            int N = Integer.parseInt(st.nextToken());
            ArrayList<Integer> arr = new ArrayList<>();
            for (int n = 0; n < N; n++) {
                arr.add(Integer.parseInt(st.nextToken()));
            }
            for (int i = 0; i < arr.size() - 1; i++) {
                int first = arr.get(i);
                for (int j = i + 1; j < arr.size(); j++) {
                    int second = arr.get(j);
                    sum += gcd(first, second);
                }
            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }

    private static int gcd(int x, int y) {
        if (y == 0) {
            return x;
        }
        return gcd(y, x % y);
    }

}
