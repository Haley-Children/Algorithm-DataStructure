package hash.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class boj_9375_패션왕신혜빈 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            HashMap<String, Integer> map = new HashMap<>();
            for (int j = 0; j < n; j++) {
                String[] split = br.readLine().split(" ");
                map.put(split[1], map.getOrDefault(split[1], 0)+1);
            }
            int ans = 1;
            Iterator<Integer> it = map.values().iterator();
            while (it.hasNext()) {
                ans *= (it.next()+1);
            }
            ans--;// 알몸인 경우 제외
            sb.append(ans).append("\n"); 

        }
        System.out.println(sb);
    }
}
