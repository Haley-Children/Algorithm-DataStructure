package hash.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// long 주의
public class boj_1351_무한수열 {
    static long N;
    static int P,Q;
    static HashMap<Long, Long> map = new HashMap<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        map.put(0L, 1L);
        long ans = recur(N,P,Q);
        System.out.println(ans);
    }

    private static long recur(long n, int p, int q) {
        if (map.containsKey(n)) {
            return map.get(n);
        } else {
            long t = recur(n/p, p, q) + recur(n/q, p, q);
            map.put(n, t);
            return t;
        }
        
    }
}
