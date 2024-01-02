package priorityqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1715_카드정렬하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }
        int cnt = 0;
        while(pq.size() > 1) {
            int card1 = pq.poll();
            int card2 = pq.poll();
            int sum = card1 + card2;
            cnt += sum;
            pq.offer(sum);
        }
        System.out.println(cnt);
    }

}
