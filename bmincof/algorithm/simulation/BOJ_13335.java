import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13335 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 트럭 개수
        int n = Integer.parseInt(st.nextToken());
        int[] trucks = new int[n];

        // 다리 길이
        int w = Integer.parseInt(st.nextToken());
        // 다리 최대 하중
        int l = Integer.parseInt(st.nextToken());

        int totalWeight = 0;
        int time = 1;

        Queue<Node> bridge = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }

        int tIdx = 0;

        // 첫 트럭
        bridge.add(new Node(trucks[tIdx], time));
        totalWeight += trucks[tIdx++];

        // 모든 트럭이 다리를 지날 때 까지
        while(!bridge.isEmpty()) {
            time++;

            // 트럭이 다리를 건너면 pop
            if(time - bridge.peek().entered == w) {
                totalWeight -= bridge.poll().weight;
            }

            // 새로운 트럭이 다리에 들어갈 수 있으면 push
            if(tIdx < n && totalWeight + trucks[tIdx] <= l) {
                bridge.add(new Node(trucks[tIdx], time));
                totalWeight += trucks[tIdx++];
            }
        }

        System.out.println(time);

    }

    static class Node {
        int weight;
        int entered;

        Node(int weight, int entered) {
            this.weight = weight;
            this.entered = entered;
        }
    }

}

