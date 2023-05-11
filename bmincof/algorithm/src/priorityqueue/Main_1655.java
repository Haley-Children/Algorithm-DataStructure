package priorityqueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main_1655 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        // 중간값 이하를 담을 우선순위 큐
        PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());
        // 중간값 이상을 담을 우선순위 큐
        PriorityQueue<Integer> upper = new PriorityQueue<>();

        lower.offer(Integer.parseInt(br.readLine()));
        System.out.println(lower.peek());

        while(n-- > 1) {
            int next = Integer.parseInt(br.readLine());

            // 다음 값이 현재 중간값이하라면
            if(next <= lower.peek()) {
                lower.offer(next);
            } else {
                upper.offer(next);
            }

            // 두 우선순위 큐의 크기 차이가 2 이상이라면 밸런싱하기
            if(lower.size() - upper.size() > 1) {
                upper.offer(lower.poll());
            } else if(upper.size() - lower.size() > 1) {
                lower.offer(upper.poll());
            }

            sb.append(lower.size() >= upper.size() ? lower.peek() : upper.peek()).append("\n");
        }

        System.out.println(sb);
    }
}
