package bst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main_7662_이중우선순위큐 {
    // java treeSet은 multiset이 아니므로, treemap 사용
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int tk = 1; tk <= T; tk++) {
            int K = Integer.parseInt(br.readLine()); // 연산 수
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int num = Integer.parseInt(st.nextToken());
                if (command.equals("I")) {
                    map.put(num, map.getOrDefault(num, 0)+1);
                } else if (command.equals("D")){
                    if (map.isEmpty()) continue;
                    if (num == 1) {
                        int lastKey = map.lastKey();
                        if (map.get(lastKey) > 1) {
                            map.put(lastKey, map.get(lastKey)-1);
                        } else if (map.get(lastKey) == 1) {
                            map.remove(lastKey);
                        }
                    } else if (num == -1) {
                        int firstKey = map.firstKey();
                        if (map.get(firstKey) > 1) {
                            map.put(firstKey, map.get(firstKey)-1);
                        } else if (map.get(firstKey) == 1) {
                            map.remove(firstKey);
                        }
                    }
                }
            }
            if (map.isEmpty()) sb.append("EMPTY\n");
            else sb.append(map.lastKey()+" "+map.firstKey()+"\n");
        }
        System.out.println(sb);
    }
}
