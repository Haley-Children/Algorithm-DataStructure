import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2910 {

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        Map<Integer, Node> map = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            if(!map.containsKey(tmp)) {
                Node node = new Node(tmp);
                map.put(tmp, node);
            } else {
                map.get(tmp).upCount();
            }
        }

        ArrayList<Node> arr = new ArrayList<>(n);

        arr.addAll(map.values());
        Collections.sort(arr);

        for (Node node : arr) {
            for(int i = 0; i < node.count; i++) sb.append(node.value).append(' ');
        }
        System.out.println(sb);
    }

    static class Node implements Comparable<Node> {
        static int serial = 1;
        int value;
        int count;
        int order;

        Node(int value) {
            this.order = serial++;
            this.value = value;
            this.count = 1;
        }

        void upCount() {
            count++;
        }

        @Override
        public int compareTo(Node o) {
            if(this.count == o.count) {
                return this.order < o.order ? -1 : 1;
            } else {
                return this.count > o.count ? -1 : 1;
            }
        }

    }
}

