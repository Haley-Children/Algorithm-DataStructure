package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;

// 1. 빈도수 -> HashMap --> 순서를 유지해야 하므로 LinkedHashMap
// 2. 등장 횟수가 같을 시 순서 유지 - stable sort -> Collections.sort()
public class Main_2910 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        HashMap<Integer, Integer> map = new LinkedHashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int key = Integer.parseInt(st.nextToken());
            map.put(key, map.getOrDefault(key, 0)+1);
        }

        List<Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet()); // ****
        list.sort(new Comparator<Entry<Integer, Integer>>() {
            @Override
            public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue(); // ****
            }
        });

        for (Entry<Integer, Integer> kv :list) {
            for (int i = 0; i < kv.getValue(); i++) {
                sb.append(kv.getKey()).append(" ");
            }
        }

        System.out.println(sb);
    }
}
