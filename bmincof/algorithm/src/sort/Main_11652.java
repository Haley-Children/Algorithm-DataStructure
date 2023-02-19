package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// 그냥 카운트 세는게 더 빨랐음
public class Main_11652 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Map<Long, Node> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            long in = Long.parseLong(br.readLine());

            if(!map.containsKey(in)) {
                map.put(in, new Node(in));
            } else {
                map.get(in).count++;
            }
        }
        ArrayList<Node> arr = new ArrayList<>(map.values());

        Collections.sort(arr);
        System.out.println(arr.get(0).value);
    }

    static class Node implements Comparable<Node> {
        long value;
        int count;

        Node(long value) {
            this.value = value;
            this.count = 1;
        }

        @Override
        public int compareTo(Node o) {
            if(this.count == o.count) {
                return this.value < o.value ? -1 : 1;
            } else {
                return this.count > o.count ? -1 : 1;
            }
        }

    }
}
