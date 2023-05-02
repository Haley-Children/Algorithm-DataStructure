package hash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_1351 {
    static Map<Long, Long> memo = new HashMap<>();

    static long p, q;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        System.out.println(dp(n));
    }

    static long dp(long n) {
        // n에 해당하는 메모가 있을경우
        if(memo.containsKey(n)) {
            return memo.get(n);
        }
        if(n == 0) {
            return 1;
        }

        // 처음 나오는 값이면 메모
        long first = dp(n/p);
        if(!memo.containsKey(n/p)) memo.put(n/p, first);
        long second = dp(n/q);
        if(!memo.containsKey(n/q)) memo.put(n/q, second);

        return first + second;
    }
}
