package hash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_13414 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 과목 수강 가능 인원
        int k = Integer.parseInt(st.nextToken());
        // 대기목록의 길이
        int l = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new LinkedHashMap<>();

        for (int i = 0; i < l; i++) {
            String id = br.readLine();
            map.remove(id);
            map.put(id, l);
        }

        for(String id : map.keySet()) {
            if(--k < 0) break;
            sb.append(id).append("\n");
        }
        System.out.println(sb);
    }
}
