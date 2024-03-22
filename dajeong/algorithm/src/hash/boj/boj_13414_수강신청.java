package hash.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class boj_13414_수강신청 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        HashSet set = new LinkedHashSet<>();
        for (int i = 0; i < L; i++) {
            String num = br.readLine();
            if (set.contains(num)) {
                set.remove(num);
                set.add(num);
            } else {
                set.add(num);
            }
        }

        Iterator it = set.iterator();
        int order = K;
        StringBuilder sb = new StringBuilder();
        while (K-->0 && it.hasNext()) { // 인원이 더 적을 경우도 고려해야 한다!
            sb.append((it.next())).append("\n");
        }
        System.out.println(sb);
    }
}
