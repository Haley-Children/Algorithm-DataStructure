package sort.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class boj_2910_빈도정렬 {

    public static void main(String[] args) throws IOException {
        // 각 원소당 횟수 저장할 map (stable sort 순서 유지를 위해 LinkedHashMap)
        // map 정렬

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int k = Integer.parseInt(st.nextToken());
            map.put(k, map.getOrDefault(k, 0) + 1);
        }

        ArrayList<Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(new Comparator<Entry<Integer, Integer>>() {
            @Override
            public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        StringBuilder sb = new StringBuilder();
        for (Entry<Integer, Integer> entry : list) {
            for (int i = 0; i < entry.getValue(); i++) {
                sb.append(entry.getKey()).append(" ");
            }
        }

        System.out.println(sb);
    }

}
